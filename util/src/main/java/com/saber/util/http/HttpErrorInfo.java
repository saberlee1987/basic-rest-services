package com.saber.util.http;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.Objects;

public class HttpErrorInfo {
    private final ZonedDateTime timeStamp;
    private final String path;
    private final HttpStatus httpStatus;
    private final String message;

    public HttpErrorInfo(){
        this.timeStamp=null;
        this.httpStatus=null;
        this.path=null;
        this.message=null;
    }
    public HttpErrorInfo( HttpStatus httpStatus,String path, String message) {
        this.timeStamp = ZonedDateTime.now();
        this.path = path;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public ZonedDateTime getTimeStamp() {
        return timeStamp;
    }

    public String getPath() {
        return path;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HttpErrorInfo that = (HttpErrorInfo) o;
        return Objects.equals(timeStamp, that.timeStamp) &&
                Objects.equals(path, that.path) &&
                httpStatus == that.httpStatus &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeStamp, path, httpStatus, message);
    }

    @Override
    public String toString() {
        return "HttpErrorInfo{" +
                "timeStamp=" + timeStamp +
                ", path='" + path + '\'' +
                ", httpStatus=" + httpStatus +
                ", message='" + message + '\'' +
                '}';
    }
}
