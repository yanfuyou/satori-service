package com.satori.satoriservice.enums;

/**
 * @auth YanFuYou
 * @date 17/09/23 上午 12:22
 */
public enum SendTypeEnum {
    TO_USER((byte)1,"user2user"),
    TO_GROUP((byte)2,"user2group"),
    ;


    private Byte val;
    private String desc;

    SendTypeEnum(Byte val, String desc) {
        this.val = val;
        this.desc = desc;
    }


    public Byte getVal() {
        return val;
    }

    public String getDesc() {
        return desc;
    }
}
