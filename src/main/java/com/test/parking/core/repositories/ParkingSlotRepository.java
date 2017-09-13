/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.parking.core.repositories;


import com.test.parking.core.models.spaces.ParkingSlot;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author 986056
 */
public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Integer> {
    
}
