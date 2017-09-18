package com.test.parking.core.services;

import com.test.parking.core.models.reservations.Registration;
import com.test.parking.core.models.tickets.NormalTicket;
import com.test.parking.core.models.tickets.Ticket;
import com.test.parking.core.repositories.RegistrationRepository;
import com.test.parking.core.utils.QRCodeGenerator;
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
        NormalTicket ticket = new NormalTicket();
        ticket.setFromDate(registration.getFromDate());
        ticket.setToDate(registration.getToDate());
        ticket.setVehicleNumber(registration.getVehicleNumber());
        ticket.setSlotNumber(registration.getParkingSlot().getSlotName());
        ticket.setOccupiedTime(registration.getTime());
        ticket.setTotalCost(registration.getFeeAmount());
        ticket.setCreateDate(registration.getCreateDate());

        ticket.setQrCodeImage(QRCodeGenerator.qrCodeFileGenerator(ticket.toString()));

        return ticket;
    }
}
