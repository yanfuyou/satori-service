package com.satori.satoriservice.enums;

/**
 * @auth YanFuYou
 * @date 03/09/23 下午 10:25
 */
public enum ErrorEnum {

    U_VALIDATION_ERROR("U10001","验证码错误"),
    U_VALIDATION_BLANK("U10002","验证码不能为空"),
    U_VALIDATION_EXPIRED("U10003","验证码过期"),
    U_NAME_OR_PWD_ERROR("U10004","用户名错误"),
    U_EXIST("U10005","户名已存在"),
    U_SIGN_UP_FAIL("U10006","注册失败"),


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
