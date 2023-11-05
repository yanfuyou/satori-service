package com.satori.service.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author YanFuYou
 * @date 05/11/23 下午 09:43
 */

@Data
public class UserTodoModel implements Serializable {
    private Long id;

    /**
     * 创建者
     */
    @NotNull(message = "用户id不能为空")
    private Long createUserId;

    /**
     * 代办内容
     */
    @NotBlank(message = "待办内容不能为空")
    private String content;

    /**
     * 状态 0-初始化,1-已解决
     */
    private Integer state;

    /**
     * 是否删除
     */
    private Integer deleted;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
