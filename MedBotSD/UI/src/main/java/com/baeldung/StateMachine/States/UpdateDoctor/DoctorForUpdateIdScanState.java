package com.baeldung.StateMachine.States.UpdateDoctor;

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

public class DoctorForUpdateIdScanState extends State {
    private Logger logger;

    public DoctorForUpdateIdScanState(Logger logger) { this.logger = logger; }

    @Override
    public int updateHandler(int state, EnumStates enumStates, Update update, User user, User userTmp, Doctor doctorTmp,
                             Record recordTmp, IUserService userService, IDoctorService doctorService,
                             IRecordService recordService, UserView userView, DoctorView doctorView, RecordView recordView) {

        if (update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().equals("/start")) {
            userView.startUserMenu(update);
            return enumStates.START_MENU_STATE;
        }
        else if (update.hasMessage() && update.getMessage().hasText()) {
            int id;
            try {
                id = Integer.parseInt(update.getMessage().getText());
            } catch (Exception e) {
                logger.error("Ошибка " + e + " при чтении id врача!");
                id = -1;
            }
            ArrayList<Doctor> doctorsList;
            boolean flag = false;
            try {
                doctorsList = doctorService.getDoctorsList();
                for (int i = 0; i < doctorsList.size(); i++)
                    if (doctorsList.get(i).getId() == id)
                        flag = true;
            } catch (Exception e) {
                logger.error("Ошибка " + e + " при получении списка врачей!");
            }
            if (!flag) {
                id = -1;
            }

            if (id < 0) {
                doctorView.doctorIncorrectInputMessage(update);
                userView.adminMenu(update);
                return enumStates.ADMIN_MENU_STATE;
            }
            else {
                doctorTmp.setId(id);
                doctorView.doctorFirstNameInputMessage(update);
                return enumStates.UPDATE_DOCTOR_FIRST_NAME_SCAN_STATE;
            }
        }

        return state;
    }
}
