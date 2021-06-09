package com.saber.product.composite.service.routes;

import com.saber.api.core.recomndation.Recommendation;
import com.saber.api.core.review.Review;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HelloRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {


        rest("")
                .get("/hello")
                .description("hello service")
                .id(Routes.HELLO)
                .produces(MediaType.TEXT_PLAIN_VALUE)
                .enableCORS(true)
                .bindingMode(RestBindingMode.off)
                .route()
                .routeId(Routes.HELLO)
                .log("hello service called ....")
                .setBody().simple("Hello World")
                .setHeader(Exchange.HTTP_RESPONSE_CODE,constant(200));
    }
}
