package com.satori.service.enums;

/**
 * @author YanFuYou
 * @date 17/11/23 下午 09:50
 */
public enum SystemConfigNameEnum {
    LIMIT("limit"),
    MAX_PETS("maxPets"),
    ;
    public final String name;

    SystemConfigNameEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
