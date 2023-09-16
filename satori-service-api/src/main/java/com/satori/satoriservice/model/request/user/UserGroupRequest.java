package com.satori.satoriservice.model.request.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @auth YanFuYou
 * @date 16/09/23 下午 10:55
 */

@ApiModel
public class UserGroupRequest {

    @ApiModelProperty("组id,更新时使用")
    private Long id;

    @ApiModelProperty("创建者id")
    private Long createUserId;

    @ApiModelProperty("用户组名")
    private String groupName;

}
