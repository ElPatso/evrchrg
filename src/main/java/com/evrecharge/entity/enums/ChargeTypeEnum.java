package com.evrecharge.entity.enums;

public enum ChargeTypeEnum {
    TYPE_1("Type 1"),
    TYPE_2("Type 2");

    private String name;

    ChargeTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
