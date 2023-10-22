package com.baeldung.Record;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "Records")
public class RecordDAModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "idDoctor")
    private int idDoctor;
    @Column(name = "idUser")
    private int idUser;
    @Column(name = "date")
    private Date date;
    @Column(name = "startTime")
    private Time startTime;
    @Column(name = "endTime")
    private Time endTime;

    public RecordDAModel() {}

    public RecordDAModel(int id, int idDoctor, int idUser, Date date, Time startTime, Time endTime) {
        this.id = id;
        this.idDoctor = idDoctor;
        this.idUser = idUser;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public RecordDAModel(int idDoctor, int idUser, Date date, Time startTime, Time endTime) {
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
