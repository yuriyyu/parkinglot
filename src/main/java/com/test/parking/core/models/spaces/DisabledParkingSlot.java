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
@DiscriminatorValue("disabled")
public class DisabledParkingSlot
        extends ParkingSlot {

    public DisabledParkingSlot() {
        super();
    }

    public DisabledParkingSlot(ParkingLot parkingLot, String column, int row) {
        super(parkingLot, column, row);
    }
}
