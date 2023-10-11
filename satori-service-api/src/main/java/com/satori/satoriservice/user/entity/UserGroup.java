package com.satori.satoriservice.user.entity;

import java.io.Serializable;
import java.util.Date;

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
    private Byte process;

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
    private Integer disable;

    /**
     * 
     */
    private Integer deleted;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}