package com.test.parking.core.repositories;

import com.test.parking.core.models.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Dmitry on 9/17/2017.
 */
public interface UserRepository
        extends JpaRepository<User, Integer> {

    User findByLoginName(String loginName);

}
