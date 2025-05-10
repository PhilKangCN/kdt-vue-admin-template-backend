package com.kdt.utils.response;

public enum ResponseHttpStatus {

    NOT_LOGIN(401, "用户未登录"),
    TOKEN_INCORRECT(508, "用户验证信息不正确"),
    LOGIN_IN_OTHER_CLIENTS(512, "其他客户端已登录"),
    TOKEN_EXPIRED(514, "登录已过期"),
    PARAM_INCORRECT(405, "请求参数不正确");

    public int code;
    public String message;

    ResponseHttpStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
