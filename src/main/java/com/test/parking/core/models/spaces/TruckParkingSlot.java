package com.test.parking.core.models.spaces;

import com.test.parking.core.models.ParkingLot;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by User on 7/4/2016.
 */
@Entity
@DiscriminatorValue("truck")
public class TruckParkingSlot
        extends ParkingSlot {

    public TruckParkingSlot() {
        super();
    }

    public TruckParkingSlot(ParkingLot parkingLot, String column, int row) {
        super(parkingLot, column, row);
    }
}
