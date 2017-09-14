package com.test.parking.core.factories;

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


    public Tariff createTariff(VehicleType type) {
        Tariff tariff = null;
        switch (type) {
            case CAR:
                tariff = new CarTariff();
                break;
            case TRUCK:
                tariff = new TruckTariff();
                break;
            case BIKE:
                tariff = new BikeTariff();
                break;
            case DISABLED:
                tariff = new DisabledTariff();
                break;
        }
        return tariff;
    }
}
