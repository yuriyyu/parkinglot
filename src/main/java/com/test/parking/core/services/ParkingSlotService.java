/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.parking.core.services;

import com.test.parking.core.factories.ParkingSlotFactory;
import com.test.parking.core.models.ParkingLot;
import com.test.parking.core.models.VehicleType;
import com.test.parking.core.models.spaces.ParkingSlot;
import com.test.parking.core.repositories.ParkingLotRepository;
import com.test.parking.core.repositories.ParkingSlotRepository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 986056
 */
@Service("parkingSlotService")
public class ParkingSlotService {

    private ParkingSlotFactory slotFactory;

    private ParkingSlotRepository parkingSlotRepository;

    @Autowired
    public ParkingSlotService(ParkingSlotFactory slotFactory,
                              ParkingSlotRepository parkingSlotRepository) {
        this.slotFactory = slotFactory;
        this.parkingSlotRepository = parkingSlotRepository;
    }
    
    public List<ParkingSlot> createStubSlots(ParkingLot parkingLot) {
        ArrayList<ParkingSlot> parkingSlots = new ArrayList<>();

        for(int j = 1; j < 7; j++) {
            // create disabled slot
            ParkingSlot parkingSlotA = slotFactory.createParkingSlot(parkingLot, VehicleType.DISABLED, "A", j);
            parkingSlots.add(parkingSlotA);
            // create car slot
            ParkingSlot parkingSlotB = slotFactory.createParkingSlot(parkingLot, VehicleType.CAR, "B", j);
            parkingSlots.add(parkingSlotB);
            ParkingSlot parkingSlotC = slotFactory.createParkingSlot(parkingLot, VehicleType.CAR, "C", j);
            parkingSlots.add(parkingSlotC);
            // create truck slot
            ParkingSlot parkingSlotD = slotFactory.createParkingSlot(parkingLot, VehicleType.TRUCK, "D", j);
            parkingSlots.add(parkingSlotD);
            // create bike slot
            ParkingSlot parkingSlotE = slotFactory.createParkingSlot(parkingLot, VehicleType.BIKE, "E", j);
            parkingSlots.add(parkingSlotE);
        }
        return parkingSlotRepository.save(parkingSlots);
    }
}
