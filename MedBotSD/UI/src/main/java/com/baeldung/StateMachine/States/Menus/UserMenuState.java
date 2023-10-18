package com.baeldung.StateMachine.States.Menus;

import com.baeldung.Doctor.Doctor;
import com.baeldung.Doctor.IDoctorService;
import com.baeldung.Record.IRecordService;
import com.baeldung.Record.Record;
import com.baeldung.StateMachine.EnumStates.EnumStates;
import com.baeldung.StateMachine.States.ParentState.State;
import com.baeldung.User.IUserService;
import com.baeldung.User.User;
import com.baeldung.View.DoctorView;
import com.baeldung.View.RecordView;
import com.baeldung.View.UserView;
import org.slf4j.Logger;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;

public class UserMenuState extends State {
    private Logger logger;

    public UserMenuState(Logger logger) { this.logger = logger; }

    private int doctorsListForRecord(int nextState, EnumStates enumStates, DoctorView doctorView, UserView userView,
                                     Record recordTmp, IDoctorService doctorService, Update update, User user) {
        ArrayList<Doctor> doctorsList = null;
        try {
            doctorsList = doctorService.getDoctorsList();
        } catch (Exception e) {
            logger.error("Ошибка " + e + " при получении списка врачей!");
        }

        if (doctorsList == null) {
            doctorView.doctorsListErrorMessage(update);
            userView.userMenu(update);
            return enumStates.USER_MENU_STATE;
        }
        else if (doctorsList.size() == 0) {
            doctorView.doctorsListEmptyMessage(update);
            userView.userMenu(update);
            return enumStates.USER_MENU_STATE;
        }
        else {
            doctorView.doctorsListForUpdateMessage(doctorsList, update);
            doctorView.doctorIdInputMessage(update);
            recordTmp.setIdUser(user.getId());
            return nextState;
        }
    }

    @Override
    public int updateHandler(int state, EnumStates enumStates, Update update, User user, User userTmp, Doctor doctorTmp,
                             Record recordTmp, IUserService userService, IDoctorService doctorService,
                             IRecordService recordService, UserView userView, DoctorView doctorView, RecordView recordView) {

        if (update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().equals("/start")) {
            userView.startUserMenu(update);
            return enumStates.START_MENU_STATE;
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

            userView.userMenu(update);
            return state;
        }
        else if (update.hasCallbackQuery() && update.getCallbackQuery().getData().equals("updateMyAccount")) {
            userTmp.setId(user.getId());
            userView.userNewDataInputMessage(update);
            userView.userFirstNameInputMessage(update);
            return enumStates.UPDATE_ACCOUNT_FIRST_NAME_SCAN_STATE;
        }
        else if (update.hasCallbackQuery() && update.getCallbackQuery().getData().equals("addRecord")) {
            return doctorsListForRecord(enumStates.ADD_RECORD_DOCTOR_ID_SCAN_STATE, enumStates, doctorView,
                    userView, recordTmp, doctorService, update, user);
        }
        else if (update.hasCallbackQuery() && update.getCallbackQuery().getData().equals("deleteRecord")) {
            return doctorsListForRecord(enumStates.DELETE_RECORD_DOCTOR_ID_SCAN_STATE, enumStates, doctorView,
                    userView, recordTmp, doctorService, update, user);
        }
        else if (update.hasCallbackQuery() && update.getCallbackQuery().getData().equals("updateRecord")) {
            return doctorsListForRecord(enumStates.UPDATE_RECORD_DOCTOR_ID_SCAN_STATE, enumStates, doctorView,
                    userView, recordTmp, doctorService, update, user);
        }

        return state;
    }
}
