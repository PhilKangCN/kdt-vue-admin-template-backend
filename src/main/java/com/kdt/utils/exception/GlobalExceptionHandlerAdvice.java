package com.kdt.utils.exception;

import com.kdt.utils.response.ResponseMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandlerAdvice {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandlerAdvice.class);

    @ExceptionHandler({Exception.class})
    public ResponseMessage handleException(Exception e, HttpServletRequest request, HttpServletResponse response) {
        logger.error("统一异常处理：", e);
        return new ResponseMessage<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "服务器异常", null);
    }

}
