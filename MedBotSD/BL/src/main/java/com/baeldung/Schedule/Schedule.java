package com.baeldung.Schedule;

import java.sql.Time;

public class Schedule {
    private int id;
    private int idDoctor;

    private final int COUNT_WORK_DAYS = 5;

    private Time[] startTimes;

    private Time[] endTimes;

    public Schedule() {
        startTimes = new Time[COUNT_WORK_DAYS];
        endTimes = new Time[COUNT_WORK_DAYS];

        for (int i = 0; i < COUNT_WORK_DAYS; i++) {
            startTimes[i] = Time.valueOf("9:00:00");
            endTimes[i] = Time.valueOf("18:00:00");
        }
    }

    public Schedule(int idDoctor) {
        this.idDoctor = idDoctor;
        startTimes = new Time[COUNT_WORK_DAYS];
        endTimes = new Time[COUNT_WORK_DAYS];

        for (int i = 0; i < COUNT_WORK_DAYS; i++) {
            startTimes[i] = Time.valueOf("9:00:00");
            endTimes[i] = Time.valueOf("18:00:00");
        }
    }

    public Schedule(Time startTime, Time endTime) {
        startTimes = new Time[COUNT_WORK_DAYS];
        endTimes = new Time[COUNT_WORK_DAYS];

        for (int i = 0; i < COUNT_WORK_DAYS; i++) {
            startTimes[i] = startTime;
            endTimes[i] = endTime;
        }
    }

    public Schedule(int id, int idDoctor, Time startTime, Time endTime) {
        startTimes = new Time[COUNT_WORK_DAYS];
        endTimes = new Time[COUNT_WORK_DAYS];

        for (int i = 0; i < COUNT_WORK_DAYS; i++) {
            startTimes[i] = startTime;
            endTimes[i] = endTime;
        }
        this.id = id;
        this.idDoctor = idDoctor;
    }

    public Schedule(int idDoctor, Time startTime, Time endTime) {
        startTimes = new Time[COUNT_WORK_DAYS];
        endTimes = new Time[COUNT_WORK_DAYS];

        for (int i = 0; i < COUNT_WORK_DAYS; i++) {
            startTimes[i] = startTime;
            endTimes[i] = endTime;
        }
        this.id = id;
        this.idDoctor = idDoctor;
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

    public void setStartByDay(int dayOfWeek, Time time) {
        startTimes[dayOfWeek] = time;
    }

    public Time getStartByDay(int dayOfWeek) {
        return startTimes[dayOfWeek];
    }

    public void setEndByDay(int dayOfWeek, Time time) {
        endTimes[dayOfWeek] = time;
    }

    public Time getEndByDay(int dayOfWeek) {
        return endTimes[dayOfWeek];
    }
}
