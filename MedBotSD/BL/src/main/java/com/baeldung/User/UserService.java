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
        User tmpUser = null;
        try {
            tmpUser = getUserRep.getUserByLogin(user.getLogin());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (tmpUser != null)
            return false;

        Boolean res;
        try {
            res = userRep.addUser(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

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
        User tmpUser = null;
        try {
            tmpUser = getUserRep.getUserById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (tmpUser == null)
            return false;

        Boolean res;
        try {
            res = userRep.deleteUser(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (res)
            logger.info("Удалён пользователь: идентификатор - " + Integer.toString(id));
        else
            logger.info("Неудачная попытка удалить пользователя: идентификатор - " + Integer.toString(id));

        return res;
    }

    @Override
    public Boolean updateUser(User user) {
        User tmpUser = null;
        try {
            tmpUser = getUserRep.getUserById(user.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (tmpUser == null)
            return false;

        Boolean res;
        try {
            res = userRep.updateUser(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

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
        User user = null;
        try {
            user = getUserRep.getUserAccount(login, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

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
        User user = null;
        try {
            user = getUserRep.getUserByLogin(login);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

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
        ArrayList<User> arrUsers = null;
        try {
            arrUsers = getUserRep.getUsersList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (arrUsers != null && arrUsers.size() != 0)
            logger.info("Получен список из " + arrUsers.size() + " пользователей");
        else
            logger.info("Неудачная попытка получить список пользователей");

        return arrUsers;
    }
}

