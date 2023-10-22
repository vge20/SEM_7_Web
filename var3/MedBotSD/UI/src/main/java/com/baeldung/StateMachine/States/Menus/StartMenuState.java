package com.baeldung.StateMachine.States.Menus;

import com.baeldung.Doctor.Doctor;
import com.baeldung.Doctor.IDoctorService;
import com.baeldung.Record.IRecordService;
import com.baeldung.Record.Record;
import com.baeldung.StateMachine.EnumStates.EnumStates;
import com.baeldung.StateMachine.States.ParentState.State;
import com.baeldung.User.IUserService;
import com.baeldung.User.User;
import com.baeldung.View.RecordView;
import com.baeldung.View.UserView;
import com.baeldung.View.DoctorView;
import org.slf4j.Logger;
import org.telegram.telegrambots.meta.api.objects.Update;


import java.util.ArrayList;

public class StartMenuState extends State {
    private Logger logger;

    public StartMenuState(Logger logger) {
        this.logger = logger;
    }

    @Override
    public int updateHandler(int state, EnumStates enumStates, Update update, User user, User userTmp, Doctor doctorTmp,
                             Record recordTmp, IUserService userService, IDoctorService doctorService,
                             IRecordService recordService, UserView userView, DoctorView doctorView, RecordView recordView) {

        if (update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().equals("/start")) {
            userView.startUserMenu(update);
            return enumStates.START_MENU_STATE;
        }
        else if (update.hasCallbackQuery() && update.getCallbackQuery().getData().equals("unregLogIn")) {
            userView.userLogInInputMessage(update);
            return enumStates.UNREG_USER_LOGIN_SCAN_STATE;
        }
        else if (update.hasCallbackQuery() && update.getCallbackQuery().getData().equals("unregRegistration")) {
            userView.userFirstNameInputMessage(update);
            return enumStates.REGISTRATION_FIRST_NAME_SCAN_STATE;
        }
        else if (update.hasCallbackQuery() && update.getCallbackQuery().getData().equals("doctorsList")) {
           ArrayList<Doctor> doctorsList = null;
            try {
                doctorsList = doctorService.getDoctorsList();
            } catch (Exception e) {
                logger.error("Ошибка " + e + " при получении списка врачей!");
            }

            if (doctorsList == null)
                doctorView.doctorsListErrorMessage(update);
            else if (doctorsList.size() == 0)
                doctorView.doctorsListEmptyMessage(update);
            else
                doctorView.doctorsListMessage(update, doctorsList);

            userView.startUserMenu(update);
            return state;
        }

        return state;
    }
}
