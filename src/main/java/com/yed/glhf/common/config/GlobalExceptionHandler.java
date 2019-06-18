package com.yed.glhf.common.config;

import com.yed.glhf.common.exception.ServiceException;
import com.yed.glhf.common.rpc.RpcResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ValidationException;


@ControllerAdvice(basePackages = "com.yed.glhf.controller")
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    public RpcResult handleException(final ValidationException validationException) {
        logger.error("validationException is ", validationException);
        RpcResult rpcResult = new RpcResult();
        rpcResult.setCode(String.valueOf(HttpStatus.UNPROCESSABLE_ENTITY.value()));
        rpcResult.setMessage(validationException.getMessage());
        rpcResult.setError(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase());
        return rpcResult;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public RpcResult handleException(final ServiceException serviceException) {
        logger.error("serviceException is ", serviceException);
        RpcResult rpcResult = new RpcResult();
        rpcResult.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        rpcResult.setMessage(serviceException.getMessage());
        rpcResult.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        return rpcResult;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public RpcResult handleException(final Throwable throwable) {
        logger.error("throwable is ", throwable);
        RpcResult rpcResult = new RpcResult();
        rpcResult.setCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        rpcResult.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        rpcResult.setError(throwable.getMessage());
        return rpcResult;
    }
}
