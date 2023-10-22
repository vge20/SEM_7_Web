package com.baeldung.StateMachine.States.AddRecord;

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

import java.sql.Time;

public class AddRecordTimeScanState extends State {
    private Logger logger;

    public AddRecordTimeScanState(Logger logger) { this.logger = logger; }

    private void setTime(String startTime, String endTime, Record recordTmp) {
        recordTmp.setStartTime(Time.valueOf(startTime));
        recordTmp.setEndTime(Time.valueOf(endTime));
    }

    @Override
    public int updateHandler(int state, EnumStates enumStates, Update update, User user, User userTmp, Doctor doctorTmp,
                             Record recordTmp, IUserService userService, IDoctorService doctorService,
                             IRecordService recordService, UserView userView, DoctorView doctorView, RecordView recordView) {

        boolean setTimeFlag = false;
        if (update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().equals("/start")) {
            userView.startUserMenu(update);
            return enumStates.START_MENU_STATE;
        }
        else if (update.hasCallbackQuery() && update.getCallbackQuery().getData().equals("9:00-9:25")) {
            setTime("9:00:00", "9:25:00", recordTmp);
            setTimeFlag = true;
        }
        else if (update.hasCallbackQuery() && update.getCallbackQuery().getData().equals("9:30-9:55")) {
            setTime("9:30:00", "9:55:00", recordTmp);
            setTimeFlag = true;
        }
        else if (update.hasCallbackQuery() && update.getCallbackQuery().getData().equals("10:00-10:25")) {
            setTime("10:00:00", "10:25:00", recordTmp);
            setTimeFlag = true;
        }
        else if (update.hasCallbackQuery() && update.getCallbackQuery().getData().equals("10:30-10:55")) {
            setTime("10:30:00", "10:55:00", recordTmp);
            setTimeFlag = true;
        }
        else if (update.hasCallbackQuery() && update.getCallbackQuery().getData().equals("11:00-11:25")) {
            setTime("11:00:00", "11:25:00", recordTmp);
            setTimeFlag = true;
        }
        else if (update.hasCallbackQuery() && update.getCallbackQuery().getData().equals("11:30-11:55")) {
            setTime("11:30:00", "11:55:00", recordTmp);
            setTimeFlag = true;
        }
        else if (update.hasCallbackQuery() && update.getCallbackQuery().getData().equals("12:00-12:25")) {
            setTime("12:00:00", "12:25:00", recordTmp);
            setTimeFlag = true;
        }
        else if (update.hasCallbackQuery() && update.getCallbackQuery().getData().equals("12:30-12:55")) {
            setTime("12:30:00", "12:55:00", recordTmp);
            setTimeFlag = true;
        }
        else if (update.hasCallbackQuery() && update.getCallbackQuery().getData().equals("13:00-13:25")) {
            setTime("13:00:00", "13:25:00", recordTmp);
            setTimeFlag = true;
        }
        else if (update.hasCallbackQuery() && update.getCallbackQuery().getData().equals("13:30-13:55")) {
            setTime("13:30:00", "13:55:00", recordTmp);
            setTimeFlag = true;
        }
        else if (update.hasCallbackQuery() && update.getCallbackQuery().getData().equals("14:00-14:25")) {
            setTime("14:00:00", "14:25:00", recordTmp);
            setTimeFlag = true;
        }
        else if (update.hasCallbackQuery() && update.getCallbackQuery().getData().equals("14:30-14:55")) {
            setTime("14:30:00", "14:55:00", recordTmp);
            setTimeFlag = true;
        }
        else if (update.hasCallbackQuery() && update.getCallbackQuery().getData().equals("15:00-15:25")) {
            setTime("15:00:00", "15:25:00", recordTmp);
            setTimeFlag = true;
        }
        else if (update.hasCallbackQuery() && update.getCallbackQuery().getData().equals("15:30-15:55")) {
            setTime("15:30:00", "15:55:00", recordTmp);
            setTimeFlag = true;
        }
        else if (update.hasCallbackQuery() && update.getCallbackQuery().getData().equals("16:00-16:25")) {
            setTime("16:00:00", "16:25:00", recordTmp);
            setTimeFlag = true;
        }
        else if (update.hasCallbackQuery() && update.getCallbackQuery().getData().equals("16:30-16:55")) {
            setTime("16:30:00", "16:55:00", recordTmp);
            setTimeFlag = true;
        }
        else if (update.hasCallbackQuery() && update.getCallbackQuery().getData().equals("17:00-17:25")) {
            setTime("17:00:00", "17:25:00", recordTmp);
            setTimeFlag = true;
        }
        else if (update.hasCallbackQuery() && update.getCallbackQuery().getData().equals("17:30-17:55")) {
            setTime("17:30:00", "17:55:00", recordTmp);
            setTimeFlag = true;
        }

        if (setTimeFlag) {
            boolean flag = false;
            try {
                flag = recordService.addRecord(recordTmp);
            } catch (Exception e) {
                logger.error("Ошибка " + e + " при добавлении записи!");
            }

            if (flag)
                recordView.addRecordSuccessMessage(update);
            else
                recordView.addRecordErrorMessage(update);

            if (user.getPrivilegeLevel() == 1) {
                userView.adminMenu(update);
                return enumStates.ADMIN_MENU_STATE;
            }
            else {
                userView.userMenu(update);
                return enumStates.USER_MENU_STATE;
            }
        }

        return state;
    }
}
