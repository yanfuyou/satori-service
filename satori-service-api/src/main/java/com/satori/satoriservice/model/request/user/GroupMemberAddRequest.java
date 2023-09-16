package com.satori.satoriservice.model.request.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @auth YanFuYou
 * @date 16/09/23 下午 11:03
 */
@ApiModel
public class GroupMemberAddRequest {

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("群组id")
    private Long groupId;
}
