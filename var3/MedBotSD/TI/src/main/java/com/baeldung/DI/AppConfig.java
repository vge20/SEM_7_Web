package com.baeldung.DI;

import com.baeldung.Doctor.*;
import com.baeldung.Record.IRecordRepository;
import com.baeldung.Record.IRecordService;
import com.baeldung.Record.RecordRepository;
import com.baeldung.Record.RecordService;
import com.baeldung.Schedule.IScheduleRepository;
import com.baeldung.Schedule.ScheduleRepository;
import com.baeldung.User.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class AppConfig {
    private Properties props;

    public AppConfig() throws IOException {
        props = new Properties();
        try {
            FileInputStream in = new FileInputStream("src/main/resources/config.properties");
            props.load(in);
        } catch (IOException e) {
            throw e;
        }
    }

    @Bean
    public IUserRepository getUserRepositoryImpl() {
        return new UserRepository();
    }
    @Bean
    public IGetUserRepository getGetUserRepositoryImpl() {
        return new UserRepository();
    }
    @Bean
    public IUserService getUserServiceImpl() throws IOException {
        if (props.getProperty("db").equals("postgres"))
            return new UserService(getGetUserRepositoryImpl(), getUserRepositoryImpl());
        else
            return null;
    }
    @Bean
    public IDoctorRepository getDoctorRepositoryImpl() {
        return new DoctorRepository();
    }
    @Bean
    public IGetDoctorRepository getGetDoctorRepositoryImpl() {
        return new DoctorRepository();
    }
    @Bean
    public IDoctorService getDoctorServiceImpl() throws IOException {
        if (props.getProperty("db").equals("postgres"))
            return new DoctorService(getGetDoctorRepositoryImpl(),
                    getDoctorRepositoryImpl(), getScheduleRepositoryImpl());
        else
            return null;
    }
    @Bean
    public IScheduleRepository getScheduleRepositoryImpl() {
        return new ScheduleRepository();
    }
    @Bean
    public IRecordRepository getRecordRepositoryImpl() {
        return new RecordRepository();
    }
    @Bean
    public IRecordService getRecordServiceImpl() throws IOException {
        if (props.getProperty("db").equals("postgres"))
            return new RecordService(getRecordRepositoryImpl(), getGetDoctorRepositoryImpl(),
                    getScheduleRepositoryImpl(), getGetUserRepositoryImpl());
        else
            return null;
    }
}
