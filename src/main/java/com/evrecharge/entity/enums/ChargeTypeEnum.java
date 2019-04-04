package com.evrecharge.entity.enums;

import java.math.BigDecimal;

public enum ChargeTypeEnum {
    TYPE_1("Type 1") {
        @Override
        public BigDecimal getPrice(BigDecimal hours) {
            return new BigDecimal(1.5).multiply(hours);
        }
    },
    TYPE_2("Type 2") {
        @Override
        public BigDecimal getPrice(BigDecimal hours) {
            return new BigDecimal(1.9).multiply(hours);
        }
    };

    private String name;

    ChargeTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ChargeTypeEnum getEnum(String value) {
        for (ChargeTypeEnum equipmentInsulationEnum : ChargeTypeEnum.values()) {
            if (equipmentInsulationEnum.getName().equalsIgnoreCase(value)) {
                return equipmentInsulationEnum;
            }
        }
        throw new IllegalArgumentException("Wrong value Enum");
    }

    public abstract BigDecimal getPrice(BigDecimal hours);
}
