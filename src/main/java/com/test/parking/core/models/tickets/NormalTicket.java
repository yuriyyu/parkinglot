package com.test.parking.core.models.tickets;

import javax.persistence.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Yuriy Yugay on 9/13/2017.
 *
 * @author Yuriy Yugay
 */
public class NormalTicket
        extends Ticket {

    private LocalDateTime fromDate;

    private LocalDateTime toDate;

    private String vehicleNumber;

    private String slotNumber;

    private int occupiedTime;

    private double totalCost;

    private BufferedImage qrCodeImage;

    public NormalTicket() {
        super();
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

    public String getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(String slotNumber) {
        this.slotNumber = slotNumber;
    }

    public BufferedImage getQrCodeImage() {
        return qrCodeImage;
    }

    public void setQrCodeImage(BufferedImage qrCodeImage) {
        this.qrCodeImage = qrCodeImage;
    }

    public int getOccupiedTime() {
        return occupiedTime;
    }

    public void setOccupiedTime(int occupiedTime) {
        this.occupiedTime = occupiedTime;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy hh:mm");
        final StringBuilder sb = new StringBuilder("Ticket: \n");
        sb.append("Vehicle Number: ").append(vehicleNumber).append("\n");
        sb.append("Slot Number: ").append(slotNumber).append("\n");
        sb.append("From Date: ").append(fromDate.format(formatter)).append("\n");
        sb.append("To Date: ").append(toDate.format(formatter)).append("\n");
        sb.append("Occupied Time: ").append(occupiedTime).append(" minutes").append("\n");
        sb.append("Total Cost: ").append(totalCost).append(" $");
        return sb.toString();
    }
}
