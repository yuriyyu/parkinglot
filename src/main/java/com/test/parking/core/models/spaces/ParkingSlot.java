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

    private String col;

    private int row;

    public ParkingSlot(){}

    public ParkingSlot(String column, int row) {
        this.col = column;
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
        return col;
    }

    public void setColumn(String column) {
        this.col = column;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ParkingSlot{");
        sb.append("id=").append(id);
        sb.append(", col='").append(col).append('\'');
        sb.append(", row=").append(row);
        sb.append('}');
        return sb.toString();
    }
}
