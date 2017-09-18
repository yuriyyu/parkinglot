package com.test.parking.core.models;

import javax.persistence.*;

/**
 * Created by User on 7/4/2016.
 */
@Entity
@DiscriminatorValue("ground")
public class GroundParkingLot
        extends ParkingLot {


    public GroundParkingLot() {
    }
    
    
}
