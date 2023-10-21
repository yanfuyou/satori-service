package com.satori.service.enums;

/**
 * @author YanFuYou
 * @date 02/10/23 上午 12:31
 */

public enum LockKeyEnum {
    CREATE_GROUP("creat-group","创建群"),
    ;
    private String name;

    private String desc;


    LockKeyEnum(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
