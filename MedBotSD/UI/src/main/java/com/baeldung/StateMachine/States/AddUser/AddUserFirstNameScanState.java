package com.baeldung.StateMachine.States.AddUser;

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

public class AddUserFirstNameScanState extends State {
    private Logger logger;

    public AddUserFirstNameScanState(Logger logger) { this.logger = logger; }

    @Override
    public int updateHandler(int state, EnumStates enumStates, Update update, User user, User userTmp, Doctor doctorTmp,
                             Record recordTmp, IUserService userService, IDoctorService doctorService,
                             IRecordService recordService, UserView userView, DoctorView doctorView, RecordView recordView) {

        if (update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().equals("/start")) {
            userView.startUserMenu(update);
            return enumStates.START_MENU_STATE;
        }
        else if (update.hasMessage() && update.getMessage().hasText()) {
            userTmp.setFirstName(update.getMessage().getText());
            userView.userLastNameInputMessage(update);
            return enumStates.ADD_USER_LAST_NAME_SCAN_STATE;
        }

        return state;
    }
}
