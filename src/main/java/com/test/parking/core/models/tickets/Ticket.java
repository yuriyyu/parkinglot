package com.test.parking.core.models.tickets;

import com.test.parking.core.models.reservations.Registration;
import com.test.parking.core.models.spaces.ParkingSlot;
import com.test.parking.core.models.vehicles.Vehicle;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Yuriy Yugay on 9/13/2017.
 *
 * @author Yuriy Yugay
 */
public class Ticket {

    protected String vehicleNumber;

    protected String slotNumber;
    protected LocalDateTime createDate;
    protected int registrationId;

    public Ticket(){

    }

    public int getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(int registrationId) {
        this.registrationId = registrationId;
    }

    public String getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(String slotNumber) {
        this.slotNumber = slotNumber;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
}
