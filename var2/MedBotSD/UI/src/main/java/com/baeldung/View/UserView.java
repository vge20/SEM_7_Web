package com.baeldung.View;

import com.baeldung.User.User;
import org.slf4j.Logger;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class UserView extends TelegramLongPollingBot {
    private Logger logger;
    public UserView(Logger logger) {
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

    public void startUserMenu(Update update) {
        SendMessage message = new SendMessage();
        if (update.hasMessage())
            message.setChatId(update.getMessage().getChatId().toString());
        else
            message.setChatId(update.getCallbackQuery().getMessage().getChatId().toString());
        message.setText("Выберите пункт меню:");

        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> buttons1 = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("Войти в систему");
        button.setCallbackData("unregLogIn");
        buttons1.add(button);
        buttons.add(buttons1);
        List<InlineKeyboardButton> buttons2 = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText("Зарегистрироваться");
        button.setCallbackData("unregRegistration");
        buttons2.add(button);
        buttons.add(buttons2);
        List<InlineKeyboardButton> buttons3 = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText("Посмотреть список врачей");
        button.setCallbackData("doctorsList");
        buttons3.add(button);
        buttons.add(buttons3);

        InlineKeyboardMarkup markupKeyboard = new InlineKeyboardMarkup();
        markupKeyboard.setKeyboard(buttons);
        message.setReplyMarkup(markupKeyboard);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            logger.error("Ошибка " + e + " при отправке сообщения!");
        }
    }

    public void userMenu(Update update) {
        SendMessage message = new SendMessage();
        if (update.hasMessage())
            message.setChatId(update.getMessage().getChatId().toString());
        else
            message.setChatId(update.getCallbackQuery().getMessage().getChatId().toString());
        message.setText("Выберите пункт меню:");

        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> buttons1 = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("Посмотреть список врачей");
        button.setCallbackData("doctorsList");
        buttons1.add(button);
        buttons.add(buttons1);
        List<InlineKeyboardButton> buttons2 = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText("Добавить запись на приём");
        button.setCallbackData("addRecord");
        buttons2.add(button);
        buttons.add(buttons2);
        List<InlineKeyboardButton> buttons3 = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText("Удалить запись на приём");
        button.setCallbackData("deleteRecord");
        buttons3.add(button);
        buttons.add(buttons3);
        List<InlineKeyboardButton> buttons4 = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText("Изменить запись на приём");
        button.setCallbackData("updateRecord");
        buttons4.add(button);
        buttons.add(buttons4);
        List<InlineKeyboardButton> buttons5 = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText("Изменить данные своего аккаунта");
        button.setCallbackData("updateMyAccount");
        buttons5.add(button);
        buttons.add(buttons5);

        InlineKeyboardMarkup markupKeyboard = new InlineKeyboardMarkup();
        markupKeyboard.setKeyboard(buttons);
        message.setReplyMarkup(markupKeyboard);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            logger.error("Ошибка " + e + " при отправке сообщения!");
        }
    }

    public void adminMenu(Update update) {
        SendMessage message = new SendMessage();
        if (update.hasMessage())
            message.setChatId(update.getMessage().getChatId().toString());
        else
            message.setChatId(update.getCallbackQuery().getMessage().getChatId().toString());
        message.setText("Выберите пункт меню:");

        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> buttons1 = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("Посмотреть список врачей");
        button.setCallbackData("doctorsList");
        buttons1.add(button);
        buttons.add(buttons1);
        List<InlineKeyboardButton> buttons2 = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText("Добавить запись на приём");
        button.setCallbackData("addRecord");
        buttons2.add(button);
        buttons.add(buttons2);
        List<InlineKeyboardButton> buttons3 = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText("Удалить запись на приём");
        button.setCallbackData("deleteRecord");
        buttons3.add(button);
        buttons.add(buttons3);
        List<InlineKeyboardButton> buttons4 = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText("Изменить запись на приём");
        button.setCallbackData("updateRecord");
        buttons4.add(button);
        buttons.add(buttons4);
        List<InlineKeyboardButton> buttons5 = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText("Изменить данные своего аккаунта");
        button.setCallbackData("updateMyAccount");
        buttons5.add(button);
        buttons.add(buttons5);
        List<InlineKeyboardButton> buttons6 = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText("Добавить врача");
        button.setCallbackData("addDoctor");
        buttons6.add(button);
        buttons.add(buttons6);
        List<InlineKeyboardButton> buttons7 = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText("Удалить врача");
        button.setCallbackData("deleteDoctor");
        buttons7.add(button);
        buttons.add(buttons7);
        List<InlineKeyboardButton> buttons8 = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText("Изменить врача");
        button.setCallbackData("updateDoctor");
        buttons8.add(button);
        buttons.add(buttons8);
        List<InlineKeyboardButton> buttons9 = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText("Добавить акаунт пользователя");
        button.setCallbackData("addUser");
        buttons9.add(button);
        buttons.add(buttons9);
        List<InlineKeyboardButton> buttons10 = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText("Удалить акаунт пользователя");
        button.setCallbackData("deleteUser");
        buttons10.add(button);
        buttons.add(buttons10);
        List<InlineKeyboardButton> buttons11 = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText("Изменить акаунт пользователя");
        button.setCallbackData("updateUser");
        buttons11.add(button);
        buttons.add(buttons11);

        InlineKeyboardMarkup markupKeyboard = new InlineKeyboardMarkup();
        markupKeyboard.setKeyboard(buttons);
        message.setReplyMarkup(markupKeyboard);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            logger.error("Ошибка " + e + " при отправке сообщения!");
        }
    }

    public void userLogInInputMessage(Update update) {
        sendText("Введите логин:", update);
    }

    public void userLoginInputMessage(Update update) {
        sendText("Введите логин:", update);
    }

    public void userPasswordInputMessage(Update update) {
        sendText("Введите пароль:", update);
    }

    public void userFirstNameInputMessage(Update update) {
        sendText("Введите имя:", update);
    }

    public void userLastNameInputMessage(Update update) {
        sendText("Введите фамилию:", update);
    }

    public void userGenderInputMessage(Update update) {
        sendText("Если женщина введите 'ж', если мужчина 'м':", update);
    }

    public void userBirthDateInputMessage(Update update) {
        sendText("Введите дату рождения в формате '1985-04-23':", update);
    }

    public void userForDeleteLoginInputMessage(Update update) {
        sendText("Введите логин пользователя, аккаунт которого нужно удалить:", update);
    }

    public void userForUpdateLoginInputMessage(Update update) {
        sendText("Введите логин нужного пользователя:", update);
    }

    public void userNewDataInputMessage(Update update) {
        sendText("Введите новые данные аккаунта пользователя ниже.", update);
    }

    public void logInErrorMessage(Update update) {
        sendText("Неверный логин или пароль!", update);
    }

    public void logInSuccessMessage(Update update) {
        sendText("Вы успешно вошли в акканут!", update);
    }

    public void inputFormatErrorMessage(Update update) {
        sendText("Неверный формат ввода!", update);
    }

    public void registrationErrorMessage(Update update) {
        sendText("Не удалось добавить аккаунт с такими данными!", update);
    }

    public void registrationSuccessMessage(Update update) {
        sendText("Аккаунт успешно добавлен!", update);
    }

    public void usersListErrorMessage(Update update) {
        sendText("Не удалось получить список пользователей!", update);
    }

    public void usersListEmptyMessage(Update update) {
        sendText("Список пользователей пуст на данный момент!", update);
    }

    public void usersListMessage(Update update, ArrayList<User> usersList) {
        String textMessage = "";
        for (int i = 0; i < usersList.size(); i++)
            textMessage += usersList.get(i).getFirstName() + " " + usersList.get(i).getLastName() + " "
                    + usersList.get(i).getLogin() + "\n";

        sendText(textMessage, update);
    }

    public void deleteUserErrorMessage(Update update) {
        sendText("Невозможно удалить пользователя с таким логином!", update);
    }

    public void updateUserErrorMessage(Update update) {
        sendText("Невозможно изменить аккаунт пользователя на такие данные!", update);
    }

    public void updateUserSuccessMessage(Update update) {
        sendText("Аккаунт пользователя изменён!", update);
    }

    public void getUserByLoginErrorMessage(Update update) {
        sendText("Нет пользователя с таким логином!", update);
    }

    public void deleteUserSuccessMessage(Update update) {
        sendText("Пользователь удалён!", update);
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

