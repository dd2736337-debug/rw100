package com.vti.enums;

public enum PositionName {
    DEV("DEV"), TEST("TEST"), SCRUM_MASTER("SM"), PM("PM");

    private String value;

    PositionName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PositionName toENum(String value) {
        PositionName[] arr = PositionName.values();
        for (PositionName positionName : arr) {
            if (value.equals(positionName.getValue())) {
                return positionName;
            }
        }

        return null;
    }
}
