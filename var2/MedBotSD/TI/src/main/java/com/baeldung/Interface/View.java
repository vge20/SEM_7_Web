package com.baeldung.Interface;

public class View {
    private final String USER_GREETING = "Здравствуйте, медицинский бот рад вас видеть!";
    private final String BASE_MENU_FOR_UNREG = "Войти в систему - введите '1';\n" +
            "зарегистрироваться - введите '2';\n" +
            "посмотреть список врачей - введите '3'.\n";
    private final String BASE_MENU_FOR_USER = "Посмотреть список врачей - введите '1';\n" +
            "добавить запись на приём - введите '2';\n" +
            "удалить запись на приём - введите '3';\n" +
            "изменить запись на приём - введите '4';\n" +
            "изменить данные своего аккаунта - введите '5'.\n";
    private final String BASE_MENU_FOR_ADMIN = "Посмотреть список врачей - введите '1';\n" +
            "добавить запись на приём - введите '2';\n" +
            "удалить запись на приём - введите '3';\n" +
            "изменить запись на приём - введите '4';\n" +
            "изменить данные своего аккаунта - введите '5';\n" +
            "добавить врача - введите '6';\n" +
            "удалить врача - введите '7';\n" +
            "изменить врача - введите '8';\n" +
            "добавить аккаунт пользователя - введите '9';\n" +
            "удалить аккаунт пользователя - введите '10';\n" +
            "изменить аккаунт пользователя - введите '11'.\n";
    private final String INVITE_TO_LOGIN_INPUT = "Введите ваш логин: ";
    private final String INVITE_TO_PASSWORD_INPUT = "Введите ваш пароль: ";
    private final String ERROR_LOG_IN_MESSAGE = "Неверный логин или пароль!\n";
    private final String INVITE_TO_FIRST_NAME_INPUT = "Введите ваше имя: ";
    private final String INVITE_TO_LAST_NAME_INPUT = "Введите вашу фамилию: ";
    private final String INVITE_TO_GENDER_INPUT = "Если вы женщина введите 'ж', если мужчина 'м': ";
    private final String INVITE_TO_DATE_BIRTH_INPUT = "Введите дату вашего рождения в формате '1985-04-23': ";
    private final String INCORRECT_LOGIN_INPUT_MESSAGE = "Некорректный логин!\n";
    private final String INCORRECT_PASSWORD_INPUT_MESSAGE = "Некорректный пароль!\n";
    private final String INCORRECT_FIRST_NAME_INPUT_MESSAGE = "Некорректное имя!\n";
    private final String INCORRECT_LAST_NAME_INPUT_MESSAGE = "Некорректная фамилия!\n";
    private final String INCORRECT_GENDER_INPUT_MESSAGE = "Некорректный ввод пола!\n";
    private final String INCORRECT_DATE_BIRTH_INPUT_MESSAGE = "Некорректная дата рождения!\n";
    private final String INCORRECT_MENU_CHOICE_MESSAGE = "Некорректный выбор пункта меню!\n";
    private final String ADD_USER_ERROR_MESSAGE = "Пользователя с такими параметрами добавить невозмножно,\n" +
            "поробуйте указать другой логин.\n";
    private final String INVITE_TO_DOCTOR_SPECIALIZATION_INPUT = "Введите интересующую специалзацию доктора: ";
    private final String INCORRECT_DOCTOR_SPECIALIZATION_INPUT_MESSAGE = "Некорректная специализация доктора!\n";
    private final String DOCTORS_LIST_BY_SPECIALIZATION_BLANK = "Список врачей пуст!\n";
    private final String USER_ACCOUNT_UPDATE_ERROR_MESSAGE = "Обновить аккаунт пользователя на такие данные " +
            "невозможно!\n";
    private final String INVITE_TO_UPDATE_USER_PARAMETERS = "Ниже введите новые данные своего аккаунта.\n";
    private final String INVITE_TO_DOCTOR_FIRST_NAME_INPUT = "Введите имя врача: ";
    private final String INVITE_TO_DOCTOR_LAST_NAME_INPUT = "Введите фамилию врача: ";
    public void outputUserGreeting() {
        System.out.println(USER_GREETING);
    }

    public void outputBaseMenuForUnreg() {
        System.out.println(BASE_MENU_FOR_UNREG);
    }

    public void outputInviteToLoginInput() {
        System.out.println(INVITE_TO_LOGIN_INPUT);
    }

    public void outputInviteToPasswordInput() {
        System.out.println(INVITE_TO_PASSWORD_INPUT);
    }
    public void outputInviteToFirstNameInput() {
        System.out.println(INVITE_TO_FIRST_NAME_INPUT);
    }
    public void outputInviteToLastNameInput() {
        System.out.println(INVITE_TO_LAST_NAME_INPUT);
    }
    public void outputInviteToGenderInput() {
        System.out.println(INVITE_TO_GENDER_INPUT);
    }
    public void outputInviteToDateBirthInput() {
        System.out.println(INVITE_TO_DATE_BIRTH_INPUT);
    }
    public void outputErrorLogInMessage() {
        System.out.println(ERROR_LOG_IN_MESSAGE);
    }
    public void outputIncorrectLoginInputMessage() {
        System.out.println(INCORRECT_LOGIN_INPUT_MESSAGE);
    }
    public void outputIncorrectPasswordInputMessage() {
        System.out.println(INCORRECT_PASSWORD_INPUT_MESSAGE);
    }
    public void outputIncorrectFirstNameInputMessage() {
        System.out.println(INCORRECT_FIRST_NAME_INPUT_MESSAGE);
    }
    public void outputIncorrectLastNameInputMessage() {
        System.out.println(INCORRECT_LAST_NAME_INPUT_MESSAGE);
    }
    public void outputIncorrectGenderInputMessage() {
        System.out.println(INCORRECT_GENDER_INPUT_MESSAGE);
    }
    public void outputIncorrectDateBirthInputMessage() {
        System.out.println(INCORRECT_DATE_BIRTH_INPUT_MESSAGE);
    }
    public void outputAddUserErrorMessage() {
        System.out.println(ADD_USER_ERROR_MESSAGE);
    }
    public void outputIncorrectMenuChoiceMessage() {
        System.out.println(INCORRECT_MENU_CHOICE_MESSAGE);
    }
    public void outputInviteToDoctorSpecializationInput() {
        System.out.println(INVITE_TO_DOCTOR_SPECIALIZATION_INPUT);
    }
    public void outputIncorrectDoctorSpecializationInputMessage() {
        System.out.println(INCORRECT_DOCTOR_SPECIALIZATION_INPUT_MESSAGE);
    }
    public void outputDoctorsListBySpecializationBlank() {
        System.out.println(DOCTORS_LIST_BY_SPECIALIZATION_BLANK);
    }
    public void outputBaseMenuForUser() {
        System.out.println(BASE_MENU_FOR_USER);
    }
    public void outputBaseMenuForAdmin() {
        System.out.println(BASE_MENU_FOR_ADMIN);
    }
    public void outputUserAccountUpdateErrorMessage() {
        System.out.println(USER_ACCOUNT_UPDATE_ERROR_MESSAGE);
    }
    public void outputInviteToUpdateUserParameters() {
        System.out.println(INVITE_TO_UPDATE_USER_PARAMETERS);
    }
    public void outputInviteToDoctorFirstNameInput() {
        System.out.println(INVITE_TO_DOCTOR_FIRST_NAME_INPUT);
    }
    public void outputInviteToDoctorLastNameInput() {
        System.out.println(INVITE_TO_DOCTOR_LAST_NAME_INPUT);
    }
}