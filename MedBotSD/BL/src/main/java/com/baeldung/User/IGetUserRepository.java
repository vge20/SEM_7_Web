package com.baeldung.User;

import java.util.ArrayList;

public interface IGetUserRepository {
    public User getUserByLogin(String login) throws Exception;
    public User getUserById(int id) throws Exception;
    public User getUserAccount(String login, String password) throws Exception;
    public ArrayList<User> getUsersList() throws Exception;
}