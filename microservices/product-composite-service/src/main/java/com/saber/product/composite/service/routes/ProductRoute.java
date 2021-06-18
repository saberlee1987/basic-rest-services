package com.saber.product.composite.service.routes;

import com.saber.api.core.product.Product;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProductRoute extends AbstractRouteBuilder {

    @Value(value = "${service.product-service.url}")
    private String productUrl;
    @Value(value = "${service.product-service.port}")
    private String productPort;
    @Value(value = "${service.product-service.productEndpoint}")
    private String productEndpointUrl;

    @Value(value = "${service.minThreadPool}")
    private Integer minThreadPool;
    @Value(value = "${service.maxThreadPool}")
    private Integer maxThreadPool;
    @Value(value = "${service.maxQueueSize}")
    private Integer maxQueueSize;


    @Override
    public void configure() throws Exception {

        super.configure();

        from("direct:product-service-route")
                .id("product-service-gateway")
                .log("get product From product Service from " + productUrl + ":" + productPort + productEndpointUrl)
                .log("header productId is ${in.header.productId}")

                .process(exchange -> {
                    Integer productId = exchange.getProperty("productId", Integer.class);
                    exchange.getIn().setHeader(Exchange.HTTP_PATH, productId);
                })
                .threads(minThreadPool,maxThreadPool).threadName("product-service-route").maxQueueSize(maxQueueSize)
                .to(productUrl + ":" + productPort + productEndpointUrl + "?bridgeEndpoint=true")
                .convertBodyTo(String.class)
                .unmarshal().json(JsonLibrary.Jackson, Product.class)
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200));

    }
}
