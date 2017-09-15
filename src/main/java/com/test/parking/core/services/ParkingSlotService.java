/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.parking.core.services;

import com.test.parking.core.factories.ParkingSlotFactory;
import com.test.parking.core.models.ParkingLot;
import com.test.parking.core.models.VehicleType;
import com.test.parking.core.models.spaces.DisabledParkingSlot;
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

    public List<ParkingSlot> createParkingSlots(int parkingLotId,
            int carSlotsNum, int truckSlotsNum, int bikeSlotsNum, int disabledSlotsNum) {
        ArrayList<ParkingSlot> parkingSlots = new ArrayList<>();
        ParkingLot parkingLot = parkingLotRepository.findOne(parkingLotId);
        String[] alphabet = "abcdefghijklmnopqrstuvwxyz".toUpperCase().split("");
        int letterNum = 0;

        for(int i = 0; i < disabledSlotsNum; i++) {
            String letter = alphabet[letterNum];
            for(int j = 1; j < 7; j++) {
                ParkingSlot parkingSlot = slotFactory.createParkingSlot(VehicleType.DISABLED, letter, j);
                parkingSlot.setParkingLot(parkingLot);
                addParkingSlot(parkingSlot);
                parkingSlots.add(parkingSlot);
            }
            letterNum++;
        }

        for(int i = 0; i < disabledSlotsNum; i++) {
            String letter = alphabet[letterNum];
            for(int j = 1; j < 7; j++) {
                ParkingSlot parkingSlot = slotFactory.createParkingSlot(VehicleType.CAR, letter, j);
                parkingSlot.setParkingLot(parkingLot);
                addParkingSlot(parkingSlot);
                parkingSlots.add(parkingSlot);
            }
            letterNum++;
        }

        for(int i = 0; i < disabledSlotsNum; i++) {
            String letter = alphabet[letterNum];
            for(int j = 1; j < 7; j++) {
                ParkingSlot parkingSlot = slotFactory.createParkingSlot(VehicleType.TRUCK, letter, j);
                parkingSlot.setParkingLot(parkingLot);
                addParkingSlot(parkingSlot);
                parkingSlots.add(parkingSlot);
            }
            letterNum++;
        }

        for(int i = 0; i < disabledSlotsNum; i++) {
            String letter = alphabet[letterNum];
            for(int j = 1; j < 7; j++) {
                ParkingSlot parkingSlot = slotFactory.createParkingSlot(VehicleType.BIKE, letter, j);
                parkingSlot.setParkingLot(parkingLot);
                addParkingSlot(parkingSlot);
                parkingSlots.add(parkingSlot);
            }
            letterNum++;
        }

        return parkingSlots;
    }
}
