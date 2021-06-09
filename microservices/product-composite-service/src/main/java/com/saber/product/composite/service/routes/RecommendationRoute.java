package com.saber.product.composite.service.routes;

import com.saber.api.core.recomndation.Recommendation;
import com.saber.api.core.recomndation.RecommendationResponse;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecommendationRoute extends AbstractRouteBuilder {

    @Value(value = "${service.recommendation-service.url}")
    private String recommendationUrl;
    @Value(value = "${service.recommendation-service.port}")
    private String recommendationPort;
    @Value(value = "${service.recommendation-service.recommendationEndpoint}")
    private String recommendationEndpointUrl;


    @Override
    public void configure() throws Exception {

        super.configure();
        from("direct:recommendation-service-route")
                .id("recommendation-service-gateway")
                .log("get recommendation From recommendation Service from "+recommendationUrl+":"+recommendationPort+recommendationEndpointUrl)
                .process(exchange -> {
                    Integer productId = exchange.getProperty("productId", Integer.class);
                    exchange.getIn().setHeader(Exchange.HTTP_QUERY, "productId="+productId);
                    exchange.getIn().setHeader(Exchange.HTTP_PATH, "");
                })
                .to(recommendationUrl+":"+recommendationPort+recommendationEndpointUrl+"?bridgeEndpoint=true")
                .convertBodyTo(String.class)
                .unmarshal().json(JsonLibrary.Jackson, RecommendationResponse.class)
                .setHeader(Exchange.HTTP_RESPONSE_CODE,constant(200));

    }
}
