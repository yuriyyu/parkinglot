package com.test.parking.core.models;

import com.test.parking.core.models.spaces.ParkingSlot;
import com.test.parking.core.models.tariffs.Tariff;

import javax.persistence.*;
import java.util.ArrayList;
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

    @OneToMany(mappedBy = "parkingLot",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ParkingSlot> parkingSlots;

    private int size;

    @OneToMany(mappedBy = "parkingLot",cascade = CascadeType.ALL, orphanRemoval = true)
    List<Tariff> tariffs;

    public ParkingLot(int id, int size) {
        this.id = id;
        this.size = size;
        this.parkingSlots = new ArrayList<>(size);
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<ParkingSlot> getParkingSlots() {
        return parkingSlots;
    }

    public void setParkingSlots(List<ParkingSlot> parkingSlots) {
        this.parkingSlots.clear();
        this.size = 0;

        if(parkingSlots != null) {
            this.size = parkingSlots.size();
            this.parkingSlots.addAll(parkingSlots);
        }
    }

    public int getSize() {
        return size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
