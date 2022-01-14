package com.example.egedemirbas.Dept.Enum;

public enum EnumDeptType {

    NORMAL("NORMAL_DEPT"),
    LATE("LATE_FEE");

    private String type;

    EnumDeptType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type;
    }
}
