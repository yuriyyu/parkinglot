package com.test.parking.core.services;

import com.test.parking.core.models.reservations.Registration;
import com.test.parking.core.models.tickets.NormalTicket;
import com.test.parking.core.models.tickets.Ticket;
import com.test.parking.core.repositories.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Yuriy Yugay on 9/13/2017.
 *
 * @author Yuriy Yugay
 */
@Service("ticketService")
public class TicketService {

    private RegistrationRepository registrationRepository;

    @Autowired
    public TicketService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    public NormalTicket createNormalTicket(Registration registration) {

        System.out.println(registration);
        NormalTicket ticket = new NormalTicket();
//        ticket.setOccupiedTime();

//        registration.getTime()

//        ticket.setTotalCost();
        ticket.setCreateDate(registration.getCreateDate());
        return ticket;
    }
}
