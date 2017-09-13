package com.test.parking.core.repositories;

import com.test.parking.core.models.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Yuriy Yugay on 9/13/2017.
 *
 * @author Yuriy Yugay
 */
public interface ParkingLotRepository
        extends JpaRepository<ParkingLot, Integer> {
}
