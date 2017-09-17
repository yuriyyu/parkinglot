package com.test.parking.core.services;

import com.test.parking.core.factories.TariffFactory;
import com.test.parking.core.models.ParkingLot;
import com.test.parking.core.models.calendars.HolidayCalendar;
import com.test.parking.core.models.tariffs.Tariff;
import com.test.parking.core.models.VehicleType;
import com.test.parking.core.repositories.ParkingLotRepository;
import com.test.parking.core.repositories.TariffRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public Map<VehicleType, Tariff> getCurrentParkingLotTariffs(int parkingLotId) {
        ParkingLot parkingLot = parkingLotRepository.findOne(parkingLotId);
        boolean isHoliday = HolidayCalendar.isHoliday(LocalDateTime.now());

        return tariffRepository.findByParkingLotAndHoliday(parkingLot, isHoliday)
                .stream()
                .collect(Collectors.toMap(Tariff::getType,tariff -> tariff));
    }

    public List<Tariff> getParkingLotTariffs(int parkingLotId) {
        ParkingLot parkingLot = parkingLotRepository.findOne(parkingLotId);

        return tariffRepository.findByParkingLot(parkingLot);
    }

    public List<Tariff> createStubTariffs(ParkingLot parkingLot) {
        ArrayList<Tariff> tariffs = new ArrayList<>();
        // normal tariffs
        tariffs.add(tariffFactory.createTariff(VehicleType.CAR, parkingLot, 20, false));
        tariffs.add(tariffFactory.createTariff(VehicleType.TRUCK, parkingLot, 30, false));
        tariffs.add(tariffFactory.createTariff(VehicleType.BIKE, parkingLot, 10, false));
        tariffs.add(tariffFactory.createTariff(VehicleType.DISABLED, parkingLot, 5, false));
        // holiday tariffs
        tariffs.add(tariffFactory.createTariff(VehicleType.CAR, parkingLot, 40, true));
        tariffs.add(tariffFactory.createTariff(VehicleType.TRUCK, parkingLot, 60, true));
        tariffs.add(tariffFactory.createTariff(VehicleType.BIKE, parkingLot, 20, true));
        tariffs.add(tariffFactory.createTariff(VehicleType.DISABLED, parkingLot, 10, true));

        return tariffRepository.save(tariffs);
    }
}
