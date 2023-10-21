package com.satori.service.model.request.user;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @auth YanFuYou
 * @date 23/09/23 下午 10:47
 */
@Data
public class UserFriendAddRequest {

    @NotNull(message = "用户id不能为空")
    private Long userId;

    @NotNull(message = "好友id不能为空")
    private Long userFriendId;
}
