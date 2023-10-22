package com.baeldung.StateMachine.States.ParentState;

import com.baeldung.Doctor.Doctor;
import com.baeldung.Doctor.IDoctorService;
import com.baeldung.Record.IRecordService;
import com.baeldung.Record.Record;
import com.baeldung.StateMachine.EnumStates.EnumStates;
import com.baeldung.User.IUserService;
import com.baeldung.User.User;
import com.baeldung.View.DoctorView;
import com.baeldung.View.RecordView;
import com.baeldung.View.UserView;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class State extends TelegramLongPollingBot {

    public int updateHandler(int state, EnumStates enumStates, Update update, User user, User userTmp, Doctor doctorTmp,
                             Record recordTmp, IUserService userService, IDoctorService doctorService,
                             IRecordService recordService, UserView userView, DoctorView doctorView, RecordView recordView) {
        return 0;
    }

    @Override
    public String getBotUsername() {
        return "Gleb123TestBot";
    }

    @Override
    public String getBotToken() {
        return "5986950292:AAETXSccuoG5rwijJtx2jv7x6KYStLca4_0";
    }

    @Override
    public void onUpdateReceived(Update update) {}
}
