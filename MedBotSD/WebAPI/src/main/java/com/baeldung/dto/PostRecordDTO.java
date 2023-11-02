package com.baeldung.dto;

import java.sql.Date;
import java.sql.Time;

public class PostRecordDTO {

    private int id;
    private int doctorId;
    private String patientLogin;
    private Date date;
    private Time startTime;
    private Time endTime;

    public PostRecordDTO() {}

    public PostRecordDTO(int doctorId, String patientLogin, Date date, Time startTime, Time endTime) {
        this.doctorId = doctorId;
        this.patientLogin = patientLogin;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getPatientLogin() {
        return patientLogin;
    }

    public void setPatientLogin(String patientLogin) {
        this.patientLogin = patientLogin;
    }
}
