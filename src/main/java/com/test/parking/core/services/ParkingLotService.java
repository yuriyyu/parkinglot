/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.parking.core.services;

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
}
