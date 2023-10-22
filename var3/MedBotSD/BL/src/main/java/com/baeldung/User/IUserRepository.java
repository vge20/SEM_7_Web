package com.baeldung.User;

public interface IUserRepository {
    public Boolean addUser(User user);
    public Boolean deleteUser(int id);
    public Boolean updateUser(User user);
}