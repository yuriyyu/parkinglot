package com.test.parking.core.models.users;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Dmitry on 9/17/2017.
 */
@Entity
@DiscriminatorValue("manager")
public class Manager extends User {

    public Manager() {}

    public Manager(String loginName, String password, String fullName) {
        super(loginName, password, fullName);
    }
}
