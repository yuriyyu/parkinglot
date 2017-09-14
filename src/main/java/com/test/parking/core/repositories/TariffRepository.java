package com.test.parking.core.repositories;

import com.test.parking.core.models.ParkingLot;
import com.test.parking.core.models.tariffs.Tariff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Yuriy Yugay on 9/13/2017.
 *
 * @author Yuriy Yugay
 */
public interface TariffRepository
        extends JpaRepository<Tariff, Integer> {
    List<Tariff> findByParkingLot(ParkingLot parkingLot);
}
