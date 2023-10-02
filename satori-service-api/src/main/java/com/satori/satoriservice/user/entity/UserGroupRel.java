package com.satori.satoriservice.user.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * 用户组关系
 * @TableName user_group_rel
 */
@Data
public class UserGroupRel implements Serializable {
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 组id
     */
    private Long groupId;

    /**
     * 当前状态
     */
    private Byte status;

    /**
     * 
     */
    private Integer deleted;

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