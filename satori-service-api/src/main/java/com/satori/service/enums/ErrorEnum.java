package com.satori.service.enums;

import com.satori.model.ex.BaseException;
import com.satori.model.model.BaseResponse;

/**
 * @auth YanFuYou
 * @date 03/09/23 下午 10:25
 */
public enum ErrorEnum {
    NULL_ID("00000","id不能为空"),
    U_VALIDATION_ERROR("U10001","验证码错误"),
    U_VALIDATION_BLANK("U10002","验证码不能为空"),
    U_VALIDATION_EXPIRED("U10003","验证码错误或过期"),
    U_NAME_OR_PWD_ERROR("U10004","用户名或密码错误"),
    U_EXIST("U10005","户名已存在"),
    U_NOT_EXIST("U10006","用户不存在"),
    U_SIGN_UP_FAIL("U10007","注册失败"),
    U_NULL_ID("U10008","id不能为空"),
    U_DEL_FAIL("U10009","注销失败"),
    U_FRIEND_ADD_FAIL("U10010","好友添加失败"),

    G_REPEAT_NAME("G10001","群名称已存在"),

    //内容错误
    C_CATE_NULL_NAME("C10001","名称不能为空"),
    C_CATE_NOT_EXST("C10002","分类不存在"),
    C_CATE_NULL_ID("C10003","id不能为空"),
    C_CONTENT_NOT_EXIST("C10004","文章不存在"),
    F_UPLOAD_FAIL("F10001","文件上传失败"),


    P_MAX_LIMIT("P10001","宠物数量达到最大限制"),
    ;


    private final String code;

    private final String msg;


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

    public BaseException buildEx(){
        return new BaseException(this.code,this.msg);
    }

    public BaseResponse<Object> buildResp(){
        BaseResponse<Object> response = new BaseResponse<>();
        response.setCode(this.code);
        response.setErrMsg(this.msg);
        return response;
    }
}
