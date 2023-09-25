package com.satori.satoriservice.user.entity;

import java.io.Serializable;
import java.util.Date;

import com.satori.satoriservice.enums.UserFriendStatusEnum;
import lombok.Data;

/**
 * 好友列表
 * @TableName user_friend_rel
 */
@Data
public class UserFriendRel implements Serializable {
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 好友id
     */
    private Long userFriendId;

    /**
     * 好友备注
     */
    private String remark;

    /**
     * 当前状态
     */
    private UserFriendStatusEnum status;

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