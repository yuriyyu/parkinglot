package com.test.parking.core.models.tickets;

import javax.persistence.*;

/**
 * Created by Yuriy Yugay on 9/13/2017.
 *
 * @author Yuriy Yugay
 */
public class NormalTicket
        extends Ticket {

    private int occupiedTime;

    private double totalCost;

    private byte[] qrCode;

    public NormalTicket() {
        super();
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
}
