package com.satori.service.model.request.user;

import com.satori.model.enums.YesOrNoEnum;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @auth YanFuYou
 * @date 16/09/23 下午 10:55
 */

@Data
public class UserGroupRequest {

    @ApiModelProperty("组id,更新时使用")
    private Long id;

    @ApiModelProperty("创建者id")
    @NotNull(message = "创建者不能为空")
    private Long createUserId;

    @ApiModelProperty("用户组名")
    @NotBlank(message = "群组名不能为空")
    private String groupName;

    @ApiModelProperty("是否需要审核")
    private YesOrNoEnum process;

    @ApiModelProperty("类型")
    @NotNull(message = "类型不能为空")
    private Byte type;

    @ApiModelProperty("简介")
    private String description;
}
