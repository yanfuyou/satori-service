package com.satori.service.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.satori.model.enums.YesOrNoEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author yanfuyou
 * @since 2023-09-02 12:06:30
 */

@Data
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("自增主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("昵称")
    private String nikeName;

    @ApiModelProperty("密码")
    private String userPassword;

    @ApiModelProperty("盐值")
    private String userSalt;

    @ApiModelProperty("头像")
    private String userAvatar;

    @ApiModelProperty("删除标记")
    private YesOrNoEnum deleted;

    @ApiModelProperty("禁用")
    private YesOrNoEnum disable;

    @ApiModelProperty("禁用结束时间")
    private LocalDateTime disableEndTime;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("用户邮箱")
    private String userEmail;

}
