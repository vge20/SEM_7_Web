package com.baeldung.User;

public interface IUserRepository {
    public Boolean addUser(User user) throws Exception;
    public Boolean deleteUser(int id) throws Exception;
    public Boolean updateUser(User user) throws Exception;
}