package com.kdt.utils.response;

import org.springframework.http.HttpStatus;

public class ResponseMessage<T> {



    private int code;
    private String message;
    private T data;

    public ResponseMessage(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> ResponseMessage<T> success(T data) {
        return new ResponseMessage<>(HttpStatus.OK.value(), "successful", data);
    }

    public static <T> ResponseMessage<T> fail(T data) {
        return new ResponseMessage<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), data);
    }

    public static <T> ResponseMessage<T> failMessage(String message, T data) {
        return new ResponseMessage<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, data);
    }

    public static <T> ResponseMessage<T> failMessage(ResponseHttpStatus responseHttpStatus, T data) {
        return new ResponseMessage<>(responseHttpStatus.code, responseHttpStatus.message, data);
    }
}
