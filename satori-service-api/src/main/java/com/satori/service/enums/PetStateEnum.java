package com.satori.service.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author YanFuYou
 * @date 17/11/23 下午 10:09
 */
public enum PetStateEnum {
    NORMAL(1,"正常"),
    DISABLE(2,"禁用"),
    ;

    @EnumValue
    @JsonValue
    public final Integer value;

    public final String desc;

    PetStateEnum(Integer value, String desc) {
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
