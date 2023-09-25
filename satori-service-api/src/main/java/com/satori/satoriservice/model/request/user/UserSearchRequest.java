package com.satori.satoriservice.model.request.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @auth YanFuYou
 * @date 23/09/23 下午 10:58
 */

@Data
public class UserSearchRequest {
    @ApiModelProperty("昵称")
    private String nikeName;

    @ApiModelProperty("登录名")
    private String userName;

    @ApiModelProperty("邮箱")
    private String userEmail;

}
