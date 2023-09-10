package com.satori.model.enums;

/**
 * @auth YanFuYou
 * @date 02/09/23 下午 08:30
 */
public enum SystemCodeEnum {

    SUCCESS("200","成功"),
    SYS_INTERNAL_ERR("500","系统内部错误"),
    DATA_NOT_EXIST("501","数据不存在"),
    METHOD_ARGS_PARSING_ERR("502","参数解析异常"),
    ;
    private final String code;

    private final String desc;

    SystemCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
