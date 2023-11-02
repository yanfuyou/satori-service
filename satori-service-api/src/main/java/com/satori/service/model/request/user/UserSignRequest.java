package com.satori.service.model.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @auth YanFuYou
 * @date 03/09/23 下午 10:21
 */
@Data
public class UserSignRequest {

    @NotBlank(message = "用户名不能为空")
    private String userName;

    @NotBlank(message = "昵称不能为空")
    private String nikeName;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "验证码不能为空")
    private String validationCode;

}
