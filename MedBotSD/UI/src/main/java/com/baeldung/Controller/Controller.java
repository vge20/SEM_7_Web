package com.baeldung.Controller;

import com.baeldung.StateMachine.Machine.StateMachine;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Controller extends TelegramLongPollingBot {

    private StateMachine stateMachine;

    public Controller() {
        stateMachine = new StateMachine();
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
    public void onUpdateReceived(Update update) {
        stateMachine.updateHandler(update);
    }
}
