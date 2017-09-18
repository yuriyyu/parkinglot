package com.test.parking.core.models.users;

import javax.persistence.*;

/**
 * Created by Dmitry on 9/17/2017.
 */
@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USER_TYPE")
public abstract class User {
    @Id
    @GeneratedValue
    protected int id;

    @Column(name="USER_TYPE", updatable = false, insertable = false)
    protected String type;

    @Column(unique = true)
    protected String loginName;

    protected String password;

    protected String fullName;

    public User() { }

    public User(String loginName, String password, String fullName) {
        this.loginName = loginName;
        this.password = password;
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getLoginName() {
        return loginName;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }
}
