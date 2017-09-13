package com.test.parking.core.models.tickets;

import javax.persistence.*;

/**
 * Created by Yuriy Yugay on 9/13/2017.
 *
 * @author Yuriy Yugay
 */
@Entity
@Table(name = "normal_ticket")
@Inheritance(strategy = InheritanceType.JOINED)
public class NormalTicket
        extends Ticket {

    @Column(name = "occupied_time")
    private int occupiedTime;

    @Column(name = "total_cost")
    private double totalCost;

    private byte[] qrCode;

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
