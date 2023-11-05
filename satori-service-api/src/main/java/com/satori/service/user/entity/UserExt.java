package com.satori.service.user.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 用户扩展信息
 * @TableName user_ext
 */
@Data
public class UserExt implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 自我据介绍
     */
    private String introduction;

    /**
     * 今日一言
     */
    private String oneWords;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}