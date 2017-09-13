package com.test.parking.core.models.spaces;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by User on 7/4/2016.
 */
@Entity
@DiscriminatorValue("truck")
public class TruckParkingSlot
        extends ParkingSlot {

    public TruckParkingSlot(String column, int row) {
        super(column, row);
    }
}
