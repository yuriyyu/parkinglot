package com.test.parking.core.models.vehicles;

import javax.persistence.*;

/**
 * Created by User on 7/4/2016.
 */
@Entity
@Table(name = "vehicles")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "VEHICLE_TYPE")
public abstract class Vehicle {

    @Id
    @GeneratedValue
    protected int id;

    @Column(unique = true)
    protected String vehicleNumber;

    public Vehicle() {

    }

    public abstract boolean canParkInNormalSlot();
    public abstract boolean canParkInLargeSlot();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }


}
