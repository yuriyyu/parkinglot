package com.test.parking.core.models.spaces;

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

    public DisabledParkingSlot(String column, int row) {
        super(column, row);
    }
}
