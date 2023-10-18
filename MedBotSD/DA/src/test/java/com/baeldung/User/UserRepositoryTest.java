package com.baeldung.User;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class UserRepositoryTest {
    private final int TEST_ID = 10;
    private final String TEST_LOGIN = "qwerty";
    private final String TEST_PASSWORD = "12344qwer";
    private final int TEST_PRIVILEGE_LEVEL = 1;
    private final String TEST_FIRST_NAME = "Anton";
    private final String TEST_LAST_NAME = "Petrov";
    private final Boolean TEST_GENDER = true;
    private final Date TEST_BIRTH_DATE = Date.valueOf("2023-04-20");
    @Test
    void deleteUser() {
        UserDAModel TEST_USER_DAM = new UserDAModel(TEST_LOGIN, TEST_PASSWORD, TEST_PRIVILEGE_LEVEL,
                TEST_FIRST_NAME, TEST_LAST_NAME, TEST_GENDER, TEST_BIRTH_DATE);
        Session session = UserSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(TEST_USER_DAM);
        transaction.commit();
        session.close();

        UserRepository userRepository = new UserRepository();
        Boolean res = userRepository.deleteUser(TEST_USER_DAM.getId());

        int countUsersInTable = UserSessionFactory.getSessionFactory().openSession()
                .createQuery("from UserDAModel").list().size();
        assertEquals(countUsersInTable, 0);
        assertTrue(res);
    }

    @Test
    void getUserById() {
        UserDAModel TEST_USER_DAM = new UserDAModel(TEST_LOGIN, TEST_PASSWORD, TEST_PRIVILEGE_LEVEL,
                TEST_FIRST_NAME, TEST_LAST_NAME, TEST_GENDER, TEST_BIRTH_DATE);
        Session session = UserSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(TEST_USER_DAM);
        transaction.commit();
        session.close();

        UserRepository userRepository = new UserRepository();
        User TEST_USER = userRepository.getUserById(TEST_USER_DAM.getId());

        session = UserSessionFactory.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.delete(TEST_USER_DAM);
        transaction.commit();
        session.close();

        assertEquals(TEST_USER.getId(), TEST_USER_DAM.getId());
        assertEquals(TEST_USER.getLogin(), TEST_USER_DAM.getLogin());
        assertEquals(TEST_USER.getPassword(), TEST_USER_DAM.getPassword());
        assertEquals(TEST_USER.getPrivilegeLevel(), TEST_USER_DAM.getPrivilegeLevel());
        assertEquals(TEST_USER.getFirstName(), TEST_USER_DAM.getFirstName());
        assertEquals(TEST_USER.getLastName(), TEST_USER_DAM.getLastName());
        assertEquals(TEST_USER.getGender(), TEST_USER_DAM.getGender());
        assertEquals(TEST_USER.getBirthDate(), TEST_USER_DAM.getBirthDate());
    }
}
