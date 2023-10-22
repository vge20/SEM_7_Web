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

public class AdminMenuState extends State {
    private Logger logger;

    public AdminMenuState(Logger logger) { this.logger = logger; }

    private int usersListForRecord(int nextState, EnumStates enumStates, UserView userView, IUserService userService,
                                   Update update) {
        ArrayList<User> usersList = null;
        try {
            usersList = userService.getUsersList();
        } catch (Exception e) {
            logger.error("Ошибка " + e + " при получении списка пользователей!");
        }

        if (usersList == null) {
            userView.usersListErrorMessage(update);
            userView.adminMenu(update);
            return enumStates.ADMIN_MENU_STATE;
        }
        else if (usersList.size() == 0) {
            userView.usersListEmptyMessage(update);
            userView.adminMenu(update);
            return enumStates.ADMIN_MENU_STATE;
        }
        else {
            userView.usersListMessage(update, usersList);
            userView.userForUpdateLoginInputMessage(update);
            return nextState;
        }
    }

    private int usersListForUpdate(int nextState, EnumStates enumStates, UserView userView, IUserService userService,
                                   Update update, boolean flag) {
        ArrayList<User> usersList = null;
        try {
            usersList = userService.getUsersList();
        } catch (Exception e) {
            logger.error("Ошибка " + e + " при получении списка пользователей!");
        }

        if (usersList == null) {
            userView.usersListErrorMessage(update);
            userView.adminMenu(update);
            return enumStates.ADMIN_MENU_STATE;
        }
        else if (usersList.size() == 0) {
            userView.usersListEmptyMessage(update);
            userView.adminMenu(update);
            return enumStates.ADMIN_MENU_STATE;
        }
        else {
            userView.usersListMessage(update, usersList);
            if (flag)
                userView.userForDeleteLoginInputMessage(update);
            else
                userView.userForUpdateLoginInputMessage(update);
            return nextState;
        }
    }

    private int doctorsListForUpdate(int nextState, IDoctorService doctorService, DoctorView doctorView,
                                     UserView userView, Update update, EnumStates enumStates) {
        ArrayList<Doctor> doctorsList = null;
        try {
            doctorsList = doctorService.getDoctorsList();
        } catch (Exception e) {
            logger.error("Ошибка " + e + " при получении списка врачей!");
        }

        if (doctorsList == null) {
            doctorView.doctorsListErrorMessage(update);
            userView.adminMenu(update);
            return enumStates.ADMIN_MENU_STATE;
        }
        else if (doctorsList.size() == 0) {
            doctorView.doctorsListEmptyMessage(update);
            userView.adminMenu(update);
            return enumStates.ADMIN_MENU_STATE;
        }
        else {
            doctorView.doctorsListForUpdateMessage(doctorsList, update);
            doctorView.doctorIdInputMessage(update);
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

            userView.adminMenu(update);
            return state;
        }
        else if (update.hasCallbackQuery() && update.getCallbackQuery().getData().equals("updateMyAccount")) {
            userTmp.setId(user.getId());
            userView.userNewDataInputMessage(update);
            userView.userFirstNameInputMessage(update);
            return enumStates.UPDATE_ACCOUNT_FIRST_NAME_SCAN_STATE;
        }
        else if (update.hasCallbackQuery() && update.getCallbackQuery().getData().equals("addRecord")) {
            return usersListForRecord(enumStates.ADD_RECORD_USER_LOGIN_SCAN_STATE, enumStates,
                    userView, userService, update);
        }
        else if (update.hasCallbackQuery() && update.getCallbackQuery().getData().equals("deleteRecord")) {
            return usersListForRecord(enumStates.DELETE_RECORD_USER_LOGIN_SCAN_STATE, enumStates,
                    userView, userService, update);
        }
        else if (update.hasCallbackQuery() && update.getCallbackQuery().getData().equals("updateRecord")) {
            return usersListForRecord(enumStates.UPDATE_RECORD_USER_LOGIN_SCAN_STATE, enumStates,
                    userView, userService, update);
        }
        else if (update.hasCallbackQuery() && update.getCallbackQuery().getData().equals("addUser")) {
            userView.userFirstNameInputMessage(update);
            return enumStates.ADD_USER_FIRST_NAME_SCAN_STATE;
        }
        else if (update.hasCallbackQuery() && update.getCallbackQuery().getData().equals("deleteUser")) {
            return usersListForUpdate(enumStates.USER_FOR_DELETE_LOGIN_SCAN_STATE, enumStates, userView,
                    userService, update, true);
        }
        else if (update.hasCallbackQuery() && update.getCallbackQuery().getData().equals("updateUser")) {
            return usersListForUpdate(enumStates.USER_FOR_UPDATE_LOGIN_SCAN_STATE, enumStates, userView,
                    userService, update, false);
        }
        else if (update.hasCallbackQuery() && update.getCallbackQuery().getData().equals("addDoctor")) {
            doctorView.doctorFirstNameInputMessage(update);
            return enumStates.ADD_DOCTOR_FIRST_NAME_SCAN_STATE;
        }
        else if (update.hasCallbackQuery() && update.getCallbackQuery().getData().equals("deleteDoctor")) {
            return doctorsListForUpdate(enumStates.DOCTOR_FOR_DELETE_ID_SCAN_STATE, doctorService, doctorView,
                    userView, update, enumStates);
        }
        else if (update.hasCallbackQuery() && update.getCallbackQuery().getData().equals("updateDoctor")) {
            return doctorsListForUpdate(enumStates.DOCTOR_FOR_UPDATE_ID_SCAN_STATE, doctorService, doctorView,
                    userView, update, enumStates);
        }

        return state;
    }
}
