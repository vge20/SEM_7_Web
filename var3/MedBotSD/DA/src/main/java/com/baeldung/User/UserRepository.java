package com.baeldung.User;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class UserRepository implements IUserRepository, IGetUserRepository {
    @Override
    public Boolean addUser(User user) {
        UserDAModel userDAModel = new UserDAModel(user.getLogin(), user.getPassword(), user.getPrivilegeLevel(),
                user.getFirstName(), user.getLastName(), user.getGender(), user.getBirthDate());

        try {
            Session session = UserSessionFactory.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(userDAModel);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            throw e;
        }

        return true;
    }

    @Override
    public Boolean deleteUser(int id) {
        UserDAModel userDAModel = new UserDAModel();
        userDAModel.setId(id);

        try {
            Session session = UserSessionFactory.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(userDAModel);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            throw e;
        }

        return true;
    }

    @Override
    public Boolean updateUser(User user) {
        UserDAModel userDAModel = new UserDAModel(user.getId(), user.getLogin(), user.getLogin(), user.getPrivilegeLevel(),
                user.getFirstName(), user.getLastName(), user.getGender(), user.getBirthDate());

        try {
            Session session = UserSessionFactory.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.update(userDAModel);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            throw e;
        }

        return true;
    }

    @Override
    public User getUserByLogin(String login) {
        ArrayList<UserDAModel> userDAModel;

        try {
            userDAModel = (ArrayList<UserDAModel>) UserSessionFactory.getSessionFactory().openSession()
                    .createQuery("from UserDAModel where login = '" + login + "'").list();
        } catch (Exception e) {
            throw e;
        }

        User user;
        if (userDAModel.size() != 0)
            user = new User(userDAModel.get(0).getId(), userDAModel.get(0).getLogin(),
                    userDAModel.get(0).getPassword(), userDAModel.get(0).getPrivilegeLevel(),
                    userDAModel.get(0).getFirstName(), userDAModel.get(0).getLastName(), userDAModel.get(0).getGender(),
                    userDAModel.get(0).getBirthDate());
        else
            user = null;

        return user;
    }

    @Override
    public User getUserById(int id) {
        UserDAModel userDAModel;

        try {
            userDAModel = UserSessionFactory.getSessionFactory().openSession()
                    .get(UserDAModel.class, id);
        } catch (Exception e) {
            throw e;
        }

        User user;
        if (userDAModel != null)
            user = new User(userDAModel.getId(), userDAModel.getLogin(), userDAModel.getPassword(),
                    userDAModel.getPrivilegeLevel(), userDAModel.getFirstName(), userDAModel.getLastName(),
                    userDAModel.getGender(), userDAModel.getBirthDate());
        else
            user = null;

        return user;
    }

    @Override
    public User getUserAccount(String login, String password) {
        ArrayList<UserDAModel> userDAModel;

        try {
            userDAModel = (ArrayList<UserDAModel>) UserSessionFactory.getSessionFactory().openSession()
                    .createQuery("from UserDAModel where login = '" + login + "' and password = '" + password + "'").
                    list();
        } catch (Exception e) {
            throw e;
        }

        User user;
        if (userDAModel.size() != 0)
            user = new User(userDAModel.get(0).getId(), userDAModel.get(0).getLogin(),
                    userDAModel.get(0).getPassword(), userDAModel.get(0).getPrivilegeLevel(),
                    userDAModel.get(0).getFirstName(), userDAModel.get(0).getLastName(), userDAModel.get(0).getGender(),
                    userDAModel.get(0).getBirthDate());
        else
            user = null;

        return user;
    }

    @Override
    public ArrayList<User> getUsersList() {
        ArrayList<UserDAModel> userDAModelArr;

        try {
            userDAModelArr = (ArrayList<UserDAModel>) UserSessionFactory.getSessionFactory().openSession()
                    .createQuery("from UserDAModel").list();
        } catch (Exception e) {
            throw e;
        }

        ArrayList<User> userArr;
        if (userDAModelArr.size() != 0) {
            userArr = new ArrayList<>(0);
            for (int i = 0; i < userDAModelArr.size(); i++) {
                userArr.add(new User(userDAModelArr.get(i).getId(), userDAModelArr.get(i).getLogin(),
                        userDAModelArr.get(i).getPassword(), userDAModelArr.get(i).getPrivilegeLevel(),
                        userDAModelArr.get(i).getFirstName(), userDAModelArr.get(i).getLastName(),
                        userDAModelArr.get(i).getGender(), userDAModelArr.get(i).getBirthDate()));
            }
        }
        else
            userArr = null;

        return userArr;
    }
}
