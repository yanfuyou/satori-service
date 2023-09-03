package com.satori.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * @auth YanFuYou
 * @date 02/09/23 下午 08:59
 */
public enum YesOrNoEnum {

    YES(1,"是"),
    NO(0,"否"),
    ;

    @EnumValue
    public final Integer value;

    public final String desc;

    YesOrNoEnum(Integer value, String desc) {
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
