package com.test.parking.core.models.tariffs;

import com.test.parking.core.models.ParkingLot;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * Created by Yuriy Yugay on 9/13/2017.
 *
 * @author Yuriy Yugay
 */
@Entity
@DiscriminatorValue("normal")
public class NormalTariff
        extends Tariff {

    @OneToOne
    @JoinColumn(name = "parking_lot_id")
    protected ParkingLot parkingLot;
}
