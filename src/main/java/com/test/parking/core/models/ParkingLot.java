package com.test.parking.core.models;

import com.test.parking.core.models.spaces.ParkingSlot;
import com.test.parking.core.models.tariffs.Tariff;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
    protected int id;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.ALL, orphanRemoval = true)
    protected List<ParkingSlot> parkingSlots = new ArrayList<>();

    protected int size;

    protected String address;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.ALL, orphanRemoval = true)
    protected List<Tariff> tariffs = new ArrayList<>();

    public ParkingLot() {

    }

    public List<ParkingSlot> getParkingSlots() {
        return parkingSlots;
    }

    public List<Tariff> getTariffs() {
        return tariffs;
    }

    public void setParkingSlots(List<ParkingSlot> parkingSlots) {
        this.parkingSlots.clear();
        this.size = 0;

        if(parkingSlots != null) {
            this.size = parkingSlots.size();
            this.parkingSlots.addAll(parkingSlots);
        }
    }
    
    public void setTariffs(List<Tariff> tariffs) {
        this.tariffs.clear();

        if(tariffs != null) {
            this.tariffs.addAll(tariffs);
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ParkingLot{");
        sb.append("id=").append(id);
        sb.append(", size=").append(size);
        sb.append('}');
        return sb.toString();
    }
}
