package com.saber.product.composite.service.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RouteConfiguration extends RouteBuilder {

    @Value(value = "${service.api.base-path}")
    private String apiBasePath;

    @Value(value = "${service.log.prettyPrint}")
    private String prettyPrint;

    @Override
    public void configure() throws Exception {

        restConfiguration()
                .contextPath(apiBasePath)
                .enableCORS(true)
                .bindingMode(RestBindingMode.json)
                .component("servlet")
                .apiContextPath("/v2/api-docs")
                .apiProperty("api.version","v1")
                .apiProperty("api.title","product composite service")
                .apiProperty("cors","true")
                .apiContextRouteId("doc-api")
                .dataFormatProperty("prettyPrint",prettyPrint);

    }
}
