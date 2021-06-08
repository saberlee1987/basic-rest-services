package com.saber.util.http;

import com.saber.util.exceptions.InvalidInputException;
import com.saber.util.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.server.reactive.ServerHttpRequest;

@RestControllerAdvice
@Slf4j
public class GlobalControllerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public HttpErrorInfo handleNotFoundException(ServerHttpRequest request,Exception ex){
        return createHttpErrorInfo(HttpStatus.NOT_FOUND,request,ex);
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(InvalidInputException.class)
    @ResponseBody
    public HttpErrorInfo handleInvalidInputException(ServerHttpRequest request,Exception ex){
        return createHttpErrorInfo(HttpStatus.UNPROCESSABLE_ENTITY,request,ex);
    }

    private HttpErrorInfo createHttpErrorInfo(HttpStatus httpStatus,ServerHttpRequest request,Exception ex){
        String path= request.getPath().pathWithinApplication().value();
        String message= ex.getMessage();
        log.debug("Returning HTTP status: {} for path: {}, message: {}", httpStatus, path, message);
        return new HttpErrorInfo(httpStatus,path,message);

    }

}
