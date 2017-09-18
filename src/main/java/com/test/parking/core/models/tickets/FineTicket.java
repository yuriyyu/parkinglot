package com.test.parking.core.models.tickets;

import javax.persistence.*;

/**
 * Created by Yuriy Yugay on 9/13/2017.
 *
 * @author Yuriy Yugay
 */
public class FineTicket
        extends Ticket {

    private double fineCost;
    // in minutes
    private int exceededTime;

    public FineTicket() {
        super();
    }

    public double getFineCost() {
        return fineCost;
    }

    public void setFineCost(double fineCost) {
        this.fineCost = fineCost;
    }

    public int getExceededTime() {
        return exceededTime;
    }

    public void setExceededTime(int exceededTime) {
        this.exceededTime = exceededTime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FineTicket: \n");
        sb.append("Registration Id: ").append(registrationId).append("\n");
        sb.append("Slot Number: ").append(slotNumber).append("\n");
        sb.append("Vehicle Number: ").append(vehicleNumber).append("\n");
        sb.append("Exceeded Time: ").append(exceededTime).append("\n");
        sb.append("Fine Cost: ").append(fineCost);
        return sb.toString();
    }
}
