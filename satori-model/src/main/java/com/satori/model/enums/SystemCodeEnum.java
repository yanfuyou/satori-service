package com.satori.model.enums;

import com.satori.model.ex.BaseException;
import com.satori.model.model.BaseResponse;

/**
 * @auth YanFuYou
 * @date 02/09/23 下午 08:30
 */
public enum SystemCodeEnum implements ICodeService{

    SUCCESS("0","成功"),
    SYS_INTERNAL_ERR("500","系统内部错误"),
    DATA_NOT_EXIST("501","数据不存在"),
    METHOD_ARGS_PARSING_ERR("502","参数解析异常"),
    GET_LOCK_FAIL("503","请勿重复操作"),
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


    @Override
    public BaseException buildEx() {
        return new BaseException(this.code,this.desc);
    }

    @Override
    public BaseException buildEx(Object... args) {
        return null;
    }

    @Override
    public BaseResponse<Object> buildResp() {
        BaseResponse<Object> response = new BaseResponse<>();
        response.setCode(this.code);
        response.setErrMsg(this.desc);
        return response;
    }


}
