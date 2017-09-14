package com.test.parking.core.models.spaces;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by User on 7/4/2016.
 */
@Entity
@DiscriminatorValue("car")
public class CarParkingSlot
        extends ParkingSlot {

    public CarParkingSlot() {
        super();
    }

    public CarParkingSlot(String column, int row) {
        super(column, row);
    }
}
