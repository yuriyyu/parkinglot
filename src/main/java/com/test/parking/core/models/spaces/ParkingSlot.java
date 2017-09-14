package com.test.parking.core.models.spaces;

import com.test.parking.core.models.ParkingLot;
import com.test.parking.core.models.reservations.Registration;

import javax.persistence.*;

/**
 * Created by User on 7/4/2016.
 */
@Entity
@Table(name = "parking_slot")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "SLOT_TYPE")
public abstract class ParkingSlot {
    @Id
    @GeneratedValue
    protected int id;

//    @OneToOne(mappedBy = "parkingSlot",cascade = CascadeType.ALL)
//    protected Registration registration;

    @ManyToOne()
    @JoinColumn(name = "parking_id")
    protected ParkingLot parkingLot;

    private String column;

    private int row;

    public ParkingSlot(String column, int row) {
        this.column = column;
        this.row = row;
    }

//    public Registration getRegistration() {
//        return registration;
//    }
//
//    public void setRegistration(Registration registration) {
//        this.registration = registration;
//    }
//
    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
