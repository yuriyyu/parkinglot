/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.parking.core.services;

import com.test.parking.core.factories.ParkingSlotFactory;
import com.test.parking.core.models.ParkingLot;
import com.test.parking.core.models.spaces.ParkingSlot;
import com.test.parking.core.repositories.ParkingLotRepository;
import com.test.parking.core.repositories.ParkingSlotRepository;
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
    private ParkingLotRepository parkingLotRepository;

    @Autowired
    public ParkingSlotService(ParkingSlotFactory slotFactory,
                              ParkingSlotRepository parkingSlotRepository,
                              ParkingLotRepository parkingLotRepository) {
        this.slotFactory = slotFactory;
        this.parkingSlotRepository = parkingSlotRepository;
        this.parkingLotRepository = parkingLotRepository;
    }
    
    public List<ParkingSlot> getParkingSlots(int parkingLotId) {
        ParkingLot parkingLot = parkingLotRepository.findOne(parkingLotId);
        return parkingSlotRepository.findByParkingLot(parkingLot);
    }
    
    public ParkingSlot getParkingSlot(Integer id) {
        return parkingSlotRepository.findOne(id);
    }
    
    public void addParkingSlot(ParkingSlot parkingSlot) {
        parkingSlotRepository.save(parkingSlot);
    }

    public void createParkingSlots(int parkingLotId,
            int carSlotsNum, int truckSlotsNum, int bikeSlotsNum, int disabledSlotsNum) {
        ParkingLot parkingLot = parkingLotRepository.findOne(parkingLotId);
        String[] alphabet = "abcdefghijklmnopqrstuvwxyz".toUpperCase().split("");
    }
}
