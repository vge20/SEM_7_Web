package com.baeldung.StateMachine.States.Registration;

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

public class RegistrationPasswordScanState extends State {
    private Logger logger;

    public RegistrationPasswordScanState(Logger logger) { this.logger = logger; }

    @Override
    public int updateHandler(int state, EnumStates enumStates, Update update, User user, User userTmp, Doctor doctorTmp,
                             Record recordTmp, IUserService userService, IDoctorService doctorService,
                             IRecordService recordService, UserView userView, DoctorView doctorView, RecordView recordView) {

        if (update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().equals("/start")) {
            userView.startUserMenu(update);
            return enumStates.START_MENU_STATE;
        }
        else if (update.hasMessage() && update.getMessage().hasText()) {
            user.setPassword(update.getMessage().getText());
            User tmpUser = null;
            boolean flag = false;

            try {
                flag = userService.addUser(user);
                tmpUser = userService.getUserByLogin(user.getLogin());
            } catch (Exception e) {
                logger.error("Ошибка " + e + " при добавлении пользователя!");
            }

            if (tmpUser == null || !flag)
                userView.registrationErrorMessage(update);
            else
                userView.registrationSuccessMessage(update);

            if (tmpUser != null && flag) {
                user.setId(tmpUser.getId());
                userView.userMenu(update);
                return enumStates.USER_MENU_STATE;
            }
            else {
                userView.startUserMenu(update);
                return enumStates.START_MENU_STATE;
            }
        }

        return state;
    }
}
