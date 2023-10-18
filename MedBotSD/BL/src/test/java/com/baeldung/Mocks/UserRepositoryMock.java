package com.baeldung.Mocks;

import com.baeldung.User.IGetUserRepository;
import com.baeldung.User.IUserRepository;
import com.baeldung.User.User;

import java.util.ArrayList;

public class UserRepositoryMock implements IUserRepository, IGetUserRepository {
    public User getUserByLogin(String login) {
        return null;
    }

    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public User getUserAccount(String login, String password) {
        return null;
    }

    @Override
    public ArrayList<User> getUsersList() {
        return null;
    }

    @Override
    public Boolean addUser(User user) {
        return null;
    }

    @Override
    public Boolean deleteUser(int id) {
        return null;
    }

    @Override
    public Boolean updateUser(User user) {
        return null;
    }
}

