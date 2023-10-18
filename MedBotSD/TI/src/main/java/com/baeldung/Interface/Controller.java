package com.baeldung.Interface;

import com.baeldung.DI.AppConfig;
import com.baeldung.Doctor.Doctor;
import com.baeldung.Doctor.IDoctorService;
import com.baeldung.Record.IRecordService;
import com.baeldung.User.IUserService;
import com.baeldung.User.User;
import com.baeldung.Record.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class Controller {
    private static AppConfig appConfig;
    private static View view = new View();
    private static Scanner scanner = new Scanner(System.in);
    private static IUserService userService;
    private static IDoctorService doctorService;
    private static IRecordService recordService;
    private static Logger logger;

    public static void main(String[] args) {
        try {
            FileInputStream in = new FileInputStream("src/main/resources/config.properties");
            Properties props = new Properties();
            props.load(in);

            if (props.getProperty("log_level").equals("info"))
                logger = LoggerFactory.getLogger("info.logger");
            else
                logger = LoggerFactory.getLogger("error.logger");

        } catch (FileNotFoundException e) {
            System.out.println("Не найден конфигурационный файл в Controller!");
        } catch (IOException e) {
            System.out.println("Ошибка подкачки настроек из конфигурационного файла в Controller!");
        }

        try {
            appConfig = new AppConfig();
        } catch (IOException e) {
            logger.error("Ошибка при получении конфигурационного файла в AppConfig!");
        }
        try {
             userService = appConfig.getUserServiceImpl();
        } catch (IOException e) {
            logger.error("Ошибка при получении UserServiceImpl в Controller!");
        }
        try {
            doctorService = appConfig.getDoctorServiceImpl();
        } catch (IOException e) {
            logger.error("Ошибка при получении DoctorServiceImpl в Controller!");
        }
        try {
            recordService = appConfig.getRecordServiceImpl();
        } catch (IOException e) {
            logger.error("Ошибка при получении RecordServiceImpl в Controller!");
        }

        User user = unregUserCases();
        System.out.println();

        userCases(user);
    }

    private static User unregUserCases() {
        view.outputUserGreeting();
        User user;
        String password;
        String login;
        int privilegeLevel;
        String firstName;
        String lastName;
        String genderString;
        Boolean gender;
        Date birthDate;
        Boolean res;
        ArrayList<Doctor> arrDoctors;

        while (true){
            view.outputBaseMenuForUnreg();
            switch (scanner.nextInt()) {
                case 1:
                    view.outputInviteToLoginInput();
                    login = scanner.next();
                    view.outputInviteToPasswordInput();
                    password = scanner.next();
                    try {
                        user = userService.logIn(login, password);
                    } catch (Exception e) {
                        logger.error("Ошибка " + e + " при входе в аккаунт!");
                        break;
                    }
                    if (user == null) {
                        view.outputErrorLogInMessage();
                        break;
                    }
                    return user;
                case 2:
                    privilegeLevel = 0;
                    view.outputInviteToFirstNameInput();
                    firstName = scanner.next();
                    if (firstName.length() == 0){
                        view.outputIncorrectFirstNameInputMessage();
                        break;
                    }
                    view.outputInviteToLastNameInput();
                    lastName = scanner.next();
                    if (lastName.length() == 0){
                        view.outputIncorrectLastNameInputMessage();
                        break;
                    }
                    view.outputInviteToGenderInput();
                    genderString = scanner.next();
                    if (genderString.equals("м"))
                        gender = true;
                    else if (genderString.equals("ж"))
                        gender = false;
                    else {
                        view.outputIncorrectGenderInputMessage();
                        break;
                    }
                    view.outputInviteToDateBirthInput();
                    try {
                        birthDate = Date.valueOf(scanner.next());
                    } catch (Exception e) {
                        view.outputIncorrectDateBirthInputMessage();
                        break;
                    }
                    view.outputInviteToLoginInput();
                    login = scanner.next();
                    if (login.length() == 0) {
                        view.outputIncorrectLoginInputMessage();
                        break;
                    }
                    view.outputInviteToPasswordInput();
                    password = scanner.next();
                    if (password.length() == 0) {
                        view.outputIncorrectPasswordInputMessage();
                        break;
                    }
                    user = new User();
                    user.setPrivilegeLevel(privilegeLevel);
                    user.setLogin(login);
                    user.setPassword(password);
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setGender(gender);
                    user.setBirthDate(birthDate);
                    try {
                        res = userService.addUser(user);
                    } catch (Exception e) {
                        logger.error("Ошибка " + e + " при регистрации пользователя!");
                        break;
                    }
                    if (res) {
                        return userService.getUserByLogin(login);
                    }
                    else {
                        view.outputAddUserErrorMessage();
                        break;
                    }
                case (3):
                    try {
                        arrDoctors = doctorService.getDoctorsList();
                    } catch (Exception e) {
                        logger.error("Ошибка " + e + " при получении списка врачей!");
                        break;
                    }

                    if (arrDoctors == null || arrDoctors.size() == 0) {
                        view.outputDoctorsListBySpecializationBlank();
                        break;
                    }

                    for (int i = 0; i < arrDoctors.size(); i++)
                        System.out.println(arrDoctors.get(i).getFirstName() + " " +
                                arrDoctors.get(i).getLastName() + " " + arrDoctors.get(i).getSpecialization());
                    System.out.println();

                    break;
                default:
                    view.outputIncorrectMenuChoiceMessage();
                    break;
            }
        }
    }
    private static void userCases(User user) {
        String specialization;
        String password;
        String login;
        String firstName;
        String lastName;
        String genderString;
        int privilegeLevel;
        Boolean gender;
        Date birthDate;
        ArrayList<Doctor> arrDoctors;
        Record record;
        Doctor doctor;
        Time time;
        Date date;
        User tmpUser;
        ArrayList<Record> arrRecords;
        ArrayList<User> arrUsers;
        int id;
        boolean flag;
        boolean res;

        while (true) {
            if (user.getPrivilegeLevel() == 0)
                view.outputBaseMenuForUser();
            else
                view.outputBaseMenuForAdmin();

            switch (scanner.nextInt()) {
                case 1:
                    try {
                        arrDoctors = doctorService.getDoctorsList();
                    } catch (Exception e) {
                        logger.error("Ошибка " + e + " при получении списка врачей!");
                        break;
                    }

                    if (arrDoctors == null || arrDoctors.size() == 0) {
                        view.outputDoctorsListBySpecializationBlank();
                        break;
                    }

                    for (int i = 0; i < arrDoctors.size(); i++)
                        System.out.println(arrDoctors.get(i).getFirstName() + " " +
                                arrDoctors.get(i).getLastName() + " " + arrDoctors.get(i).getSpecialization());
                    System.out.println();

                    break;
                case 2:
                    try {
                        arrDoctors = doctorService.getDoctorsList();
                    } catch (Exception e) {
                        logger.error("Ошибка " + e + " при получении списка врачей!");
                        break;
                    }

                    if (arrDoctors == null || arrDoctors.size() == 0) {
                        view.outputDoctorsListBySpecializationBlank();
                        break;
                    }

                    for (int i = 0; i < arrDoctors.size(); i++)
                        System.out.println(arrDoctors.get(i).getFirstName() + " " +
                                arrDoctors.get(i).getLastName() + " " + arrDoctors.get(i).getSpecialization() + " "
                                + arrDoctors.get(i).getId());
                    System.out.println();

                    System.out.println("Введите идентификатор нужного врача: ");
                    id = scanner.nextInt();
                    flag = false;

                    record = new Record();

                    for (int i = 0; i < arrDoctors.size(); i++)
                        if (arrDoctors.get(i).getId() == id) {
                            record.setIdDoctor(arrDoctors.get(i).getId());
                            flag = true;
                        }

                    if (!flag) {
                        System.out.println("Врача с таким идентификатором нет в списке!\n");
                        break;
                    }

                    if (user.getPrivilegeLevel() == 0)
                        record.setIdUser(user.getId());
                    else {
                        try {
                            arrUsers = userService.getUsersList();
                        } catch (Exception e) {
                            logger.error("Ошибка " + e + " при получении списка пользователей!");
                            break;
                        }
                        System.out.println("Список пользователей, которым можно добавить запись:");
                        for (int i = 0; i < arrUsers.size(); i++)
                            System.out.println(arrUsers.get(i).getFirstName() + " " + arrUsers.get(i).getLastName() +
                                    " " + arrUsers.get(i).getLogin());

                        System.out.println("Введите логин интересующего пользователя: ");
                        login = scanner.next();
                        if (login.length() == 0) {
                            view.outputIncorrectLoginInputMessage();
                            break;
                        }

                        try {
                            record.setIdUser(userService.getUserByLogin(login).getId());
                        } catch (Exception e) {
                            logger.error("Ошибка " + e + " при получении пользователя по логину!");
                            break;
                        }
                    }

                    System.out.println("Введите дату записи в формате '2023-03-12': ");
                    try {
                        date = Date.valueOf(scanner.next());
                    } catch (Exception e) {
                        System.out.println("Некорректный формат ввода даты!\n");
                        break;
                    }
                    record.setDate(date);
                    System.out.println("Введите время начала записи в формате '18:25:00': ");
                    try {
                        time = Time.valueOf(scanner.next());
                    } catch (Exception e) {
                        System.out.println("Некорректный формат ввода времени!\n");
                        break;
                    }
                    record.setStartTime(time);
                    System.out.println("Введите время конца записи в формате '18:25:00': ");
                    try {
                        time = Time.valueOf(scanner.next());
                    } catch (Exception e) {
                        System.out.println("Некорректный формат ввода времени!\n");
                        break;
                    }
                    record.setEndTime(time);

                    try {
                        res = recordService.addRecord(record);
                    } catch (Exception e) {
                        logger.error("Ошибка " + e + " при добавлении записи: id доктора - " + record.getIdDoctor() +
                                ", id пользователя - " + record.getIdUser() + ", дата - " + record.getDate() +
                                ", начало - " + record.getEndTime() + ", конец - " + record.getEndTime());
                        break;
                    }

                    if (!res) {
                        System.out.println("Запись на данное место невозможна или вы некорректно ввели данные!\n");
                        break;
                    }

                    System.out.println("Запись добавлена!\n");
                    break;
                case 3:
                    try {
                        arrDoctors = doctorService.getDoctorsList();
                    } catch (Exception e) {
                        logger.error("Ошибка " + e + " при получении списка врачей!");
                        break;
                    }

                    if (arrDoctors == null || arrDoctors.size() == 0) {
                        view.outputDoctorsListBySpecializationBlank();
                        break;
                    }

                    for (int i = 0; i < arrDoctors.size(); i++)
                        System.out.println(arrDoctors.get(i).getFirstName() + " " +
                                arrDoctors.get(i).getLastName() + " " + arrDoctors.get(i).getSpecialization() + " "
                                + arrDoctors.get(i).getId());
                    System.out.println();

                    System.out.println("Введите идентификатор нужного врача: ");
                    id = scanner.nextInt();
                    flag = false;

                    record = new Record();

                    for (int i = 0; i < arrDoctors.size(); i++)
                        if (arrDoctors.get(i).getId() == id) {
                            record.setIdDoctor(arrDoctors.get(i).getId());
                            flag = true;
                        }

                    if (!flag) {
                        System.out.println("Врача с таким идентификатором нет в списке!\n");
                        break;
                    }

                    if (user.getPrivilegeLevel() == 0)
                        record.setIdUser(user.getId());
                    else {
                        try {
                            arrUsers = userService.getUsersList();
                        } catch (Exception e) {
                            logger.error("Ошибка " + e + " при получении списка пользователей!");
                            break;
                        }
                        System.out.println("Список пользователей, которым можно добавить запись:");
                        for (int i = 0; i < arrUsers.size(); i++)
                            System.out.println(arrUsers.get(i).getFirstName() + " " + arrUsers.get(i).getLastName() +
                                    " " + arrUsers.get(i).getLogin());

                        System.out.println("Введите логин интересующего пользователя: ");
                        login = scanner.next();
                        if (login.length() == 0) {
                            view.outputIncorrectLoginInputMessage();
                            break;
                        }

                        try {
                            record.setIdUser(userService.getUserByLogin(login).getId());
                        } catch (Exception e) {
                            logger.error("Ошибка " + e + " при получении пользователя по логину!");
                            break;
                        }
                    }

                    System.out.println("Введите дату записи в формате '2023-03-12': ");
                    try {
                        date = Date.valueOf(scanner.next());
                    } catch (Exception e) {
                        System.out.println("Некорректный формат ввода даты!\n");
                        break;
                    }
                    record.setDate(date);
                    System.out.println("Введите время начала записи в формате '18:25:00': ");
                    try {
                        time = Time.valueOf(scanner.next());
                    } catch (Exception e) {
                        System.out.println("Некорректный формат ввода времени!\n");
                        break;
                    }
                    record.setStartTime(time);
                    System.out.println("Введите время конца записи в формате '18:25:00': ");
                    try {
                        time = Time.valueOf(scanner.next());
                    } catch (Exception e) {
                        System.out.println("Некорректный формат ввода времени!\n");
                        break;
                    }
                    record.setEndTime(time);

                    try {
                        arrRecords = recordService.getRecordsByDate(record.getIdDoctor(), record.getDate());
                    } catch (Exception e) {
                        logger.error("Ошибка " + e + " при получении списка записей!");
                        break;
                    }
                    if (arrRecords == null) {
                        System.out.println("Нет таких записей!\n");
                        break;
                    }
                    for (int i = 0; i < arrRecords.size(); i++)
                        if (record.getIdUser() == arrRecords.get(i).getIdUser() && record.getStartTime()
                                .equals(arrRecords.get(i).getStartTime()) && record.getEndTime()
                                .equals(arrRecords.get(i).getEndTime()))
                            record.setId(arrRecords.get(i).getId());

                    try {
                        res = recordService.deleteRecord(record);
                    } catch (Exception e) {
                        logger.error("Ошибка " + e + " при удалении записи: дата - " + record.getDate() +
                                ", начало - " + record.getStartTime() + ", конец - " + record.getEndTime());
                        break;
                    }

                    if (!res) {
                        System.out.println("Не удаётся удалить такую запись, проверьте вводимые данные!\n");
                        break;
                    }
                    break;
                case 4:
                    try {
                        arrDoctors = doctorService.getDoctorsList();
                    } catch (Exception e) {
                        logger.error("Ошибка " + e + " при получении списка врачей!");
                        break;
                    }

                    if (arrDoctors == null || arrDoctors.size() == 0) {
                        view.outputDoctorsListBySpecializationBlank();
                        break;
                    }

                    for (int i = 0; i < arrDoctors.size(); i++)
                        System.out.println(arrDoctors.get(i).getFirstName() + " " +
                                arrDoctors.get(i).getLastName() + " " + arrDoctors.get(i).getSpecialization() + " "
                                + arrDoctors.get(i).getId());
                    System.out.println();

                    System.out.println("Введите идентификатор нужного врача: ");
                    id = scanner.nextInt();
                    flag = false;

                    record = new Record();

                    for (int i = 0; i < arrDoctors.size(); i++)
                        if (arrDoctors.get(i).getId() == id) {
                            record.setIdDoctor(arrDoctors.get(i).getId());
                            flag = true;
                        }

                    if (!flag) {
                        System.out.println("Врача с таким идентификатором нет в списке!\n");
                        break;
                    }

                    if (user.getPrivilegeLevel() == 0)
                        record.setIdUser(user.getId());
                    else {
                        try {
                            arrUsers = userService.getUsersList();
                        } catch (Exception e) {
                            logger.error("Ошибка " + e + " при получении списка пользователей!");
                            break;
                        }
                        System.out.println("Список пользователей, которым можно добавить запись:");
                        for (int i = 0; i < arrUsers.size(); i++)
                            System.out.println(arrUsers.get(i).getFirstName() + " " + arrUsers.get(i).getLastName() +
                                    " " + arrUsers.get(i).getLogin());

                        System.out.println("Введите логин интересующего пользователя: ");
                        login = scanner.next();
                        if (login.length() == 0) {
                            view.outputIncorrectLoginInputMessage();
                            break;
                        }

                        try {
                            record.setIdUser(userService.getUserByLogin(login).getId());
                        } catch (Exception e) {
                            logger.error("Ошибка " + e + " при получении пользователя по логину!");
                            break;
                        }
                    }

                    System.out.println("Введите дату записи в формате '2023-03-12': ");
                    try {
                        date = Date.valueOf(scanner.next());
                    } catch (Exception e) {
                        System.out.println("Некорректный формат ввода даты!\n");
                        break;
                    }
                    record.setDate(date);
                    System.out.println("Введите время начала записи в формате '18:25:00': ");
                    try {
                        time = Time.valueOf(scanner.next());
                    } catch (Exception e) {
                        System.out.println("Некорректный формат ввода времени!\n");
                        break;
                    }
                    record.setStartTime(time);
                    System.out.println("Введите время конца записи в формате '18:25:00': ");
                    try {
                        time = Time.valueOf(scanner.next());
                    } catch (Exception e) {
                        System.out.println("Некорректный формат ввода времени!\n");
                        break;
                    }
                    record.setEndTime(time);

                    try {
                        arrRecords = recordService.getRecordsByDate(record.getIdDoctor(), record.getDate());
                    } catch (Exception e) {
                        logger.error("Ошибка " + e + " при получении списка записей!");
                        break;
                    }
                    if (arrRecords == null) {
                        System.out.println("Нет таких записей!\n");
                        break;
                    }
                    for (int i = 0; i < arrRecords.size(); i++)
                        if (record.getIdUser() == arrRecords.get(i).getIdUser() && record.getStartTime()
                                .equals(arrRecords.get(i).getStartTime()) && record.getEndTime()
                                .equals(arrRecords.get(i).getEndTime()))
                            record.setId(arrRecords.get(i).getId());

                    System.out.println("Теперь введите дату и время записи, на коротые вы хотели бы её переместить.\n");
                    System.out.println("Введите дату записи в формате '2023-03-12': ");
                    try {
                        date = Date.valueOf(scanner.next());
                    } catch (Exception e) {
                        System.out.println("Некорректный формат ввода даты!\n");
                        break;
                    }
                    record.setDate(date);
                    System.out.println("Введите время начала записи в формате '18:25:00': ");
                    try {
                        time = Time.valueOf(scanner.next());
                    } catch (Exception e) {
                        System.out.println("Некорректный формат ввода времени!\n");
                        break;
                    }
                    record.setStartTime(time);
                    System.out.println("Введите время конца записи в формате '18:25:00': ");
                    try {
                        time = Time.valueOf(scanner.next());
                    } catch (Exception e) {
                        System.out.println("Некорректный формат ввода времени!\n");
                        break;
                    }
                    record.setEndTime(time);

                    try {
                        res = recordService.updateRecord(record);
                    } catch (Exception e) {
                        logger.error("Ошибка " + e + " при обновлении записи на данные: id доктора - " +
                                record.getIdDoctor() + ", id пользователя - " + record.getIdUser() + ", дата - " +
                                record.getDate() + ", начало - " + record.getEndTime() + ", конец - " +
                                record.getEndTime());
                        break;
                    }

                    if (!res) {
                        System.out.println("Не удаётся обновить запись на такие данные, проверьте корректность " +
                                "вводимых данных или попробуйте выбрать другие время и дату!\n");
                        break;
                    }
                    break;
                case 5:
                    view.outputInviteToUpdateUserParameters();
                    view.outputInviteToFirstNameInput();
                    firstName = scanner.next();
                    if (firstName.length() == 0){
                        view.outputIncorrectFirstNameInputMessage();
                        break;
                    }
                    view.outputInviteToLastNameInput();
                    lastName = scanner.next();
                    if (lastName.length() == 0){
                        view.outputIncorrectLastNameInputMessage();
                        break;
                    }
                    view.outputInviteToGenderInput();
                    genderString = scanner.next();
                    if (genderString.equals("м"))
                        gender = true;
                    else if (genderString.equals("ж"))
                        gender = false;
                    else {
                        view.outputIncorrectGenderInputMessage();
                        break;
                    }
                    view.outputInviteToDateBirthInput();
                    try {
                        birthDate = Date.valueOf(scanner.next());
                    } catch (Exception e) {
                        view.outputIncorrectDateBirthInputMessage();
                        break;
                    }
                    view.outputInviteToLoginInput();
                    login = scanner.next();
                    if (login.length() == 0) {
                        view.outputIncorrectLoginInputMessage();
                        break;
                    }
                    view.outputInviteToPasswordInput();
                    password = scanner.next();
                    if (password.length() == 0) {
                        view.outputIncorrectPasswordInputMessage();
                        break;
                    }

                    tmpUser = new User();
                    tmpUser.setLogin(login);
                    tmpUser.setPassword(password);
                    tmpUser.setFirstName(firstName);
                    tmpUser.setLastName(lastName);
                    tmpUser.setGender(gender);
                    tmpUser.setPrivilegeLevel(user.getPrivilegeLevel());
                    tmpUser.setBirthDate(birthDate);
                    tmpUser.setId(user.getId());
                    try {
                        res = userService.updateUser(tmpUser);
                    } catch (Exception e) {
                        logger.error("Ошибка " + e + " при обновлении пользователем с id - " + tmpUser.getId() +
                                " данных своего аккаунта!");
                        break;
                    }
                    if (!res)
                        view.outputUserAccountUpdateErrorMessage();

                    break;
                case 6:
                    if (user.getPrivilegeLevel() == 0) {
                        view.outputIncorrectMenuChoiceMessage();
                        break;
                    }

                    view.outputInviteToDoctorFirstNameInput();
                    firstName = scanner.next();
                    if (firstName.length() == 0){
                        view.outputIncorrectFirstNameInputMessage();
                        break;
                    }
                    view.outputInviteToDoctorLastNameInput();
                    lastName = scanner.next();
                    if (lastName.length() == 0){
                        view.outputIncorrectLastNameInputMessage();
                        break;
                    }
                    view.outputInviteToGenderInput();
                    genderString = scanner.next();
                    if (genderString.equals("м"))
                        gender = true;
                    else if (genderString.equals("ж"))
                        gender = false;
                    else {
                        view.outputIncorrectGenderInputMessage();
                        break;
                    }
                    view.outputInviteToDoctorSpecializationInput();
                    specialization = scanner.next();
                    if (specialization.length() == 0) {
                        view.outputIncorrectDoctorSpecializationInputMessage();
                        break;
                    }

                    doctor = new Doctor();
                    doctor.setFirstName(firstName);
                    doctor.setLastName(lastName);
                    doctor.setGender(gender);
                    doctor.setSpecialization(specialization);

                    try {
                        res = doctorService.addDoctor(doctor);
                    } catch (Exception e) {
                        logger.error("Ошибка " + e + " при добавлении врача: " + doctor.getFirstName() + " " +
                                doctor.getLastName() + ", специализация - " + doctor.getSpecialization());
                        break;
                    }

                    if (!res)
                        System.out.println("Врача с такими данными добавить невозможно!\n");
                    break;
                case 7:
                    if (user.getPrivilegeLevel() == 0) {
                        view.outputIncorrectMenuChoiceMessage();
                        break;
                    }

                    try {
                        arrDoctors = doctorService.getDoctorsList();
                    } catch (Exception e) {
                        logger.error("Ошибка " + e + " при получении списка врачей!");
                        break;
                    }

                    if (arrDoctors == null || arrDoctors.size() == 0) {
                        view.outputDoctorsListBySpecializationBlank();
                        break;
                    }

                    for (int i = 0; i < arrDoctors.size(); i++)
                        System.out.println(arrDoctors.get(i).getFirstName() + " " +
                                arrDoctors.get(i).getLastName() + " " + arrDoctors.get(i).getSpecialization() + " "
                                + arrDoctors.get(i).getId());
                    System.out.println();

                    System.out.println("Введите идентификатор нужного врача: ");
                    id = scanner.nextInt();
                    flag = false;

                    for (int i = 0; i < arrDoctors.size(); i++)
                        if (arrDoctors.get(i).getId() == id) {
                            flag = true;
                        }

                    if (!flag) {
                        System.out.println("Врача с таким идентификатором нет в списке!\n");
                        break;
                    }

                    try {
                        res = doctorService.deleteDoctor(id);
                    } catch (Exception e) {
                        logger.error("Ошибка " + e + " при удалении врача с id - " + id);
                        break;
                    }

                    if (!res)
                        System.out.println("Доктора с такими данными удалить невозможно!\n");
                    break;
                case 8:
                    if (user.getPrivilegeLevel() == 0) {
                        view.outputIncorrectMenuChoiceMessage();
                        break;
                    }

                    try {
                        arrDoctors = doctorService.getDoctorsList();
                    } catch (Exception e) {
                        logger.error("Ошибка " + e + " при получении списка врачей!");
                        break;
                    }

                    if (arrDoctors == null || arrDoctors.size() == 0) {
                        view.outputDoctorsListBySpecializationBlank();
                        break;
                    }

                    for (int i = 0; i < arrDoctors.size(); i++)
                        System.out.println(arrDoctors.get(i).getFirstName() + " " +
                                arrDoctors.get(i).getLastName() + " " + arrDoctors.get(i).getSpecialization() + " "
                                + arrDoctors.get(i).getId());
                    System.out.println();

                    System.out.println("Введите идентификатор нужного врача: ");
                    id = scanner.nextInt();
                    flag = false;

                    for (int i = 0; i < arrDoctors.size(); i++)
                        if (arrDoctors.get(i).getId() == id) {
                            flag = true;
                        }

                    if (!flag) {
                        System.out.println("Врача с таким идентификатором нет в списке!\n");
                        break;
                    }

                    doctor = new Doctor();
                    doctor.setId(id);

                    System.out.println("Ниже введите новые данные врача.");
                    view.outputInviteToDoctorFirstNameInput();
                    firstName = scanner.next();
                    if (firstName.length() == 0){
                        view.outputIncorrectFirstNameInputMessage();
                        break;
                    }
                    view.outputInviteToDoctorLastNameInput();
                    lastName = scanner.next();
                    if (lastName.length() == 0){
                        view.outputIncorrectLastNameInputMessage();
                        break;
                    }
                    view.outputInviteToGenderInput();
                    genderString = scanner.next();
                    if (genderString.equals("м"))
                        gender = true;
                    else if (genderString.equals("ж"))
                        gender = false;
                    else {
                        view.outputIncorrectGenderInputMessage();
                        break;
                    }
                    view.outputInviteToDoctorSpecializationInput();
                    specialization = scanner.next();
                    if (specialization.length() == 0) {
                        view.outputIncorrectDoctorSpecializationInputMessage();
                        break;
                    }

                    doctor.setFirstName(firstName);
                    doctor.setLastName(lastName);
                    doctor.setGender(gender);
                    doctor.setSpecialization(specialization);

                    try {
                        res = doctorService.updateDoctor(doctor);
                    } catch (Exception e) {
                        logger.error("Ошибка " + e + " при обновлении врача с id - " + doctor.getId() +
                                " на данные: " + doctor.getFirstName() + " " +
                                doctor.getLastName() + ", специализация - " + doctor.getSpecialization());
                        break;
                    }

                    if (!res)
                        System.out.println("Невозможно обновить доктора такими данными!\n");
                    break;
                case 9:
                    if (user.getPrivilegeLevel() == 0) {
                        view.outputIncorrectMenuChoiceMessage();
                        break;
                    }

                    privilegeLevel = 0;
                    view.outputInviteToFirstNameInput();
                    firstName = scanner.next();
                    if (firstName.length() == 0){
                        view.outputIncorrectFirstNameInputMessage();
                        break;
                    }
                    view.outputInviteToLastNameInput();
                    lastName = scanner.next();
                    if (lastName.length() == 0){
                        view.outputIncorrectLastNameInputMessage();
                        break;
                    }
                    view.outputInviteToGenderInput();
                    genderString = scanner.next();
                    if (genderString.equals("м"))
                        gender = true;
                    else if (genderString.equals("ж"))
                        gender = false;
                    else {
                        view.outputIncorrectGenderInputMessage();
                        break;
                    }
                    view.outputInviteToDateBirthInput();
                    try {
                        birthDate = Date.valueOf(scanner.next());
                    } catch (Exception e) {
                        view.outputIncorrectDateBirthInputMessage();
                        break;
                    }
                    view.outputInviteToLoginInput();
                    login = scanner.next();
                    if (login.length() == 0) {
                        view.outputIncorrectLoginInputMessage();
                        break;
                    }
                    view.outputInviteToPasswordInput();
                    password = scanner.next();
                    if (password.length() == 0) {
                        view.outputIncorrectPasswordInputMessage();
                        break;
                    }
                    tmpUser = new User();
                    tmpUser.setPrivilegeLevel(privilegeLevel);
                    tmpUser.setLogin(login);
                    tmpUser.setPassword(password);
                    tmpUser.setFirstName(firstName);
                    tmpUser.setLastName(lastName);
                    tmpUser.setGender(gender);
                    tmpUser.setBirthDate(birthDate);

                    try {
                        res = userService.addUser(tmpUser);
                    } catch (Exception e) {
                        logger.error("Ошибка " + e + " при добавлении пользователя: " + tmpUser.getFirstName() + " " +
                                tmpUser.getLastName() + ", логин - " + tmpUser.getLogin() + ", пароль" +
                                tmpUser.getPassword());
                        break;
                    }

                    if (!res)
                        System.out.println("Пользователя с такими данными добавить невозможно!\n");
                    break;
                case 10:
                    if (user.getPrivilegeLevel() == 0) {
                        view.outputIncorrectMenuChoiceMessage();
                        break;
                    }

                    try {
                        arrUsers = userService.getUsersList();
                    } catch (Exception e) {
                        logger.error("Ошибка " + e + " при получении списка пользователей!");
                        break;
                    }

                    for (int i = 0; i < arrUsers.size(); i++)
                        System.out.println(arrUsers.get(i).getFirstName() + " " + arrUsers.get(i).getLastName() + " "
                                + arrUsers.get(i).getLogin());

                    System.out.println("Введите логин нужного пользователя: ");
                    login = scanner.next();
                    if (login.length() == 0) {
                        view.outputIncorrectLoginInputMessage();
                        break;
                    }

                    try {
                        tmpUser = userService.getUserByLogin(login);
                    } catch (Exception e) {
                        logger.error("Ошибка " + e + " при получении пользователя по логину - " + login);
                        break;
                    }
                    try {
                        res = userService.deleteUser(tmpUser.getId());
                    } catch (Exception e) {
                        logger.error("Ошибка " + e + " при удалении пользователя с id - " + tmpUser.getId());
                        break;
                    }

                    if (!res)
                        System.out.println("Невозможно удалить пользователя c такими данными!\n");
                    break;
                case 11:
                    if (user.getPrivilegeLevel() == 0) {
                        view.outputIncorrectMenuChoiceMessage();
                        break;
                    }

                    try {
                        arrUsers = userService.getUsersList();
                    } catch (Exception e) {
                        logger.error("Ошибка " + e + " при получении списка пользователей!");
                        break;
                    }

                    for (int i = 0; i < arrUsers.size(); i++)
                        System.out.println(arrUsers.get(i).getFirstName() + " " + arrUsers.get(i).getLastName() + " "
                                + arrUsers.get(i).getLogin());

                    System.out.println("Введите логин нужного пользователя: ");
                    login = scanner.next();
                    if (login.length() == 0) {
                        view.outputIncorrectLoginInputMessage();
                        break;
                    }

                    try {
                        tmpUser = userService.getUserByLogin(login);
                    } catch (Exception e) {
                        logger.error("Ошибка " + e + " при получении пользователя по логину - " + login);
                        break;
                    }

                    System.out.println("Введите новые данные пользователя.");
                    view.outputInviteToFirstNameInput();
                    firstName = scanner.next();
                    if (firstName.length() == 0){
                        view.outputIncorrectFirstNameInputMessage();
                        break;
                    }
                    view.outputInviteToLastNameInput();
                    lastName = scanner.next();
                    if (lastName.length() == 0){
                        view.outputIncorrectLastNameInputMessage();
                        break;
                    }
                    view.outputInviteToGenderInput();
                    genderString = scanner.next();
                    if (genderString.equals("м"))
                        gender = true;
                    else if (genderString.equals("ж"))
                        gender = false;
                    else {
                        view.outputIncorrectGenderInputMessage();
                        break;
                    }
                    view.outputInviteToDateBirthInput();
                    try {
                        birthDate = Date.valueOf(scanner.next());
                    } catch (Exception e) {
                        view.outputIncorrectDateBirthInputMessage();
                        break;
                    }
                    view.outputInviteToLoginInput();
                    login = scanner.next();
                    if (login.length() == 0) {
                        view.outputIncorrectLoginInputMessage();
                        break;
                    }
                    view.outputInviteToPasswordInput();
                    password = scanner.next();
                    if (password.length() == 0) {
                        view.outputIncorrectPasswordInputMessage();
                        break;
                    }

                    tmpUser.setLogin(login);
                    tmpUser.setPassword(password);
                    tmpUser.setFirstName(firstName);
                    tmpUser.setLastName(lastName);
                    tmpUser.setGender(gender);
                    tmpUser.setBirthDate(birthDate);
                    tmpUser.setPrivilegeLevel(user.getPrivilegeLevel());
                    tmpUser.setId(user.getId());

                    try {
                        res = userService.updateUser(tmpUser);
                    } catch (Exception e) {
                        logger.error("Ошибка " + e + " при обноалении пользователя с id - " + tmpUser.getId() +
                                " на данные: " + tmpUser.getFirstName() + " " +
                                tmpUser.getLastName() + ", логин - " + tmpUser.getLogin() + ", пароль" +
                                tmpUser.getPassword());
                        break;
                    }

                    if (!res)
                        System.out.println("Невозможно обновить аккаунт пользователя на такие данные!\n");
                    break;
                default:
                    view.outputIncorrectMenuChoiceMessage();
                    break;
            }
        }
    }
}

