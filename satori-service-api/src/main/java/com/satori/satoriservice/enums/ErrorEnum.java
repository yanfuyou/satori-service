package com.satori.satoriservice.enums;

/**
 * @auth YanFuYou
 * @date 03/09/23 下午 10:25
 */
public enum ErrorEnum {

    U_VALIDATION_ERROR("U10001","验证码错误"),
    U_VALIDATION_EXPIRED("10002","验证码过期"),
    U_NAME_OR_PWD_ERROR("10003","用户名错误"),
    U_EXIST("10004","户名已存在"),

    ;


    public final String code;

    public final String msg;


    ErrorEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
