package com.baeldung.Record;

import java.sql.Date;
import java.sql.Time;

public class Record {
    private int id;
    private int idDoctor;
    private int idUser;
    private Date date;
    private Time startTime;
    private Time endTime;

    public Record() {}

    public Record(int id, int idDoctor, int idUser, Date date, Time startTime, Time endTime) {
        this.id = id;
        this.idDoctor = idDoctor;
        this.idUser = idUser;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Record(int idDoctor, int idUser, Date date, Time startTime, Time endTime) {
        this.idDoctor = idDoctor;
        this.idUser = idUser;
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

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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
