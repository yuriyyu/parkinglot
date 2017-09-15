package com.test.parking.core.services;

import com.test.parking.core.factories.TariffFactory;
import com.test.parking.core.models.ParkingLot;
import com.test.parking.core.models.tariffs.Tariff;
import com.test.parking.core.models.VehicleType;
import com.test.parking.core.repositories.ParkingLotRepository;
import com.test.parking.core.repositories.TariffRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Yuriy Yugay on 9/13/2017.
 *
 * @author Yuriy Yugay
 */
@Service("tariffService")
public class TariffService {

    private TariffFactory tariffFactory;

    private TariffRepository tariffRepository;
    private ParkingLotRepository parkingLotRepository;

    @Autowired
    public TariffService(TariffFactory tariffFactory,
                         TariffRepository tariffRepository,
                         ParkingLotRepository parkingLotRepository) {
        this.tariffFactory = tariffFactory;
        this.tariffRepository = tariffRepository;
        this.parkingLotRepository = parkingLotRepository;
    }

    public Tariff createTariff(VehicleType type, int parkingLotId, double price, boolean isHoliday) {
        ParkingLot parkingLot = parkingLotRepository.findOne(parkingLotId);

        Tariff tariff = tariffFactory.createTariff(type);
        tariff.setPrice(price);
        tariff.setHoliday(isHoliday);
        tariff.setParkingLot(parkingLot);

        tariffRepository.save(tariff);
        
        return tariff;
    }
    
    public List<Tariff> createTariffs(int parkingLotId) {
        ArrayList<Tariff> tariffs = new ArrayList<>();
        tariffs.add(createTariff(VehicleType.CAR, parkingLotId, 0.0, false));
        tariffs.add(createTariff(VehicleType.TRUCK, parkingLotId, 0.0, false));
        tariffs.add(createTariff(VehicleType.BIKE, parkingLotId, 0.0, false));
        tariffs.add(createTariff(VehicleType.DISABLED, parkingLotId, 0.0, false));
        
        tariffs.add(createTariff(VehicleType.CAR, parkingLotId, 0.0, true));
        tariffs.add(createTariff(VehicleType.TRUCK, parkingLotId, 0.0, true));
        tariffs.add(createTariff(VehicleType.BIKE, parkingLotId, 0.0, true));
        tariffs.add(createTariff(VehicleType.DISABLED, parkingLotId, 0.0, true));
        
        return tariffs;
    }

    public List<Tariff> getParkingLotTariffs(int parkingLotId) {
        ParkingLot parkingLot = parkingLotRepository.findOne(parkingLotId);

        return tariffRepository.findByParkingLot(parkingLot);
    }
}
