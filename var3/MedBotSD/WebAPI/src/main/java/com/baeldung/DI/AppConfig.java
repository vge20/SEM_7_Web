package com.baeldung.DI;

import com.baeldung.Doctor.*;
import com.baeldung.Record.IRecordRepository;
import com.baeldung.Record.IRecordService;
import com.baeldung.Record.RecordRepository;
import com.baeldung.Record.RecordService;
import com.baeldung.Schedule.IScheduleRepository;
import com.baeldung.Schedule.ScheduleRepository;
import com.baeldung.User.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AppConfig {
    private Properties props;

    public AppConfig() throws IOException {
        props = new Properties();
        try {
            FileInputStream in = new FileInputStream("C:/SEM_7_Web/SEM_7_Web/var3/MedBotSD/src/main/resources/config.properties");
            props.load(in);
        } catch (IOException e) {
            throw e;
        }
    }

    public IUserRepository getUserRepositoryImpl() {
        return new UserRepository();
    }

    public IGetUserRepository getGetUserRepositoryImpl() {
        return new UserRepository();
    }

    public IUserService getUserServiceImpl() throws IOException {
        if (props.getProperty("db").equals("postgres"))
            return new UserService(getGetUserRepositoryImpl(), getUserRepositoryImpl());
        else
            return null;
    }

    public IDoctorRepository getDoctorRepositoryImpl() {
        return new DoctorRepository();
    }

    public IGetDoctorRepository getGetDoctorRepositoryImpl() {
        return new DoctorRepository();
    }

    public IDoctorService getDoctorServiceImpl() throws IOException {
        if (props.getProperty("db").equals("postgres"))
            return new DoctorService(getGetDoctorRepositoryImpl(),
                    getDoctorRepositoryImpl(), getScheduleRepositoryImpl());
        else
            return null;
    }

    public IScheduleRepository getScheduleRepositoryImpl() {
        return new ScheduleRepository();
    }

    public IRecordRepository getRecordRepositoryImpl() {
        return new RecordRepository();
    }

    public IRecordService getRecordServiceImpl() throws IOException {
        if (props.getProperty("db").equals("postgres"))
            return new RecordService(getRecordRepositoryImpl(), getGetDoctorRepositoryImpl(),
                    getScheduleRepositoryImpl(), getGetUserRepositoryImpl());
        else
            return null;
    }
}
