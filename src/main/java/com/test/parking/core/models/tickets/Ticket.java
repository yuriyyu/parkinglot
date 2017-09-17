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

    private int id;

    private LocalDateTime createDate;

    public Ticket(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
}
