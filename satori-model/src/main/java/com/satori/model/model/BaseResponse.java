package com.satori.model.model;

import com.satori.model.enums.SystemCodeEnum;

import java.io.Serializable;

/**
 * 封装返回体
 * @auth YanFuYou
 * @date 02/09/23 下午 08:41
 */
public class BaseResponse<T> implements Serializable {
    static final long serialVersionUID = 42L;

    private Boolean success = true;

    private String code = "0";

    private String errMsg;

    private T data;



    public static <T>BaseResponse<T> success(){
        BaseResponse<T> response  = new BaseResponse<>();
        response.setCode(SystemCodeEnum.SUCCESS.getCode());
        response.setSuccess(Boolean.TRUE);
        return response;
    }

    public static <T> BaseResponse<T> success(T data){
        BaseResponse<T> response = success();
        response.setData(data);
        return response;
    }

    public static BaseResponse<Object> fail(String errMsg){
        BaseResponse<Object> response = new BaseResponse<>();
        response.setSuccess(Boolean.FALSE);
        response.setCode(SystemCodeEnum.SYS_INTERNAL_ERR.getCode());
        response.setErrMsg(errMsg);
        return response;
    }

    public static BaseResponse<Object> fail(String code,String errMsg){
        BaseResponse<Object> response = fail(errMsg);
        response.setCode(code);
        return response;
    }

    public boolean isSuccess() {
        return success;
    }


    public Boolean getSuccess() {
        return success;
    }

    private void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        if ("0".equals(code)){
            this.success = true;
        }else {
            this.success = false;
        }
        this.code = code;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
