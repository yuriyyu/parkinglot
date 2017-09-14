package com.test.parking.core.models.tariffs;

import com.test.parking.core.models.ParkingLot;
import com.test.parking.core.models.vehicles.Vehicle;

import javax.persistence.*;

/**
 * Created by Yuriy Yugay on 9/13/2017.
 *
 * @author Yuriy Yugay
 */
@Entity
@Table(name = "tariffs")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "VEHICLE_TYPE")
public abstract class Tariff {

    @Id
    @GeneratedValue
    protected int id;

    protected boolean isHoliday;

    @OneToOne
    @JoinColumn(name = "parking_lot_id")
    protected ParkingLot parkingLot;

    protected double price;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isHoliday() {
        return isHoliday;
    }

    public void setHoliday(boolean holiday) {
        isHoliday = holiday;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }
}
