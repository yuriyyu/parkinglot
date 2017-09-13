package com.test.parking.core.models;

import com.test.parking.core.models.spaces.ParkingSlot;
import com.test.parking.core.models.vehicles.Vehicle;

import javax.persistence.*;
import java.util.List;

/**
 * Created by User on 7/5/2016.
 */
@Entity
@Table(name = "parking_lots")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "PARKING_TYPE")
public abstract class ParkingLot {

    @Id
    @GeneratedValue
    private int id;

//    protected List<ParkingSlot> parkingSlots;

    abstract int enter(Vehicle vehicle);
    abstract boolean exit(int ticketNumber);

//    public List<ParkingSlot> getParkingSlots() {
//        return parkingSlots;
//    }

    public void setParkingSlots(List<ParkingSlot> parkingSlots) {
//        this.parkingSlots = parkingSlots;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
