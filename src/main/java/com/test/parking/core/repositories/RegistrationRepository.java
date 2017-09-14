package com.test.parking.core.repositories;

import com.test.parking.core.models.reservations.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Yuriy Yugay on 9/13/2017.
 *
 * @author Yuriy Yugay
 */
public interface RegistrationRepository
        extends JpaRepository<Registration, Integer> {
}
