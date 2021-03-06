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
@DiscriminatorValue("bike")
public class BikeTariff
        extends Tariff {

    public BikeTariff() {}

    public BikeTariff(ParkingLot parkingLot, double price, boolean isHoliday) {
        super(parkingLot, price, isHoliday);
    }
}
