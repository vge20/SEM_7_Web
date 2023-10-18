package com.baeldung.User;

import java.util.ArrayList;

public interface IUserService {
    public Boolean addUser(User user);
    public Boolean deleteUser(int id);
    public Boolean updateUser(User user);
    public User logIn(String login, String password);
    public User getUserByLogin(String login);
    public ArrayList<User> getUsersList();
}
