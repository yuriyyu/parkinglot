package com.test.parking.core.models.spaces;

import com.test.parking.core.models.ParkingLot;

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

    public CarParkingSlot(ParkingLot parkingLot, String column, int row) {
        super(parkingLot, column, row);
    }
}
