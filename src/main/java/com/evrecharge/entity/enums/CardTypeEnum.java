package com.evrecharge.entity.enums;

public enum CardTypeEnum {
    VISA("Visa");

    private String name;

    CardTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
