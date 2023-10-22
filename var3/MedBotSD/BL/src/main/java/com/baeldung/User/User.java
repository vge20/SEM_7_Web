package com.baeldung.User;

import java.sql.Date;

public class User {
    private int id;
    private String login;
    private String password;
    private int privilegeLevel;
    private String firstName;
    private String lastName;
    private Boolean gender;
    private Date birthDate;

    public User() {}

    public User(int id, String login, String password, int privilegeLevel, String firstName,
                String lastName, Boolean gender, Date birthDate) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.privilegeLevel = privilegeLevel;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    public User(String login, String password, int privilegeLevel, String firstName,
                String lastName, Boolean gender, Date birthDate) {
        this.login = login;
        this.password = password;
        this.privilegeLevel = privilegeLevel;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPrivilegeLevel() {
        return privilegeLevel;
    }

    public void setPrivilegeLevel(int privilegeLevel) {
        this.privilegeLevel = privilegeLevel;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
