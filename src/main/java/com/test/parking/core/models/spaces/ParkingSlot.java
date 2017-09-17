package com.test.parking.core.models.spaces;

import com.test.parking.core.models.ParkingLot;
import com.test.parking.core.models.VehicleType;
import com.test.parking.core.models.reservations.Registration;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

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

    @Column(name="SLOT_TYPE", updatable = false, insertable = false)
    protected String type;

    @LazyToOne(LazyToOneOption.FALSE)
    @OneToOne(mappedBy = "parkingSlot")
    protected Registration registration;

    @ManyToOne()
    @JoinColumn(name = "parking_id")
    protected ParkingLot parkingLot;

    protected String columnLetter;

    protected int row;

    public ParkingSlot(){}

    public ParkingSlot(ParkingLot parkingLot, String column, int row) {
        this.parkingLot = parkingLot;
        this.columnLetter = column;
        this.row = row;
    }

    @Transient
    public boolean isOccupied() {
        return registration != null;
    }

    @Transient
    public String getOccupiedVehicleNumber() {
        if(registration == null) {
            return "";
        }
        return registration.getVehicleNumber();
    }

    public VehicleType getType() {
        return VehicleType.fromValue(type);
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public String getColumn() {
        return columnLetter;
    }

    @Transient
    public String getSlotName() {
        return columnLetter+row;
    }

    @Transient
    public int getRowIndex() {
        return row - 1;
    }

    @Transient
    public int getColumnIndex() {
        if(columnLetter == null) {
            return -1;
        }
        int index;
        switch(columnLetter) {
            case "A":
                index = 0;
                break;
            case "B":
                index = 1;
                break;
            case "C":
                index = 2;
                break;
            case "D":
                index = 3;
                break;
            case "E":
                index = 4;
                break;
            default:
                index = -1;
        }
        return index;
    }

    public void setColumn(String column) {
        this.columnLetter = column;
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
        sb.append(", columnLetter='").append(columnLetter).append('\'');
        sb.append(", row=").append(row);
        sb.append('}');
        return sb.toString();
    }
}
