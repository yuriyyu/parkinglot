package com.test.parking.core.vehicles;

/**
 * Created by User on 7/4/2016.
 */
public class Car
        implements Vehicle {

    @Override
    public boolean canParkInNormalSlot() {
        return true;
    }

    @Override
    public boolean canParkInLargeSlot() {
        return true;
    }
}
