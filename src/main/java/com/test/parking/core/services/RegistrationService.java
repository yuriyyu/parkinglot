package com.test.parking.core.services;

import com.test.parking.core.models.reservations.Registration;
import com.test.parking.core.models.spaces.ParkingSlot;
import com.test.parking.core.repositories.ParkingSlotRepository;
import com.test.parking.core.repositories.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Created by Yuriy Yugay on 9/13/2017.
 *
 * @author Yuriy Yugay
 */
@Service("registrationService")
public class RegistrationService {

    private ParkingSlotRepository parkingSlotRepository;
    private RegistrationRepository registrationRepository;

    @Autowired
    public RegistrationService(ParkingSlotRepository parkingSlotRepository,
                               RegistrationRepository registrationRepository) {
        this.parkingSlotRepository = parkingSlotRepository;
        this.registrationRepository = registrationRepository;
    }

    public void createRegistration (int parkingSlotId, int time) {
        ParkingSlot parkingSlot = parkingSlotRepository.findOne(parkingSlotId);

        Registration registration = new Registration();
        registration.setParkingSlot(parkingSlot);
        registration.setTime(time);
        registration.setCreateDate(LocalDateTime.now());

        registrationRepository.save(registration);
    }
}
