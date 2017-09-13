/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.parking.core.services;

import com.test.parking.core.models.spaces.ParkingSlot;
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
    
    private ParkingSlotRepository parkingSlotRepository;
    
    @Autowired
    public ParkingSlotService(ParkingSlotRepository parkingSlotRepository) {
        this.parkingSlotRepository = parkingSlotRepository;
    }
    
    public List<ParkingSlot> getParkingSlots() {
        return parkingSlotRepository.findAll();
    }
    
    public ParkingSlot getParkingSlot(Integer id) {
        return parkingSlotRepository.findOne(id);
    }
    
    public void addParkingSlot(ParkingSlot parkingSlot) {
        parkingSlotRepository.save(parkingSlot);
    }
}
