package com.satori.satoriservice.enums;

/**
 * @auth YanFuYou
 * @date 17/09/23 上午 12:22
 */
public enum SendTypeEnum {
    TO_USER((byte)1,"user2user"),
    TO_GROUP((byte)2,"user2group"),
    ;


    private Byte value;
    private String desc;

    SendTypeEnum(Byte value, String desc) {
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
