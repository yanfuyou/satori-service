package com.satori.satoriservice.model.request.user;

import lombok.Data;

/**
 * @auth YanFuYou
 * @date 03/09/23 下午 10:21
 */
@Data
public class UserSignRequest {
    private String userName;

    private String nikeName;

    private String password;

    private String validationCode;

}
