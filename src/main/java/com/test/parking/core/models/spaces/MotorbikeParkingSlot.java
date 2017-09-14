package com.test.parking.core.models.spaces;

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

    public MotorbikeParkingSlot(String column, int row) {
        super(column, row);
    }
}
