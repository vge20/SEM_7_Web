package com.baeldung.View;

import com.baeldung.Doctor.Doctor;
import org.slf4j.Logger;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;

public class DoctorView extends TelegramLongPollingBot {
    private Logger logger;

    public DoctorView(Logger logger) {
        this.logger = logger;
    }

    private void sendText(String text, Update update) {
        SendMessage message = new SendMessage();
        if (update.hasCallbackQuery())
            message.setChatId(update.getCallbackQuery().getMessage().getChatId().toString());
        else
            message.setChatId(update.getMessage().getChatId().toString());
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            logger.error("Ошибка " + e + " при отправке сообщения!");
        }
    }

    public void doctorsListMessage(Update update, ArrayList<Doctor> doctorsList) {
        String textMessage = "";
        for (int i = 0; i < doctorsList.size(); i++)
            textMessage += doctorsList.get(i).getFirstName() + " " +
                    doctorsList.get(i).getLastName() + " " + doctorsList.get(i).getSpecialization() + "\n";

        sendText(textMessage, update);
    }

    public void doctorsListErrorMessage(Update update) {
        sendText("Не удалось получить список врачей!", update);
    }

    public void doctorsListEmptyMessage(Update update) {
        sendText("Список врачей пуст на данный момент!", update);
    }

    public void doctorFirstNameInputMessage(Update update) {
        sendText("Введите имя:", update);
    }

    public void doctorLastNameInputMessage(Update update) {
        sendText("Введите фамилию:", update);
    }

    public void doctorGenderInputMessage(Update update) {
        sendText("Если женщина введите 'ж', если мужчина 'м':", update);
    }

    public void doctorIncorrectInputMessage(Update update) {
        sendText("Некорректный ввод!", update);
    }

    public void doctorSpecializationInputMessage(Update update) {
        sendText("Введите специализацию:", update);
    }

    public void addDoctorSuccessMessage(Update update) {
        sendText("Врач добавлен!", update);
    }

    public void addDoctorErrorMessage(Update update) {
        sendText("Невозможно добавить врача с такими данными!", update);
    }

    public void doctorIdInputMessage(Update update) {
        sendText("Введите идентификатор нужного врача:", update);
    }

    public void doctorsListForUpdateMessage(ArrayList<Doctor> doctorsList, Update update) {
        String messageText = "";
        for (int i = 0; i < doctorsList.size(); i++)
            messageText += doctorsList.get(i).getFirstName() + " " + doctorsList.get(i).getLastName() + " "
                    + doctorsList.get(i).getSpecialization() + " " + doctorsList.get(i).getId() + "\n";
        sendText(messageText, update);
    }

    public void deleteDoctorSuccessMessage(Update update) {
        sendText("Врач удалён!", update);
    }

    public void deleteDoctorErrorMessage(Update update) {
        sendText("Не удалось удалить врача с выбранным id!", update);
    }

    public void updateDoctorSuccessMessage(Update update) {
        sendText("Врач изменён!", update);
    }

    public void updateDoctorErrorMessage(Update update) {
        sendText("Не удалось изменить врача на такие данные!", update);
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
