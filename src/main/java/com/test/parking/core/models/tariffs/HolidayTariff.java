package com.test.parking.core.models.tariffs;

import com.test.parking.core.models.ParkingLot;

import javax.persistence.*;

/**
 * Created by Yuriy Yugay on 9/13/2017.
 *
 * @author Yuriy Yugay
 */
@Entity
@DiscriminatorValue("holiday")
public class HolidayTariff
        extends Tariff {

    @OneToOne
    @JoinColumn(name = "parking_lot_id")
    protected ParkingLot parkingLot;
}