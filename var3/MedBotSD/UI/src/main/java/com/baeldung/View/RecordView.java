package com.baeldung.View;

import org.slf4j.Logger;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class RecordView extends TelegramLongPollingBot {
    private Logger logger;
    public RecordView(Logger logger) {
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

    public void recordDateInputMessage(Update update) {
        sendText("Введите дату записи в формате '1985-04-23':", update);
    }

    public void recordTimeInputMessage(Update update) {
        SendMessage message = new SendMessage();
        if (update.hasMessage())
            message.setChatId(update.getMessage().getChatId().toString());
        else
            message.setChatId(update.getCallbackQuery().getMessage().getChatId().toString());
        message.setText("Выберите время:");

        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> buttons1 = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("9:00-9:25");
        button.setCallbackData("9:00-9:25");
        buttons1.add(button);
        button = new InlineKeyboardButton();
        button.setText("9.30-9.55");
        button.setCallbackData("9:30-9:55");
        buttons1.add(button);
        buttons.add(buttons1);
        List<InlineKeyboardButton> buttons2 = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText("10:00-10:25");
        button.setCallbackData("10:00-10:25");
        buttons2.add(button);
        button = new InlineKeyboardButton();
        button.setText("10:30-10:55");
        button.setCallbackData("10:30-10:55");
        buttons2.add(button);
        buttons.add(buttons2);
        List<InlineKeyboardButton> buttons3 = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText("11:00-11:25");
        button.setCallbackData("11:00-11:25");
        buttons3.add(button);
        button = new InlineKeyboardButton();
        button.setText("11:30-11:55");
        button.setCallbackData("11:30-11:55");
        buttons3.add(button);
        buttons.add(buttons3);
        List<InlineKeyboardButton> buttons4 = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText("12:00-12:25");
        button.setCallbackData("12:00-12:25");
        buttons4.add(button);
        button = new InlineKeyboardButton();
        button.setText("12:30-12:55");
        button.setCallbackData("12:30-12:55");
        buttons4.add(button);
        buttons.add(buttons4);
        List<InlineKeyboardButton> buttons5 = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText("13:00-13:25");
        button.setCallbackData("13:00-13:25");
        buttons5.add(button);
        button = new InlineKeyboardButton();
        button.setText("13:30-13:55");
        button.setCallbackData("13:30-13:55");
        buttons5.add(button);
        buttons.add(buttons5);
        List<InlineKeyboardButton> buttons6 = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText("14:00-14:25");
        button.setCallbackData("14:00-14:25");
        buttons6.add(button);
        button = new InlineKeyboardButton();
        button.setText("14:30-14:55");
        button.setCallbackData("14:30-14:55");
        buttons6.add(button);
        buttons.add(buttons6);
        List<InlineKeyboardButton> buttons7 = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText("15:00-15:25");
        button.setCallbackData("15:00-15:25");
        buttons7.add(button);
        button = new InlineKeyboardButton();
        button.setText("15:30-15:55");
        button.setCallbackData("15:30-15:55");
        buttons7.add(button);
        buttons.add(buttons7);
        List<InlineKeyboardButton> buttons8 = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText("16:00-16:25");
        button.setCallbackData("16:00-16:25");
        buttons8.add(button);
        button = new InlineKeyboardButton();
        button.setText("16:30-16:55");
        button.setCallbackData("16:30-16:55");
        buttons8.add(button);
        buttons.add(buttons8);
        List<InlineKeyboardButton> buttons9 = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText("17:00-17:25");
        button.setCallbackData("17:00-17:25");
        buttons9.add(button);
        button = new InlineKeyboardButton();
        button.setText("17:30-17:55");
        button.setCallbackData("17:30-17:55");
        buttons9.add(button);
        buttons.add(buttons9);

        InlineKeyboardMarkup markupKeyboard = new InlineKeyboardMarkup();
        markupKeyboard.setKeyboard(buttons);
        message.setReplyMarkup(markupKeyboard);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            logger.error("Ошибка " + e + " при отправке сообщения!");
        }
    }

    public void inputFormatErrorMessage(Update update) {
        sendText("Некорректный формат ввода!", update);
    }

    public void addRecordErrorMessage(Update update) {
        sendText("Запись с такими параметрами добавить невозможно!", update);
    }

    public void addRecordSuccessMessage(Update update) {
        sendText("Запись добавлена!", update);
    }

    public void deleteRecordErrorMessage(Update update) {
        sendText("Запись с такими параметрами удалить невозможно!", update);
    }

    public void deleteRecordSuccessMessage(Update update) {
        sendText("Запись удалена!", update);
    }

    public void updateRecordErrorMessage(Update update) {
        sendText("Таким образом изменить запись невозможно!", update);
    }

    public void updateRecordSuccessMessage(Update update) {
        sendText("Запись изменена!", update);
    }

    public void getRecordErrorMessage(Update update) {
        sendText("Такая запись не найдена!", update);
    }

    public void getRecordSuccessMessage(Update update) {
        sendText("Запись найдена!", update);
    }

    public void newDataInputMessage(Update update) {
        sendText("Ниже введите новые данные:", update);
    }

    public void thereAreNotRecordMessage(Update update) {
        sendText("Не найдено такой записи!", update);
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
