package com.baeldung.dto;

public class PostDoctorDTO {

    private String firstName;
    private String lastName;
    private Boolean gender;
    private String specialization;

    public PostDoctorDTO() {}

    public PostDoctorDTO(String firstName, String lastName, Boolean gender, String specialization) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.specialization = specialization;
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
