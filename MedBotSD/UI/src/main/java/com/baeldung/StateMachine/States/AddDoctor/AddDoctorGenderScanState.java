package com.baeldung.StateMachine.States.AddDoctor;

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

public class AddDoctorGenderScanState extends State {
    private Logger logger;

    public AddDoctorGenderScanState(Logger logger) { this.logger = logger; }

    @Override
    public int updateHandler(int state, EnumStates enumStates, Update update, User user, User userTmp, Doctor doctorTmp,
                             Record recordTmp, IUserService userService, IDoctorService doctorService,
                             IRecordService recordService, UserView userView, DoctorView doctorView, RecordView recordView) {

        if (update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().equals("/start")) {
            userView.startUserMenu(update);
            return enumStates.START_MENU_STATE;
        }
        else if (update.hasMessage() && update.getMessage().hasText()) {
            String gender = update.getMessage().getText();
            if (gender.equals("ж")) {
                doctorTmp.setGender(false);
                doctorView.doctorSpecializationInputMessage(update);
                return enumStates.ADD_DOCTOR_SPECIALIZATION_SCAN_STATE;
            }
            else if (gender.equals("м")) {
                doctorTmp.setGender(true);
                doctorView.doctorSpecializationInputMessage(update);
                return enumStates.ADD_DOCTOR_SPECIALIZATION_SCAN_STATE;
            }
            else {
                doctorView.doctorIncorrectInputMessage(update);
                userView.adminMenu(update);
                return enumStates.ADMIN_MENU_STATE;
            }
        }

        return state;
    }
}
