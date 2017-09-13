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
@Entity
@Table(name = "ticket")
@Inheritance(strategy = InheritanceType.JOINED)
public class Ticket {

    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    @JoinColumn(name = "registration_id")
    private Registration registration;

    private LocalDateTime createDate;

}
