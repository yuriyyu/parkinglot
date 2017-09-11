package com.test.parking.core.vehicles;

/**
 * Created by User on 7/4/2016.
 */
public class Truck
        implements Vehicle {

    @Override
    public boolean canParkInNormalSlot() {
        return false;
    }

    @Override
    public boolean canParkInLargeSlot() {
        return true;
    }
}
