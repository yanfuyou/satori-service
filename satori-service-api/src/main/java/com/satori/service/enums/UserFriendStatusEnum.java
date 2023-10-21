package com.satori.service.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * @auth YanFuYou
 * @date 23/09/23 下午 10:42
 */
public enum UserFriendStatusEnum {
    INIT((byte)0,"申请中"),
    PASS((byte)1,"通过"),
    REJECT((byte)2,"拒绝")
    ;

    @EnumValue
    private Byte value;

    private String desc;


    UserFriendStatusEnum(Byte value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Byte getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
