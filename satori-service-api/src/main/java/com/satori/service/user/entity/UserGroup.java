package com.satori.service.user.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.satori.model.enums.YesOrNoEnum;
import lombok.Data;

/**
 * 用户组
 * @TableName user_group
 */
@Data
public class UserGroup implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 创建者id
     */
    private Long createUserId;

    /**
     * 用户组名
     */
    private String groupName;

    /**
     * 是否需要审核
     */
    private YesOrNoEnum process;

    /**
     * 类型
     */
    private Byte type;

    /**
     * 简介
     */
    private String description;

    /**
     * 
     */
    private YesOrNoEnum disable;

    /**
     * 
     */
    private YesOrNoEnum deleted;

    /**
     * 
     */
    private LocalDateTime createTime;

    /**
     * 
     */
    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}