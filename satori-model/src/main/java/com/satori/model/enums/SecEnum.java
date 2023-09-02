package com.satori.model.enums;

/**
 * @auth YanFuYou
 * @date 02/09/23 下午 09:02
 */
public enum SecEnum {
    MAM(0,"男"),
    WOMAN(1,"女"),
    UNKNOW(2,"未知"),
    ;

    public final Integer value;

    public final String desc;

    SecEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
