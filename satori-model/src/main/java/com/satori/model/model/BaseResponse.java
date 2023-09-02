package com.satori.model.model;

import com.satori.model.enums.SystemCodeEnum;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 封装返回体
 * @auth YanFuYou
 * @date 02/09/23 下午 08:41
 */
public class BaseResponse<T> implements Serializable {
    static final long serialVersionUID = 42L;

    private Boolean success;

    private String code;

    private String errMsg;

    private T data;

    private String requestPath;

    private String requestId;

    private LocalDateTime requestTime;


    public static BaseResponse<Object> success(){
        BaseResponse<Object> response  = new BaseResponse<>();
        response.setCode(SystemCodeEnum.SUCCESS.getCode());
        response.setSuccess(Boolean.TRUE);
        return response;
    }

    public static <T> BaseResponse<Object> success(T data){
        BaseResponse<Object> response = success();
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

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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

    public String getRequestPath() {
        return requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(LocalDateTime requestTime) {
        this.requestTime = requestTime;
    }
}
