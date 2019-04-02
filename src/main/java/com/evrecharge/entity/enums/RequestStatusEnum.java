package com.evrecharge.entity.enums;

public enum RequestStatusEnum {
    PENDING("Pending"),
    APPROVED("Approved"),
    REFUSED("Refused");

    private String name;

    RequestStatusEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
