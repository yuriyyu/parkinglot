package com.test.parking.core;

import com.test.parking.core.vehicles.Vehicle;

/**
 * Created by User on 7/5/2016.
 */
public interface ParkingLot {

    int enter(Vehicle vehicle);
    boolean exit(int ticketNumber);
}