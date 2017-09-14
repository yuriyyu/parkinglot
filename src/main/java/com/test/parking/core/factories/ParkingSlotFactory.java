/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.parking.core.factories;

import com.test.parking.core.models.VehicleType;
import com.test.parking.core.models.spaces.CarParkingSlot;
import com.test.parking.core.models.spaces.DisabledParkingSlot;
import com.test.parking.core.models.spaces.MotorbikeParkingSlot;
import com.test.parking.core.models.spaces.ParkingSlot;
import com.test.parking.core.models.spaces.TruckParkingSlot;
import org.springframework.stereotype.Component;

/**
 *
 * @author 986056
 */
@Component("slotFactory")
public class ParkingSlotFactory {


    public ParkingSlot createParkingSlot(VehicleType type, String column, int row) {
        ParkingSlot slot = null;
        switch (type) {
            case CAR:
                slot = new CarParkingSlot(column, row);
                break;
            case TRUCK:
                slot = new TruckParkingSlot(column, row);
                break;
            case BIKE:
                slot = new MotorbikeParkingSlot(column, row);
                break;
            case DISABLED:
                slot = new DisabledParkingSlot(column, row);
                break;
        }
        return slot;
    }
}
