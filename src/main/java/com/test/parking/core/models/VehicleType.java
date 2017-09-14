package com.test.parking.core.models;

/**
 * Created by Yuriy Yugay on 9/14/2017.
 *
 * @author Yuriy Yugay
 */
public enum VehicleType {
    CAR("car"),
    TRUCK("truck"),
    BIKE("bike"),
    DISABLED("disabled");

    private final String value;

    VehicleType(String value) {
        this.value = value;
    }

    public static VehicleType fromValue(String key) {
        if(key != null) {
            for(VehicleType type : values()) {
                if(type.value.equalsIgnoreCase(key))
                    return type;
            }
        }
        return null;
    }

    public String toValue() {
        return value;
    }
}
