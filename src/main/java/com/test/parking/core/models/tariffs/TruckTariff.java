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
@DiscriminatorValue("truck")
public class TruckTariff
        extends Tariff {

    public TruckTariff() {}

    public TruckTariff(ParkingLot parkingLot, double price, boolean isHoliday) {
        super(parkingLot, price, isHoliday);
    }
}
