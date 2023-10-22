package com.baeldung.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class UserService implements IUserService {
    private IUserRepository userRep;
    private IGetUserRepository getUserRep;
    private Logger logger;

    public UserService (IGetUserRepository getUserRepository, IUserRepository userRepository) throws IOException {
        this.userRep = userRepository;
        this.getUserRep = getUserRepository;
        try {
            FileInputStream in = new FileInputStream("src/main/resources/config.properties");
            Properties props = new Properties();
            props.load(in);

            if (props.getProperty("log_level").equals("info"))
                this.logger = LoggerFactory.getLogger("info.logger");
            else
                this.logger = LoggerFactory.getLogger("error.logger");

        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }
    }

    @Override
    public Boolean addUser(User user) {
        if (getUserRep.getUserByLogin(user.getLogin()) != null)
            return false;

        Boolean res = userRep.addUser(user);

        if (res)
            logger.info("Добавлен пользователь: " + user.getFirstName() + ", " + user.getLastName() + ", логин - " +
                    user.getLogin() + ", пароль - " + user.getPassword() + ", пол - " + user.getGender().toString() +
                    ", дата рождения - " + user.getBirthDate().toString());
        else
            logger.info("Неудачная попытка добавить пользователя: " + user.getFirstName() + ", " +
                    user.getLastName() + ", логин - " + user.getLogin() + ", пароль - " + user.getPassword() +
                    ", пол - " + user.getGender().toString() + ", дата рождения - " + user.getBirthDate().toString());

        return res;
    }

    @Override
    public Boolean deleteUser(int id) {
        if (getUserRep.getUserById(id) == null)
            return false;

        Boolean res = userRep.deleteUser(id);

        if (res)
            logger.info("Удалён пользователь: идентификатор - " + Integer.toString(id));
        else
            logger.info("Неудачная попытка удалить пользователя: идентификатор - " + Integer.toString(id));

        return res;
    }

    @Override
    public Boolean updateUser(User user) {
        if (getUserRep.getUserById(user.getId()) == null)
            return false;

        Boolean res = userRep.updateUser(user);

        if (res)
            logger.info("Обновлён пользователь с идентификатором - " + Integer.toString(user.getId()) +
                    " на данные: " + user.getFirstName() + ", " + user.getLastName() + ", логин - " +
                    user.getLogin() + ", пароль - " + user.getPassword() + ", пол - " + user.getGender().toString() +
                    ", дата рождения - " + user.getBirthDate().toString());
        else
            logger.info("Неудачная попытка удалить пользователя с идентификатором - " + Integer.toString(user.getId()) +
                    " на данные: " + user.getFirstName() + ", " + user.getLastName() + ", логин - " +
                    user.getLogin() + ", пароль - " + user.getPassword() + ", пол - " + user.getGender().toString() +
                    ", дата рождения - " + user.getBirthDate().toString());

        return res;
    }

    @Override
    public User logIn(String login, String password) {
        User user = getUserRep.getUserAccount(login, password);

        if (user != null)
            logger.info("Пользователь вошёл в аккаунт: идентификатор - " + user.getId() + ", " + user.getFirstName() +
                    ", " + user.getLastName() + ", логин - " + user.getLogin() + ", пароль - " + user.getPassword() +
                    ", пол - " + user.getGender().toString() + ", дата рождения - " + user.getBirthDate().toString());
        else
            logger.info("Неудачная попытка войти в аккаун по данным:  логин - " + login + ", пароль - " + password);

        return user;
    }

    @Override
    public User getUserByLogin(String login) {
        User user = getUserRep.getUserByLogin(login);

        if (user != null)
            logger.info("По логину получен пользователь: идентификатор - " + user.getId() + ", " + user.getFirstName() +
                    ", " + user.getLastName() + ", логин - " + user.getLogin() + ", пароль - " + user.getPassword() +
                    ", пол - " + user.getGender().toString() + ", дата рождения - " + user.getBirthDate().toString());
        else
            logger.info("Неудачная попытка получить пользователя по логину - " + login);

        return user;
    }

    @Override
    public ArrayList<User> getUsersList() {
        ArrayList<User> arrUsers = getUserRep.getUsersList();

        if (arrUsers != null && arrUsers.size() != 0)
            logger.info("Получен список из " + arrUsers.size() + " пользователей");
        else
            logger.info("Неудачная попытка получить список пользователей");

        return arrUsers;
    }
}

