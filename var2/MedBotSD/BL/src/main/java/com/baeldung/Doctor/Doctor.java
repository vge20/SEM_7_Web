package com.baeldung.Doctor;

public class Doctor {
    private int id;
    private String firstName;
    private String lastName;
    private Boolean gender;
    private String specialization;

    public Doctor() {}

    public Doctor(int id, String firstName, String lastName, Boolean gender, String specialization) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.specialization = specialization;
    }

    public Doctor(String firstName, String lastName, Boolean gender, String specialization) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.specialization = specialization;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
