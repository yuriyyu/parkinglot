package com.test.parking;

import com.test.parking.core.models.GroundParkingLot;
import com.test.parking.core.models.vehicles.Car;
import com.test.parking.core.models.vehicles.Truck;
import com.test.parking.core.models.vehicles.Vehicle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by User on 7/5/2016.
 */
public class GroundParkingLotTest {

    GroundParkingLot parkingLot;

    @Before
    public void setUp() throws Exception {
        parkingLot = new GroundParkingLot(1, "Washington", 1, 2);
    }

    @After
    public void tearDown() throws Exception {
        parkingLot = null;
    }

    @Test
    public void success_parking () {
        List<Integer> occupiedSlots = new ArrayList<>();

        Vehicle vehicle1 = new Car();
        int slotNum1 = parkingLot.enter(vehicle1);
        occupiedSlots.add(slotNum1);

        Vehicle vehicle2 = new Car();
        int slotNum2 = parkingLot.enter(vehicle2);
        occupiedSlots.add(slotNum2);

        Vehicle vehicle3 = new Truck();
        int slotNum3 = parkingLot.enter(vehicle3);
        occupiedSlots.add(slotNum3);

        assertEquals(3, occupiedSlots.size());
    }

    @Test
    public void success_exit () {
        Vehicle vehicle = new Car();
        int slotNum = parkingLot.enter(vehicle);

        boolean isSuccess = parkingLot.exit(slotNum);
        assertEquals(true, isSuccess);
    }
}