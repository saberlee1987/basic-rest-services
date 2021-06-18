package com.saber.product.composite.service.routes;

import com.saber.api.composite.product.ProductAggregate;
import com.saber.api.composite.product.RecommendationSummary;
import com.saber.api.composite.product.ReviewSummary;
import com.saber.api.composite.product.ServiceAddresses;
import com.saber.api.core.product.Product;
import com.saber.api.core.recomndation.Recommendation;
import com.saber.api.core.recomndation.RecommendationResponse;
import com.saber.api.core.review.Review;
import com.saber.api.core.review.ReviewResponse;
import com.saber.util.http.HttpErrorInfo;
import com.saber.util.http.ServiceUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@SuppressWarnings("All")
@Slf4j
public class ProductCompositeRoute extends AbstractRouteBuilder {

    @Autowired
    private ProducerTemplate producerTemplate;
    @Autowired
    private ServiceUtil serviceUtil;

    Product product;
    RecommendationResponse recommendationResponse;
    ReviewResponse reviewResponse;

    @Value(value = "${service.minThreadPool}")
    private Integer minThreadPool;
    @Value(value = "${service.maxThreadPool}")
    private Integer maxThreadPool;
    @Value(value = "${service.maxQueueSize}")
    private Integer maxQueueSize;

    @Override
    public void configure() throws Exception {
        super.configure();

        rest("/product")
                .get("/composite/{productId}")
                .id(Routes.PRODUCT_COMPOSITE)
                .description("product composite service")
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .enableCORS(true)
                .bindingMode(RestBindingMode.json)
                .param().name("productId").type(RestParamType.header).defaultValue("23").example("23").endParam()
                .responseMessage().code(200).responseModel(ProductAggregate.class).endResponseMessage()
                .route()
                .routeId(Routes.PRODUCT_COMPOSITE)
                .to("direct:product-composite-gateway");

        from("direct:product-composite-gateway")
                .id("product-composite-gateway")
                .setProperty("productId", header("productId"))
                .threads(minThreadPool,maxThreadPool).threadName(Routes.PRODUCT_COMPOSITE).maxQueueSize(maxQueueSize)
                .multicast().parallelProcessing(true)
                .enrich("direct:product-service-route", (oldExchange, newExchange) -> {
                    int statusCode = newExchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE, Integer.class);
                    if (statusCode != 200) {
                        HttpErrorInfo errorInfo= newExchange.getIn().getBody(HttpErrorInfo.class);
                        newExchange.getIn().setBody(errorInfo);
                    } else {
                        product = newExchange.getIn().getBody(Product.class);
                    }
                    return newExchange;
                })
                .enrich("direct:recommendation-service-route", (oldExchange, newExchange) -> {
                    int statusCode = newExchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE, Integer.class);
                    if (statusCode != 200) {
                        HttpErrorInfo errorInfo= newExchange.getIn().getBody(HttpErrorInfo.class);
                        newExchange.getIn().setBody(errorInfo);
                    } else {
                        recommendationResponse = newExchange.getIn().getBody(RecommendationResponse.class);
                    }
                    return newExchange;
                })
                .enrich("direct:review-service-route", (oldExchange, newExchange) -> {
                    int statusCode = newExchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE, Integer.class);
                    if (statusCode != 200) {
                        HttpErrorInfo errorInfo= newExchange.getIn().getBody(HttpErrorInfo.class);
                        newExchange.getIn().setBody(errorInfo);
                    } else {
                        reviewResponse = newExchange.getIn().getBody(ReviewResponse.class);
                    }
                    return newExchange;
                })
                .end()
                .process(exchange -> {
                    ProductAggregate productAggregate = createProductAggregate(product, recommendationResponse.getRecommendations(), reviewResponse.getReviews(), serviceUtil.getServiceAddress());
                    exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, 200);
                    exchange.getIn().setBody(productAggregate);
                });
    }

    private ProductAggregate createProductAggregate(Product product, List<Recommendation> recommendations, List<Review> reviews, String serviceAddress) {

        // 1. Setup product info
        int productId = product.getProductId();
        String name = product.getName();
        int weight = product.getWeight();

        // 2. Copy summary recommendation info, if available
        List<RecommendationSummary> recommendationSummaries = (recommendations == null) ? null :
                recommendations.stream()
                        .map(r -> new RecommendationSummary(r.getRecommendationId(), r.getAuthor(), r.getRate()))
                        .collect(Collectors.toList());

        // 3. Copy summary review info, if available
        List<ReviewSummary> reviewSummaries = (reviews == null) ? null :
                reviews.stream()
                        .map(r -> new ReviewSummary(r.getReviewId(), r.getAuthor(), r.getSubject()))
                        .collect(Collectors.toList());

        // 4. Create info regarding the involved microservices addresses
        String productAddress = product.getServiceAddress();
        String reviewAddress = (reviews != null && reviews.size() > 0) ? reviews.get(0).getServiceAddress() : "";
        String recommendationAddress = (recommendations != null && recommendations.size() > 0) ? recommendations.get(0).getServiceAddress() : "";
        ServiceAddresses serviceAddresses = new ServiceAddresses(serviceAddress, productAddress, reviewAddress, recommendationAddress);

        return new ProductAggregate(productId, name, weight, recommendationSummaries, reviewSummaries, serviceAddresses);
    }
}
