package com.saber.util.exceptions;

import java.util.Map;

public class BusinessException extends RuntimeException {

    private String message;
    private int statusCode;
    private String path;
    String statusText;
    String redirectLocation;
    Map<String, String> responseHeaders;
    String responseBody;
    public BusinessException(String uri, int statusCode, String statusText, String redirectLocation, Map<String, String> responseHeaders, String responseBody) {
        this.message = responseBody;
        this.statusCode = statusCode;
        this.path = uri;
        this.statusText=statusText;
        this.redirectLocation=redirectLocation;
        this.responseHeaders=responseHeaders;
        this.responseBody=responseBody;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getStatusText() {
        return statusText;
    }

    public String getRedirectLocation() {
        return redirectLocation;
    }

    public Map<String, String> getResponseHeaders() {
        return responseHeaders;
    }

    public String getResponseBody() {
        return responseBody;
    }
}
