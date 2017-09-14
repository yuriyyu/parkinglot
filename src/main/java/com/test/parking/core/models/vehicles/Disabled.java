package com.test.parking.core.models.vehicles;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Yuriy Yugay on 9/14/2017.
 *
 * @author Yuriy Yugay
 */
@Entity
@DiscriminatorValue(value = "disabled")
public class Disabled
        extends Vehicle {

    public Disabled() {
        super();
    }

    @Override
    public boolean canParkInNormalSlot() {
        return false;
    }

    @Override
    public boolean canParkInLargeSlot() {
        return false;
    }
}
