package com.satori.service.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @auth YanFuYou
 * @date 23/09/23 下午 11:01
 */
@Data
public class UserModel {

    private Long id;

    private String userName;

    private String nikeName;

    private String userEmail;

    private String userAvatar;

    private LocalDateTime createTime;
}
