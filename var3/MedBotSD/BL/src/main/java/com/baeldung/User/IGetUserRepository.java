package com.baeldung.User;

import java.util.ArrayList;

public interface IGetUserRepository {
    public User getUserByLogin(String login);
    public User getUserById(int id);
    public User getUserAccount(String login, String password);
    public ArrayList<User> getUsersList();
}