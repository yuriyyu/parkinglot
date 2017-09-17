package com.test.parking.core.models.spaces;

import com.test.parking.core.models.ParkingLot;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Yuriy Yugay on 9/13/2017.
 *
 * @author Yuriy Yugay
 */
@Entity
@DiscriminatorValue("bike")
public class MotorbikeParkingSlot
        extends ParkingSlot {

    public MotorbikeParkingSlot() {
        super();
    }

    public MotorbikeParkingSlot(ParkingLot parkingLot, String column, int row) {
        super(parkingLot, column, row);
    }
}
