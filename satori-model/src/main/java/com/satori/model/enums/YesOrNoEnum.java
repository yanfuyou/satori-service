package com.satori.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @auth YanFuYou
 * @date 02/09/23 下午 08:59
 */
public enum YesOrNoEnum {

    YES(1,"是"),
    NO(0,"否"),
    ;

    @EnumValue
    @JsonValue
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


    public static YesOrNoEnum of(Integer value){
        for (YesOrNoEnum item : values()) {
            if (value.equals(item.value)){
                return item;
            }
        }
        return null;
    }
}
