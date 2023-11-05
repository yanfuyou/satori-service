package com.satori.service.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * @author YanFuYou
 * @date 05/11/23 下午 01:39
 */

@Data
public class UserExtModel implements Serializable {

    /**
     *
     */
    private Long id;

    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空")
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
}
