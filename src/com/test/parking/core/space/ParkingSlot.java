package com.test.parking.core.space;

import com.test.parking.core.vehicles.Vehicle;

/**
 * Created by User on 7/4/2016.
 */
public abstract class ParkingSlot {
    private boolean isOccupied;
    private int slotNumber;
    private Vehicle vehicle;

    public ParkingSlot(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public void park(Vehicle vehicle) {
        this.vehicle = vehicle;
        isOccupied = true;
    }

    public void unpark() {
        this.vehicle = null;
        isOccupied = false;
    }

    public boolean isOccupied () {
        return isOccupied;
    }

    public int getSlotNumber() {
        return slotNumber;
    }
}
