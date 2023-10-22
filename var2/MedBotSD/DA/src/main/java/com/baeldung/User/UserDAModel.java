package com.baeldung.User;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Users")
public class UserDAModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "privilegeLevel")
    private int privilegeLevel;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "gender")
    private Boolean gender;
    @Column(name = "birthDate")
    private Date birthDate;

    public UserDAModel() {}

    public UserDAModel(int id, String login, String password, int privilegeLevel, String firstName, String lastName,
                       Boolean gender, Date birthDate) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.privilegeLevel = privilegeLevel;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    public UserDAModel(String login, String password, int privilegeLevel, String firstName, String lastName,
                       Boolean gender, Date birthDate) {
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
