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

    public static RequestStatusEnum getEnum(String value) {
        for (RequestStatusEnum equipmentInsulationEnum : RequestStatusEnum.values()) {
            if (equipmentInsulationEnum.getName().equalsIgnoreCase(value)) {
                return equipmentInsulationEnum;
            }
        }
        throw new IllegalArgumentException("Wrong value Enum");
    }
}
