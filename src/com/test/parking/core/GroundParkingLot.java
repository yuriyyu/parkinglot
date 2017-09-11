package com.test.parking.core;

import com.test.parking.core.space.ParkingSlot;
import com.test.parking.core.space.RegularParkingSlot;
import com.test.parking.core.space.TruckParkingSlot;
import com.test.parking.core.vehicles.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by User on 7/4/2016.
 */
public class GroundParkingLot
        implements ParkingLot {

    private final Map<Integer, ParkingSlot> occupiedSpaceMap;
    private final List<ParkingSlot> regularSlots;
    private final List<ParkingSlot> truckSlots;
    private final int regularSpaces;
    private final int truckSpaces;

    public GroundParkingLot(int regularSpaces, int truckSpaces) {
        if(regularSpaces < 0) {
            throw new IllegalArgumentException("Regular size < 0");
        }
        if(truckSpaces < 0) {
            throw new IllegalArgumentException("Truck size < 0");
        }
        this.regularSpaces = regularSpaces;
        this.truckSpaces = truckSpaces;

        this.occupiedSpaceMap = new HashMap<>(regularSpaces + truckSpaces);
        this.regularSlots = new ArrayList<>(regularSpaces);
        this.truckSlots = new ArrayList<>(truckSpaces);

        createParkingSlots();
    }

    // enter parking lot
    @Override
    public int enter (Vehicle vehicle) {
        if(vehicle == null) {
            throw new NullPointerException();
        }

        ParkingSlot slot = null;
        // Finding appropriate slot for the vehicle
        if(vehicle.canParkInNormalSlot()) {
            slot = getFreeSlot(regularSlots);
        }

        if(slot == null && vehicle.canParkInLargeSlot()) {
            slot = getFreeSlot(truckSlots);
        }

        if(slot == null) {
            return 0;
        }
        return register(slot, vehicle);
    }

    // exit from ground parking
    @Override
    public boolean exit (int ticketNumber) {
        if(ticketNumber <= 0) {
            throw new IllegalArgumentException("ticket number <= 0");
        }
        ParkingSlot slot = occupiedSpaceMap.remove(ticketNumber);
        if(slot == null) {
            return false;
        }

        slot.unpark();
        return true;
    }

    // creating parking spaces
    private void createParkingSlots() {
        int slotNumber = 1;
        for(int x = 1; x <= regularSpaces; x++) {
            regularSlots.add(new RegularParkingSlot(slotNumber++));
        }

        for (int x = 1; x <= truckSpaces; x++) {
            truckSlots.add(new TruckParkingSlot(slotNumber++));
        }
    }

    // get free slot from specific slots array
    private ParkingSlot getFreeSlot(List<ParkingSlot> slots) {
        for (ParkingSlot slot : slots) {
            if(!slot.isOccupied()) {
                return slot;
            }
        }
        return null;
    }

    // register and return slot number
    private int register (ParkingSlot slot, Vehicle vehicle) {
        int slotNum = slot.getSlotNumber();
        slot.park(vehicle);
        occupiedSpaceMap.put(slotNum, slot);
        return slot.getSlotNumber();
    }
}
