package com.test.parking.core.factories;

import com.test.parking.core.models.ParkingLot;
import com.test.parking.core.models.VehicleType;
import com.test.parking.core.models.tariffs.*;
import org.springframework.stereotype.Component;

/**
 * Created by Yuriy Yugay on 9/13/2017.
 *
 * Tariff factory Method pattern creates proper tariff object according to the type
 *
 * @author Yuriy Yugay
 */
@Component("tariffFactory")
public class TariffFactory {


    public Tariff createTariff(VehicleType type, ParkingLot parkingLot, double price, boolean isHoliday) {
        Tariff tariff = null;

        switch (type) {
            case CAR:
                tariff = new CarTariff(parkingLot, price, isHoliday);
                break;
            case TRUCK:
                tariff = new TruckTariff(parkingLot, price, isHoliday);
                break;
            case BIKE:
                tariff = new BikeTariff(parkingLot, price, isHoliday);
                break;
            case DISABLED:
                tariff = new DisabledTariff(parkingLot, price, isHoliday);
                break;
        }
        return tariff;
    }
}
