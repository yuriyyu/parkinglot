package com.test.parking.core.services;

import com.test.parking.core.models.ParkingLot;
import com.test.parking.core.models.reservations.Registration;
import com.test.parking.core.models.spaces.ParkingSlot;
import com.test.parking.core.models.tariffs.Tariff;
import com.test.parking.core.repositories.ParkingSlotRepository;
import com.test.parking.core.repositories.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public Registration createRegistration (String vehicleNumber, int parkingSlotId, int time, double feeAmount) {
        ParkingSlot parkingSlot = parkingSlotRepository.findOne(parkingSlotId);

        Registration registration = new Registration();
        registration.setVehicleNumber(vehicleNumber);
        registration.setParkingSlot(parkingSlot);
        registration.setTime(time);
        registration.setFeeAmount(feeAmount);

        LocalDateTime currentDateTime = LocalDateTime.now();
        long dueTimeInSeconds = currentDateTime.toEpochSecond(ZoneOffset.of("-5")) + time * 60;
        LocalDateTime dueDateTime = LocalDateTime.ofEpochSecond(dueTimeInSeconds, 0 , ZoneOffset.of("-5"));

        registration.setCreateDate(currentDateTime);
        registration.setFromDate(currentDateTime);
        registration.setToDate(dueDateTime);

        return registrationRepository.save(registration);
    }

    public void createStubRegistrations(List<ParkingSlot> parkingSlots, List<Tariff> tariffs) {
        List<String> occupied = new ArrayList<>();
        occupied.add("A3");
        occupied.add("A5");
        occupied.add("B1");
        occupied.add("B4");
        occupied.add("C2");
        occupied.add("C5");
        occupied.add("D1");
        occupied.add("D2");
        occupied.add("E4");
        occupied.add("E5");

        Random random = new Random();

        int [] timeArray = {30, 60, 90, 120};

        String randomSym = "QWERTYUIOPASDFGHJKLZXCVBNM1234567890";

        for(String str : occupied) {
            char [] chArray = str.toCharArray();
            for(ParkingSlot slot : parkingSlots) {
                if(String.valueOf(chArray[0]).equals(slot.getColumn())
                        && Character.getNumericValue(chArray[1]) == slot.getRow()) {
                    StringBuilder s = new StringBuilder();
                    for(int x = 0; x < 5; x++) {
                        int chIndex = random.nextInt(randomSym.length());
                        s.append(randomSym.charAt(chIndex));
                    }
                    Tariff tariff = tariffs.stream()
                            .filter(tar -> (tar.getType() == slot.getType() && tar.isHoliday() == false ))
                            .findFirst()
                            .orElse(tariffs.get(0));
                    double perHourPrice = tariff.getPrice();
                    double randomTime = timeArray[random.nextInt(4)];
                    double feeAmount = randomTime / 60f * perHourPrice;
                    createRegistration(s.toString(), slot.getId(), (int)randomTime, feeAmount);
                }
            }

        }
    }

    public List<Registration> getRegistrations() {
        return registrationRepository.findAll();
    }

    public Registration getRegistrationBySlotId(int slotId) {
        return registrationRepository.findByParkingSlotId(slotId);
    }
}
