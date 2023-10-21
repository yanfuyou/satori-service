package com.satori.service.model.request.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @auth YanFuYou
 * @date 10/09/23 下午 04:22
 */
@Data
public class UserInfoModel {
    @ApiModelProperty("用户id")
    private Long id;

    @ApiModelProperty("昵称")
    private String nikeName;

    @ApiModelProperty("密码")
    private String userPassword;

    @ApiModelProperty("头像")
    private String userAvatar;

    @ApiModelProperty("邮箱")
    private String userEmail;

    @ApiModelProperty("删除标记")
    private Byte deleted;

    @ApiModelProperty("禁用")
    private Byte disable;

    @ApiModelProperty("禁用结束时间")
    private LocalDateTime disableEndTime;
}
