package com.baeldung.StateMachine.Machine;

import com.baeldung.DI.AppConfig;
import com.baeldung.Doctor.Doctor;
import com.baeldung.Doctor.IDoctorService;
import com.baeldung.Record.IRecordService;
import com.baeldung.StateMachine.EnumStates.EnumStates;
import com.baeldung.User.IUserService;
import com.baeldung.Record.Record;
import com.baeldung.StateMachine.Factory.StateFactory;
import com.baeldung.User.User;
import com.baeldung.View.DoctorView;
import com.baeldung.View.RecordView;
import com.baeldung.View.UserView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class StateMachine {
    private EnumStates enumStates;
    private int state;
    private StateFactory stateFactory;
    private Logger logger;
    private User user;
    private User userTmp;
    private Doctor doctorTmp;
    private Record recordTmp;
    private AppConfig appConfig;
    private IUserService userService;
    private IDoctorService doctorService;
    private IRecordService recordService;
    private UserView userView;
    private DoctorView doctorView;
    private RecordView recordView;

    public StateMachine() {
        try {
            FileInputStream in = new FileInputStream("src/main/resources/config.properties");
            Properties props = new Properties();
            props.load(in);

            if (props.getProperty("log_level").equals("info"))
                logger = LoggerFactory.getLogger("info.logger");
            else
                logger = LoggerFactory.getLogger("error.logger");

        } catch (FileNotFoundException e) {
            System.out.println("Не найден конфигурационный файл в StateMachine!");
        } catch (IOException e) {
            System.out.println("Ошибка подкачки настроек из конфигурационного файла в StateMachine!");
        }

        enumStates = new EnumStates();
        state = enumStates.START_MENU_STATE;
        stateFactory = new StateFactory();
        user = new User();
        userTmp = new User();
        doctorTmp = new Doctor();
        recordTmp = new Record();
        userView = new UserView(logger);
        doctorView = new DoctorView(logger);
        recordView = new RecordView(logger);

        try {
            appConfig = new AppConfig();
            userService = appConfig.getUserServiceImpl();
            doctorService = appConfig.getDoctorServiceImpl();
            recordService = appConfig.getRecordServiceImpl();
        } catch (IOException e) {
            logger.error("Ошибка " + e + " при конструировании AppConfig в StateMachine.");
        }
    }

    public void updateHandler(Update update) {
        state = stateFactory.getStateClass(state, logger).updateHandler(state, enumStates, update, user, userTmp,
                doctorTmp, recordTmp, userService, doctorService, recordService, userView, doctorView, recordView);
    }
}
