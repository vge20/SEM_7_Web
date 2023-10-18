package com.baeldung.StateMachine.States.DeleteRecord;

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

public class DeleteRecordUserLoginScanState extends State {
    private Logger logger;

    public DeleteRecordUserLoginScanState(Logger logger) { this.logger = logger; }

    @Override
    public int updateHandler(int state, EnumStates enumStates, Update update, User user, User userTmp, Doctor doctorTmp,
                             Record recordTmp, IUserService userService, IDoctorService doctorService,
                             IRecordService recordService, UserView userView, DoctorView doctorView, RecordView recordView) {

        if (update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().equals("/start")) {
            userView.startUserMenu(update);
            return enumStates.START_MENU_STATE;
        }
        else if (update.hasMessage() && update.getMessage().hasText()) {
            User tmpUser = null;
            try {
                tmpUser = userService.getUserByLogin(update.getMessage().getText());
            } catch (Exception e) {
                logger.error("Ошибка " + e + " при получении пользователя по логину!");
            }

            if (tmpUser == null) {
                userView.getUserByLoginErrorMessage(update);
                userView.adminMenu(update);
                return enumStates.ADMIN_MENU_STATE;
            }
            else {
                recordTmp.setIdUser(tmpUser.getId());
                ArrayList<Doctor> doctorsList = null;
                try {
                    doctorsList = doctorService.getDoctorsList();
                } catch (Exception e) {
                    logger.error("Ошибка " + e + " при получении списка врачей!");
                }

                if (doctorsList == null) {
                    doctorView.doctorsListErrorMessage(update);
                    userView.userMenu(update);
                    return enumStates.ADMIN_MENU_STATE;
                }
                else if (doctorsList.size() == 0) {
                    doctorView.doctorsListEmptyMessage(update);
                    userView.userMenu(update);
                    return enumStates.ADMIN_MENU_STATE;
                }
                else {
                    doctorView.doctorsListForUpdateMessage(doctorsList, update);
                    doctorView.doctorIdInputMessage(update);
                    return enumStates.DELETE_RECORD_DOCTOR_ID_SCAN_STATE;
                }
            }
        }

        return state;
    }
}
