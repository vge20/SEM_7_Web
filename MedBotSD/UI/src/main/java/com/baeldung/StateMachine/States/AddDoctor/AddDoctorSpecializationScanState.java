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

public class AddDoctorSpecializationScanState extends State {
    private Logger logger;

    public AddDoctorSpecializationScanState(Logger logger) { this.logger = logger; }

    @Override
    public int updateHandler(int state, EnumStates enumStates, Update update, User user, User userTmp, Doctor doctorTmp,
                             Record recordTmp, IUserService userService, IDoctorService doctorService,
                             IRecordService recordService, UserView userView, DoctorView doctorView, RecordView recordView) {

        if (update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().equals("/start")) {
            userView.startUserMenu(update);
            return enumStates.START_MENU_STATE;
        }
        else if (update.hasMessage() && update.getMessage().hasText()) {
            doctorTmp.setSpecialization(update.getMessage().getText());

            boolean flag = false;
            try {
                flag = doctorService.addDoctor(doctorTmp);
            } catch (Exception e) {
                logger.error("Ошибка " + e + " при добавлении врача!");
                flag = false;
            }
            if (flag)
                doctorView.addDoctorSuccessMessage(update);
            else
                doctorView.addDoctorErrorMessage(update);

            userView.adminMenu(update);
            return enumStates.ADMIN_MENU_STATE;
        }

        return state;
    }
}