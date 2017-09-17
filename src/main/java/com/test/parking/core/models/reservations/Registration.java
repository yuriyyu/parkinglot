package com.test.parking.core.models.reservations;

import com.test.parking.core.models.spaces.ParkingSlot;
import com.test.parking.core.models.tariffs.Tariff;
import com.test.parking.core.models.tickets.Ticket;
import com.test.parking.core.models.vehicles.Vehicle;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Yuriy Yugay on 9/13/2017.
 *
 * @author Yuriy Yugay
 */
@Entity
@Table(name = "registration")
public class Registration {
    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    @JoinColumn(name = "parking_slot_id")
    private ParkingSlot parkingSlot;

//    private Tariff tariff;

    private int time;

    private LocalDateTime createDate;

    private LocalDateTime fromDate;

    private LocalDateTime toDate;

    private String vehicleNumber;

    public Registration() {

    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDateTime getToDate() {
        return toDate;
    }

    public void setToDate(LocalDateTime toDate) {
        this.toDate = toDate;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ParkingSlot getParkingSlot() {
        return parkingSlot;
    }

    public void setParkingSlot(ParkingSlot parkingSlot) {
        this.parkingSlot = parkingSlot;
    }

//    public Tariff getTariff() {
//        return tariff;
//    }
//
//    public void setTariff(Tariff tariff) {
//        this.tariff = tariff;
//    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Registration{");
        sb.append("id=").append(id);
        sb.append(", parkingSlot=").append(parkingSlot);
        sb.append(", time=").append(time);
        sb.append(", createDate=").append(createDate);
        sb.append(", fromDate=").append(fromDate);
        sb.append(", toDate=").append(toDate);
        sb.append(", vehicleNumber='").append(vehicleNumber).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
