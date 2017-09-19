package com.test.parking.core.services;

import com.test.parking.core.models.reservations.Registration;
import com.test.parking.core.models.tickets.FineTicket;
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

    public TicketService() {
    }

    public NormalTicket createNormalTicket(Registration registration) {
        NormalTicket ticket = new NormalTicket();
        ticket.setRegistrationId(registration.getId());
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

    public FineTicket createOvertimeTicket(Registration overtimeRegistration) {
        int exceededTime = 30;
        FineTicket fineTicket = new FineTicket();
        fineTicket.setExceededTime(exceededTime);
        fineTicket.setRegistrationId(overtimeRegistration.getId());
        fineTicket.setSlotNumber(overtimeRegistration.getParkingSlot().getSlotName());
        fineTicket.setVehicleNumber(overtimeRegistration.getVehicleNumber());

        double fineAmount = fineAmountCalculation(
                overtimeRegistration.getTime(), overtimeRegistration.getFeeAmount(), exceededTime);
        fineTicket.setFineCost(fineAmount);

        return fineTicket;
    }

    private double fineAmountCalculation(int time, double feeAmount, int exceededTime) {
        double doublePricePerHour = feeAmount / ((double)time / 60f) * 2;
        return (double)exceededTime / 60f * doublePricePerHour;
    }
}
