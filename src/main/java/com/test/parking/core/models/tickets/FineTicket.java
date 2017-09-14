package com.test.parking.core.models.tickets;

import javax.persistence.*;

/**
 * Created by Yuriy Yugay on 9/13/2017.
 *
 * @author Yuriy Yugay
 */
@Entity
@Table(name = "fine_ticket")
@Inheritance(strategy = InheritanceType.JOINED)
public class FineTicket
        extends Ticket {

    @Column(name="fine_cost")
    private double fineCost;
    // in minutes
    @Column(name = "exceeded_time")
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
}
