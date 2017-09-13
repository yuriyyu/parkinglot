package com.test.parking.core.models.vehicles;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Yuriy Yugay on 9/13/2017.
 *
 * @author Yuriy Yugay
 */
@Entity
@DiscriminatorValue(value = "Bike")
public class Motorbike
        extends Vehicle {
    @Override
    public boolean canParkInNormalSlot() {
        return false;
    }

    @Override
    public boolean canParkInLargeSlot() {
        return false;
    }
}
