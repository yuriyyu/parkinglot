package com.test.parking.core.models.vehicles;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by User on 7/4/2016.
 */
@Entity
@DiscriminatorValue(value = "Car")
public class Car
        extends Vehicle {

    @Override
    public boolean canParkInNormalSlot() {
        return true;
    }

    @Override
    public boolean canParkInLargeSlot() {
        return true;
    }
}
