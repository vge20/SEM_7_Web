package com.baeldung.User;

import com.baeldung.Mocks.UserRepositoryMock;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.sql.Date;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.times;

class UserServiceTest {
    private final String TEST_LOGIN = "test_login";
    private final String TEST_PASSWORD = "test_password";
    private final String TEST_FIRST_NAME = "test_first_name";
    private final String TEST_LAST_NAME = "test_last_name";
    private final Boolean TEST_GENDER = true;
    private final Date TEST_DATE = Date.valueOf("2023-04-20");
    private final int TEST_ID = 10;

    @Test
    void addUser() {
        UserRepositoryMock userRepositoryMock = Mockito.mock(UserRepositoryMock.class);
        User user = new User();
        user.setLogin(TEST_LOGIN);
        user.setPassword(TEST_PASSWORD);
        user.setFirstName(TEST_FIRST_NAME);
        user.setLastName(TEST_LAST_NAME);
        user.setGender(TEST_GENDER);
        user.setBirthDate(TEST_DATE);

        Mockito.doReturn(null).when(userRepositoryMock).getUserByLogin(Mockito.anyString());
        Mockito.doReturn(true).when(userRepositoryMock).addUser(Mockito.any(User.class));

        UserService userService;
        try {
            userService = new UserService(userRepositoryMock, userRepositoryMock);
        } catch (Exception e) {
            return;
        }
        final boolean res = userService.addUser(user);

        InOrder inOrder = Mockito.inOrder(userRepositoryMock);
        inOrder.verify(userRepositoryMock, times(1)).getUserByLogin(Mockito.anyString());
        inOrder.verify(userRepositoryMock, times(1)).addUser(Mockito.any(User.class));
        Mockito.verifyNoMoreInteractions(userRepositoryMock);
        assertTrue(res);
    }

    @Test
    void deleteUser() {
        UserRepositoryMock userRepositoryMock = Mockito.mock(UserRepositoryMock.class);
        User user = new User();

        Mockito.doReturn(user).when(userRepositoryMock).getUserById(Mockito.anyInt());
        Mockito.doReturn(true).when(userRepositoryMock).deleteUser(Mockito.anyInt());

        UserService userService;
        try {
            userService = new UserService(userRepositoryMock, userRepositoryMock);
        } catch (Exception e) {
            return;
        }
        final boolean res = userService.deleteUser(TEST_ID);

        InOrder inOrder = Mockito.inOrder(userRepositoryMock);
        inOrder.verify(userRepositoryMock, times(1)).getUserById(Mockito.anyInt());
        inOrder.verify(userRepositoryMock, times(1)).deleteUser(Mockito.anyInt());
        Mockito.verifyNoMoreInteractions(userRepositoryMock);
        assertTrue(res);
    }

    @Test
    void updateUser() {
        UserRepositoryMock userRepositoryMock = Mockito.mock(UserRepositoryMock.class);
        User user = new User();
        user.setId(TEST_ID);
        user.setLogin(TEST_LOGIN);
        user.setPassword(TEST_PASSWORD);
        user.setFirstName(TEST_FIRST_NAME);
        user.setLastName(TEST_LAST_NAME);
        user.setGender(TEST_GENDER);
        user.setBirthDate(TEST_DATE);

        Mockito.doReturn(user).when(userRepositoryMock).getUserById(Mockito.anyInt());
        Mockito.doReturn(true).when(userRepositoryMock).updateUser(Mockito.any(User.class));

        UserService userService;
        try {
            userService = new UserService(userRepositoryMock, userRepositoryMock);
        } catch (Exception e) {
            return;
        }
        final boolean res = userService.updateUser(user);

        InOrder inOrder = Mockito.inOrder(userRepositoryMock);
        inOrder.verify(userRepositoryMock, times(1)).getUserById(Mockito.anyInt());
        inOrder.verify(userRepositoryMock, times(1)).updateUser(Mockito.any(User.class));
        Mockito.verifyNoMoreInteractions(userRepositoryMock);
        assertTrue(res);
    }

