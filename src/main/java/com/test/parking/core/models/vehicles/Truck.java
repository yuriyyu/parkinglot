package com.test.parking.core.models.vehicles;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by User on 7/4/2016.
 */
@Entity
@DiscriminatorValue(value = "truck")
public class Truck
        extends Vehicle {

    public Truck() {
        super();
    }

    @Override
    public boolean canParkInNormalSlot() {
        return false;
    }

    @Override
    public boolean canParkInLargeSlot() {
        return true;
    }
}
