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
@DiscriminatorValue("car")
public class CarTariff
        extends Tariff {

    public CarTariff() {}

    public CarTariff(ParkingLot parkingLot, double price, boolean isHoliday) {
        super(parkingLot, price, isHoliday);
    }
}