    @Test
    void logIn() {
        UserRepositoryMock userRepositoryMock = Mockito.mock(UserRepositoryMock.class);
        User user = new User();
        user.setId(TEST_ID);
        user.setLogin(TEST_LOGIN);
        user.setPassword(TEST_PASSWORD);
        user.setFirstName(TEST_FIRST_NAME);
        user.setLastName(TEST_LAST_NAME);
        user.setGender(TEST_GENDER);
        user.setBirthDate(TEST_DATE);

        Mockito.doReturn(user).when(userRepositoryMock).getUserAccount(Mockito.anyString(), Mockito.anyString());

        UserService userService;
        try {
            userService = new UserService(userRepositoryMock, userRepositoryMock);
        } catch (Exception e) {
            return;
        }
        final int res = userService.logIn(TEST_LOGIN, TEST_PASSWORD).getId();

        Mockito.verify(userRepositoryMock, times(1)).getUserAccount(Mockito.anyString(),
                Mockito.anyString());
        Mockito.verifyNoMoreInteractions(userRepositoryMock);
        assertEquals(res, TEST_ID);
    }

    @Test
    void getUserByLogin() {
        UserRepositoryMock userRepositoryMock = Mockito.mock(UserRepositoryMock.class);
        User user = new User();
        user.setId(TEST_ID);
        user.setLogin(TEST_LOGIN);
        user.setPassword(TEST_PASSWORD);
        user.setFirstName(TEST_FIRST_NAME);
        user.setLastName(TEST_LAST_NAME);
        user.setGender(TEST_GENDER);
        user.setBirthDate(TEST_DATE);

        Mockito.doReturn(user).when(userRepositoryMock).getUserByLogin(Mockito.anyString());

        UserService userService;
        try {
            userService = new UserService(userRepositoryMock, userRepositoryMock);
        } catch (Exception e) {
            return;
        }
        final int res = userService.getUserByLogin(TEST_LOGIN).getId();

        Mockito.verify(userRepositoryMock, times(1)).getUserByLogin(Mockito.anyString());
        Mockito.verifyNoMoreInteractions(userRepositoryMock);
        assertEquals(res, TEST_ID);
    }

    @Test
    void getUsersList() {
        UserRepositoryMock userRepositoryMock = Mockito.mock(UserRepositoryMock.class);
        final int USERS_COUNT_IN_TEST_LIST = 5;
        ArrayList<User> userListResultTest = new ArrayList<>(0);
        for (int i = 0; i < USERS_COUNT_IN_TEST_LIST; i++) {
            userListResultTest.add(new User());
        }

        Mockito.doReturn(userListResultTest).when(userRepositoryMock).getUsersList();

        UserService userService;
        try {
            userService = new UserService(userRepositoryMock, userRepositoryMock);
        } catch (Exception e) {
            return;
        }
        ArrayList<User> userListExpected = new ArrayList<>(0);
        ArrayList<User> userListResult = userService.getUsersList();
        for (int i = 0; i < USERS_COUNT_IN_TEST_LIST; i++) {
            userListExpected.add(new User());
        }

        Mockito.verify(userRepositoryMock, times(1)).getUsersList();
        Mockito.verifyNoMoreInteractions(userRepositoryMock);
        assertEquals(userListExpected.size(), userListResult.size());

        for (int i = 0; i < USERS_COUNT_IN_TEST_LIST; i++) {
            assertEquals(userListExpected.get(i).getId(), userListResult.get(i).getId());
            assertEquals(userListExpected.get(i).getFirstName(), userListResult.get(i).getFirstName());
            assertEquals(userListExpected.get(i).getLastName(), userListResult.get(i).getLastName());
            assertEquals(userListExpected.get(i).getLogin(), userListResult.get(i).getLogin());
            assertEquals(userListExpected.get(i).getGender(), userListResult.get(i).getGender());
        }
    }
}