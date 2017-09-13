package com.test.parking.core.models.spaces;

import com.test.parking.core.models.vehicles.Vehicle;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by User on 7/4/2016.
 */
@MappedSuperclass
public abstract class ParkingSlot {
    @Id
    @GeneratedValue
    private int id;

    private boolean isOccupied;
    private String column;
    private int row;
    private Vehicle vehicle;

    public ParkingSlot(String column, int row) {
        this.column = column;
        this.row = row;
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
