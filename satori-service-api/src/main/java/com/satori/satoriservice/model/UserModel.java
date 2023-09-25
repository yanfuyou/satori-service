package com.satori.satoriservice.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @auth YanFuYou
 * @date 23/09/23 下午 11:01
 */
@Data
public class UserModel {

    private Long id;

    private String nikeName;

    private String userEmail;

    private String userAvatar;

    private LocalDateTime createTime;
}