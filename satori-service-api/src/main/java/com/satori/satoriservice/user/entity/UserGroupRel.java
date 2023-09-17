package com.satori.satoriservice.user.entity;

import java.io.Serializable;
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
     * 
     */
    private Integer deleted;

    private static final long serialVersionUID = 1L;
}