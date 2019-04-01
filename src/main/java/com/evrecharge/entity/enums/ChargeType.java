package com.evrecharge.entity.enums;

public enum ChargeType {
    TYPE_1("Type 1"),
    TYPE_2("Type 2");

    private String name;

    ChargeType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
