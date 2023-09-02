package com.satori.model.ex;

import com.satori.model.enums.SystemCodeEnum;

/**
 * 系统业务异常类
 * @auth YanFuYou
 * @date 02/09/23 下午 08:28
 */
public class BaseException extends RuntimeException{
    /**
     * 错误码
     */
    private String code;
    /**
     * 错误消息
     */
    private String msg;


    public BaseException(String msg) {
        super(msg);
        this.code = SystemCodeEnum.SYS_INTERNAL_ERR.getCode();
        this.msg = msg;
    }

    public BaseException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
