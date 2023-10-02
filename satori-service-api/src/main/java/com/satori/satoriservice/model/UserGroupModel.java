package com.satori.satoriservice.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author YanFuYou
 * @date 01/10/23 上午 04:23
 */

@Data
public class UserGroupModel {

    private Long id;

    private String createUserId;

    private String groupName;

    private Byte process;

    private Byte type;

    private String description;

    private LocalDateTime createTime;

    private LocalDateTime joinTime;
}
