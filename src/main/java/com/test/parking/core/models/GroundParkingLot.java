package com.test.parking.core.models;

import com.test.parking.core.models.vehicles.Vehicle;

import javax.persistence.*;

/**
 * Created by User on 7/4/2016.
 */
@Entity
@DiscriminatorValue("ground")
public class GroundParkingLot
        extends ParkingLot {


    public GroundParkingLot() {
    }

//
//    // enter parking lot
//    @Override
//    public int enter (Vehicle vehicle) {
//        if(vehicle == null) {
//            throw new NullPointerException();
//        }
//
//        ParkingSlot slot = null;
////        // Finding appropriate slot for the vehicle
////        if(vehicle.canParkInNormalSlot()) {
////            slot = getFreeSlot(regularSlots);
////        }
////
////        if(slot == null && vehicle.canParkInLargeSlot()) {
////            slot = getFreeSlot(truckSlots);
////        }
////
////        if(slot == null) {
////            return 0;
////        }
//        return register(slot, vehicle);
//    }
//
//    // exit from ground parking
//    @Override
//    public boolean exit (int ticketNumber) {
//        if(ticketNumber <= 0) {
//            throw new IllegalArgumentException("ticket number <= 0");
//        }
////        ParkingSlot slot = occupiedSpaceMap.remove(ticketNumber);
////        if(slot == null) {
////            return false;
////        }
////
////        slot.unpark();
//        return true;
//    }
//
//    // get free slot from specific slots array
//    private ParkingSlot getFreeSlot(List<ParkingSlot> slots) {
//        for (ParkingSlot slot : slots) {
//            if(!slot.isOccupied()) {
//                return slot;
//            }
//        }
//        return null;
//    }
//
//    // register and return slot number
//    private int register (ParkingSlot slot, Vehicle vehicle) {
////        int slotNum = slot.getSlotNumber();
////        slot.park(vehicle);
////        occupiedSpaceMap.put(slotNum, slot);
////        return slot.getSlotNumber();
//        return -1;
//    }
//
    
    
}
