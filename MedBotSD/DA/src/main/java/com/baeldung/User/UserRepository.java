package com.baeldung.User;

import com.baeldung.DataSource.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserRepository implements IUserRepository, IGetUserRepository {
    @Override
    public Boolean addUser(User user) throws Exception {
        UserDAModel userDAModel = new UserDAModel(user.getLogin(), user.getPassword(), user.getPrivilegeLevel(),
                user.getFirstName(), user.getLastName(), user.getGender(), user.getBirthDate());

        Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("insert into " +
                "Users(login, password, privilegeLevel, firstName, lastName, gender, birthDate) values " +
                "(?, ?, ?, ?, ?, ?, ?)");
        statement.setString(1, userDAModel.getLogin());
        statement.setString(2, userDAModel.getPassword());
        statement.setInt(3, userDAModel.getPrivilegeLevel());
        statement.setString(4, userDAModel.getFirstName());
        statement.setString(5, userDAModel.getLastName());
        statement.setBoolean(6, userDAModel.getGender());
        statement.setDate(7, userDAModel.getBirthDate());

        if (statement.executeUpdate() == 0)
            return false;
        else
            return true;
    }

    @Override
    public Boolean deleteUser(int id) throws Exception {
        Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("delete from users where id = ?");
        statement.setInt(1, id);

        if (statement.executeUpdate() == 0)
            return false;
        else
            return true;
    }

    @Override
    public Boolean updateUser(User user) throws Exception {
        UserDAModel userDAModel = new UserDAModel(user.getId(), user.getLogin(), user.getLogin(),
                user.getPrivilegeLevel(), user.getFirstName(), user.getLastName(), user.getGender(),
                user.getBirthDate());

        Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("update users set " +
                "login = ?, password = ?, privilegeLevel = ?, firstName = ?, lastName = ?, " +
                "gender = ?, birthDate = ? where id = ?");
        statement.setString(1, userDAModel.getLogin());
        statement.setString(2, userDAModel.getPassword());
        statement.setInt(3, userDAModel.getPrivilegeLevel());
        statement.setString(4, userDAModel.getFirstName());
        statement.setString(5, userDAModel.getLastName());
        statement.setBoolean(6, userDAModel.getGender());
        statement.setDate(7, userDAModel.getBirthDate());
        statement.setInt(8, userDAModel.getId());

        if (statement.executeUpdate() == 0)
            return false;
        else
            return true;
    }

    @Override
    public User getUserByLogin(String login) throws Exception {
        UserDAModel userDAModel;

        Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from users where login = ?");
        statement.setString(1, login);
        ResultSet queryRes = statement.executeQuery();

        if (queryRes.next()) {
            userDAModel = new UserDAModel();
            userDAModel.setId(queryRes.getInt("id"));
            userDAModel.setLogin(queryRes.getString("login"));
            userDAModel.setPassword(queryRes.getString("password"));
            userDAModel.setPrivilegeLevel(queryRes.getInt("privilegeLevel"));
            userDAModel.setFirstName(queryRes.getString("firstName"));
            userDAModel.setLastName(queryRes.getString("lastName"));
            userDAModel.setGender(queryRes.getBoolean("gender"));
            userDAModel.setBirthDate(queryRes.getDate("birthDate"));
        }
        else {
            userDAModel = null;
        }

        User user;
        if (userDAModel != null)
            user = new User(userDAModel.getId(), userDAModel.getLogin(),
                    userDAModel.getPassword(), userDAModel.getPrivilegeLevel(),
                    userDAModel.getFirstName(), userDAModel.getLastName(), userDAModel.getGender(),
                    userDAModel.getBirthDate());
        else
            user = null;

        return user;
    }

    @Override
    public User getUserById(int id) throws Exception {
        UserDAModel userDAModel;

        Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from users where id = ?");
        statement.setInt(1, id);
        ResultSet queryRes = statement.executeQuery();

        if (queryRes.next()) {
            userDAModel = new UserDAModel();
            userDAModel.setId(queryRes.getInt("id"));
            userDAModel.setLogin(queryRes.getString("login"));
            userDAModel.setPassword(queryRes.getString("password"));
            userDAModel.setPrivilegeLevel(queryRes.getInt("privilegeLevel"));
            userDAModel.setFirstName(queryRes.getString("firstName"));
            userDAModel.setLastName(queryRes.getString("lastName"));
            userDAModel.setGender(queryRes.getBoolean("gender"));
            userDAModel.setBirthDate(queryRes.getDate("birthDate"));
        }
        else {
            userDAModel = null;
        }

        User user;
        if (userDAModel != null)
            user = new User(userDAModel.getId(), userDAModel.getLogin(),
                    userDAModel.getPassword(), userDAModel.getPrivilegeLevel(),
                    userDAModel.getFirstName(), userDAModel.getLastName(), userDAModel.getGender(),
                    userDAModel.getBirthDate());
        else
            user = null;

        return user;
    }

    @Override
    public User getUserAccount(String login, String password) throws Exception {
        UserDAModel userDAModel;

        Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from users where " +
                "login = ? and password = ?");
        statement.setString(1, login);
        statement.setString(2, password);
        ResultSet queryRes = statement.executeQuery();

        if (queryRes.next()) {
            userDAModel = new UserDAModel();
            userDAModel.setId(queryRes.getInt("id"));
            userDAModel.setLogin(queryRes.getString("login"));
            userDAModel.setPassword(queryRes.getString("password"));
            userDAModel.setPrivilegeLevel(queryRes.getInt("privilegeLevel"));
            userDAModel.setFirstName(queryRes.getString("firstName"));
            userDAModel.setLastName(queryRes.getString("lastName"));
            userDAModel.setGender(queryRes.getBoolean("gender"));
            userDAModel.setBirthDate(queryRes.getDate("birthDate"));
        }
        else {
            userDAModel = null;
        }

        User user;
        if (userDAModel != null)
            user = new User(userDAModel.getId(), userDAModel.getLogin(),
                    userDAModel.getPassword(), userDAModel.getPrivilegeLevel(),
                    userDAModel.getFirstName(), userDAModel.getLastName(), userDAModel.getGender(),
                    userDAModel.getBirthDate());
        else
            user = null;

        return user;
    }

    @Override
    public ArrayList<User> getUsersList() throws Exception {
        Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from users");
        ResultSet queryRes = statement.executeQuery();

        ArrayList<UserDAModel> userDAModels = new ArrayList<>();
        while (queryRes.next()) {
            UserDAModel userDAModel = new UserDAModel();
            userDAModel.setId(queryRes.getInt("id"));
            userDAModel.setLogin(queryRes.getString("login"));
            userDAModel.setPassword(queryRes.getString("password"));
            userDAModel.setPrivilegeLevel(queryRes.getInt("privilegeLevel"));
            userDAModel.setFirstName(queryRes.getString("firstName"));
            userDAModel.setLastName(queryRes.getString("lastName"));
            userDAModel.setGender(queryRes.getBoolean("gender"));
            userDAModel.setBirthDate(queryRes.getDate("birthDate"));
            userDAModels.add(userDAModel);
        }

        ArrayList<User> usersList;
        if (userDAModels.size() != 0) {
            usersList = new ArrayList<>();
            for (int i = 0; i < userDAModels.size(); i++) {
                usersList.add(new User(userDAModels.get(i).getId(), userDAModels.get(i).getLogin(),
                        userDAModels.get(i).getPassword(), userDAModels.get(i).getPrivilegeLevel(),
                        userDAModels.get(i).getFirstName(), userDAModels.get(i).getLastName(),
                        userDAModels.get(i).getGender(), userDAModels.get(i).getBirthDate()));
            }
        }
        else {
            usersList = null;
        }

        return usersList;
    }
}
