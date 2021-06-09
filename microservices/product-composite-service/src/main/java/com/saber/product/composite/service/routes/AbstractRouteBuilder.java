package com.saber.product.composite.service.routes;

import com.saber.util.http.HttpErrorInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.http.base.HttpOperationFailedException;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
@Slf4j
public class AbstractRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        onException(HttpOperationFailedException.class)
                .redeliveryDelay(0)
                .handled(true)
                .process(exchange -> {
                    HttpOperationFailedException exception= exchange.getProperty(Exchange.EXCEPTION_CAUGHT,HttpOperationFailedException.class);
                    String errorResponseBody =exception.getResponseBody();
                    HttpErrorInfo errorInfo = new HttpErrorInfo();
                    int statusCode= exception.getStatusCode();
                    errorInfo.setHttpStatus(HttpStatus.valueOf(exception.getStatusCode()));
                    errorInfo.setMessage(errorResponseBody);
                    errorInfo.setPath(exception.getUri());
                    errorInfo.setTimeStamp(ZonedDateTime.now());
                    log.error("Error  statusCode {} ===> {}",statusCode,errorInfo);
                    exchange.getIn().setBody(errorInfo);
                })
        .setHeader(Exchange.HTTP_RESPONSE_CODE,constant(406));


    }
}
