package com.baeldung.Schedule;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "Schedules")
public class ScheduleDAModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "idDoctor")
    private int idDoctor;
    @Column(name = "mondayStartTime")
    private Time mondayStartTime;
    @Column(name = "mondayEndTime")
    private Time mondayEndTime;
    @Column(name = "tuesdayStartTime")
    private Time tuesdayStartTime;
    @Column(name = "tuesdayEndTime")
    private Time tuesdayEndTime;
    @Column(name = "wednesdayStartTime")
    private Time wednesdayStartTime;
    @Column(name = "wednesdayEndTime")
    private Time wednesdayEndTime;
    @Column(name = "thursdayStartTime")
    private Time thursdayStartTime;
    @Column(name = "thursdayEndTime")
    private Time thursdayEndTime;
    @Column(name = "fridayStartTime")
    private Time fridayStartTime;
    @Column(name = "fridayEndTime")
    private Time fridayEndTime;

    public ScheduleDAModel() {}

    public ScheduleDAModel(int id, int idDoctor, Time mondayStartTime, Time mondayEndTime, Time tuesdayStartTime,
                           Time tuesdayEndTime, Time wednesdayStartTime, Time wednesdayEndTime, Time thursdayStartTime,
                           Time thursdayEndTime, Time fridayStartTime, Time fridayEndTime) {
        this.id = id;
        this.idDoctor = idDoctor;
        this.mondayStartTime = mondayStartTime;
        this.mondayEndTime = mondayEndTime;
        this.tuesdayStartTime = tuesdayStartTime;
        this.tuesdayEndTime = tuesdayEndTime;
        this.wednesdayStartTime = wednesdayStartTime;
        this.wednesdayEndTime = wednesdayEndTime;
        this.thursdayStartTime = thursdayStartTime;
        this.thursdayEndTime = thursdayEndTime;
        this.fridayStartTime = fridayStartTime;
        this.fridayEndTime = fridayEndTime;
    }

    public ScheduleDAModel(int idDoctor, Time mondayStartTime, Time mondayEndTime, Time tuesdayStartTime,
                           Time tuesdayEndTime, Time wednesdayStartTime, Time wednesdayEndTime, Time thursdayStartTime,
                           Time thursdayEndTime, Time fridayStartTime, Time fridayEndTime) {
        this.idDoctor = idDoctor;
        this.mondayStartTime = mondayStartTime;
        this.mondayEndTime = mondayEndTime;
        this.tuesdayStartTime = tuesdayStartTime;
        this.tuesdayEndTime = tuesdayEndTime;
        this.wednesdayStartTime = wednesdayStartTime;
        this.wednesdayEndTime = wednesdayEndTime;
        this.thursdayStartTime = thursdayStartTime;
        this.thursdayEndTime = thursdayEndTime;
        this.fridayStartTime = fridayStartTime;
        this.fridayEndTime = fridayEndTime;
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

    public Time getMondayStartTime() {
        return mondayStartTime;
    }

    public void setMondayStartTime(Time mondayStartTime) {
        this.mondayStartTime = mondayStartTime;
    }

    public Time getMondayEndTime() {
        return mondayEndTime;
    }

    public void setMondayEndTime(Time mondayEndTime) {
        this.mondayEndTime = mondayEndTime;
    }

    public Time getTuesdayStartTime() {
        return tuesdayStartTime;
    }

    public void setTuesdayStartTime(Time tuesdayStartTime) {
        this.tuesdayStartTime = tuesdayStartTime;
    }

    public Time getTuesdayEndTime() {
        return tuesdayEndTime;
    }

    public void setTuesdayEndTime(Time tuesdayEndTime) {
        this.tuesdayEndTime = tuesdayEndTime;
    }

    public Time getWednesdayStartTime() {
        return wednesdayStartTime;
    }

    public void setWednesdayStartTime(Time wednesdayStartTime) {
        this.wednesdayStartTime = wednesdayStartTime;
    }

    public Time getWednesdayEndTime() {
        return wednesdayEndTime;
    }

    public void setWednesdayEndTime(Time wednesdayEndTime) {
        this.wednesdayEndTime = wednesdayEndTime;
    }

    public Time getThursdayStartTime() {
        return thursdayStartTime;
    }

    public void setThursdayStartTime(Time thursdayStartTime) {
        this.thursdayStartTime = thursdayStartTime;
    }

    public Time getThursdayEndTime() {
        return thursdayEndTime;
    }

    public void setThursdayEndTime(Time thursdayEndTime) {
        this.thursdayEndTime = thursdayEndTime;
    }

    public Time getFridayStartTime() {
        return fridayStartTime;
    }

    public void setFridayStartTime(Time fridayStartTime) {
        this.fridayStartTime = fridayStartTime;
    }

    public Time getFridayEndTime() {
        return fridayEndTime;
    }

    public void setFridayEndTime(Time fridayEndTime) {
        this.fridayEndTime = fridayEndTime;
    }
}
