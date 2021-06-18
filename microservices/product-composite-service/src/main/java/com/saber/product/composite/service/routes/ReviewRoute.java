package com.saber.product.composite.service.routes;

import com.saber.api.core.review.ReviewResponse;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ReviewRoute extends AbstractRouteBuilder {

    @Value(value = "${service.review-service.url}")
    private String reviewBaseUrl;
    @Value(value = "${service.review-service.port}")
    private String reviewPort;
    @Value(value = "${service.review-service.reviewEndpoint}")
    private String reviewEndpointUrl;

    @Value(value = "${service.minThreadPool}")
    private Integer minThreadPool;
    @Value(value = "${service.maxThreadPool}")
    private Integer maxThreadPool;
    @Value(value = "${service.maxQueueSize}")
    private Integer maxQueueSize;


    @Override
    public void configure() throws Exception {

        super.configure();
        from("direct:review-service-route")
                .id("review-service-gateway")
                .log("get Review From Review Service from "+reviewBaseUrl+":"+reviewPort+reviewEndpointUrl)
                .process(exchange -> {
                    Integer productId = exchange.getProperty("productId", Integer.class);
                    exchange.getIn().setHeader(Exchange.HTTP_QUERY, "productId="+productId);
                    exchange.getIn().setHeader(Exchange.HTTP_PATH, "");
                })
                .threads(minThreadPool,maxThreadPool).threadName("review-service-route").maxQueueSize(maxQueueSize)
                .to(reviewBaseUrl+":"+reviewPort+reviewEndpointUrl+"?bridgeEndpoint=true")
                .convertBodyTo(String.class)
                .unmarshal().json(JsonLibrary.Jackson, ReviewResponse.class)
                .setHeader(Exchange.HTTP_RESPONSE_CODE,constant(200));

    }
}
