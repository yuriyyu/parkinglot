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

//    private Vehicle vehicle;

//    private Tariff tariff;

    @OneToOne(mappedBy = "registration",cascade = CascadeType.ALL)
    private Ticket ticket;

    private int time;

    private LocalDateTime createDate;

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
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

//    public Vehicle getVehicle() {
//        return vehicle;
//    }
//
//    public void setVehicle(Vehicle vehicle) {
//        this.vehicle = vehicle;
//    }
//
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
}
