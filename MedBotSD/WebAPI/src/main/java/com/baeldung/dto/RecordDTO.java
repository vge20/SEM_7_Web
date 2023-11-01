package com.baeldung.dto;

import java.sql.Date;
import java.sql.Time;

public class RecordDTO {

    private int id;
    private int idDoctor;
    private String userLogin;
    private Date date;
    private Time startTime;
    private Time endTime;

    public RecordDTO() {}

    public RecordDTO(int id, int idDoctor, String userLogin, Date date, Time startTime, Time endTime) {
        this.id = id;
        this.idDoctor = idDoctor;
        this.userLogin = userLogin;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public RecordDTO(int idDoctor, String userLogin, Date date, Time startTime, Time endTime) {
        this.idDoctor = idDoctor;
        this.userLogin = userLogin;
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

    public int getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(int idDoctor) {
        this.idDoctor = idDoctor;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
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
}
