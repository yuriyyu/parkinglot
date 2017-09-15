/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.parking.core.services;

import com.test.parking.core.models.GroundParkingLot;
import com.test.parking.core.models.ParkingLot;
import com.test.parking.core.repositories.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author 986056
 */
@Service("parkingLotService")
public class ParkingLotService {

    private ParkingLotRepository parkingLotRepository;

    @Autowired
    public ParkingLotService(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLotRepository.findAll();
    }

    public ParkingLot getParkingLot(Integer id) {
        return parkingLotRepository.findOne(id);
    }

    public void addParkingLot(ParkingLot parkingLot) {
        parkingLotRepository.save(parkingLot);
    }

    public ParkingLot createParkingLot(int id, String address,
                                       //6, 12, 6, 6
                                       int carSlotsNum, int truckSlotsNum, int bikeSlotsNum, int disabledSlotsNum) {
        ParkingLot parkingLot = new GroundParkingLot(id, address);
        ParkingSlotService parkingSlotService = new ParkingSlotService(null, null, null);
        parkingLot.setParkingSlots(parkingSlotService.createParkingSlots(id, carSlotsNum,
                truckSlotsNum, bikeSlotsNum, disabledSlotsNum));
        
        TariffService ts = new TariffService(null, null, parkingLotRepository);
        parkingLot.setTariffs(ts.createTariffs(id));
        
        addParkingLot(parkingLot);

        return parkingLot;
    }
}
