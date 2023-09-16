package com.satori.satoriservice.user.entity;

import java.io.Serializable;
import java.util.Date;
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