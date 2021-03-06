package com.test.parking.core.models.tariffs;

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
public class DisabledTariff
        extends Tariff {

    public DisabledTariff() {}

    public DisabledTariff(ParkingLot parkingLot, double price, boolean isHoliday) {
        super(parkingLot, price, isHoliday);
    }
}
