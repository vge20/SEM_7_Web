package com.baeldung.Record;

import com.baeldung.Doctor.DoctorDAModel;
import com.baeldung.User.UserDAModel;
import com.baeldung.User.UserSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class RecordRepositoryTest {
    private final Date TEST_DATE = Date.valueOf("2023-04-06");
    private final Time TEST_START_TIME = Time.valueOf("12:00:00");
    private final Time TEST_END_TIME = Time.valueOf("12:30:00");
    private final String TEST_LOGIN = "qwerty";
    private final String TEST_PASSWORD = "12344qwer";
    private final int TEST_PRIVILEGE_LEVEL = 1;
    private final String TEST_FIRST_NAME = "Anton";
    private final String TEST_LAST_NAME = "Petrov";
    private final Boolean TEST_GENDER = true;
    private final Date TEST_BIRTH_DATE = Date.valueOf("2023-04-20");
    private final String TEST_SPECIALIZATION = "LOR";
    @Test
    void deleteRecord() {
        UserDAModel TEST_USER_DAM = new UserDAModel(TEST_LOGIN, TEST_PASSWORD, TEST_PRIVILEGE_LEVEL,
                TEST_FIRST_NAME, TEST_LAST_NAME, TEST_GENDER, TEST_BIRTH_DATE);
        Session session = UserSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(TEST_USER_DAM);
        transaction.commit();
        session.close();
        DoctorDAModel TEST_DOCTOR_DAM = new DoctorDAModel(TEST_FIRST_NAME, TEST_LAST_NAME,
                TEST_GENDER, TEST_SPECIALIZATION);
        session = DoctorSessionFactory.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.save(TEST_DOCTOR_DAM);
        transaction.commit();
        session.close();
        RecordDAModel TEST_RECORD_DAM = new RecordDAModel(TEST_DOCTOR_DAM.getId(), TEST_USER_DAM.getId(),
                TEST_DATE, TEST_START_TIME, TEST_END_TIME);
        session = RecordSessionFactory.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.save(TEST_RECORD_DAM);
        transaction.commit();
        session.close();

        RecordRepository recordRepository = new RecordRepository();
        Boolean res = null;
        try {
            res = recordRepository.deleteRecord(TEST_RECORD_DAM.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        session = UserSessionFactory.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.delete(TEST_USER_DAM);
        transaction.commit();
        session.close();
        session = DoctorSessionFactory.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.delete(TEST_DOCTOR_DAM);
        transaction.commit();
        session.close();

        int countRecordsInTable = RecordSessionFactory.getSessionFactory().openSession()
                .createQuery("from RecordDAModel").list().size();
        assertEquals(countRecordsInTable, 0);
        assertTrue(res);
    }

    @Test
    void getRecordsByUserDoctorDate() {
        UserDAModel TEST_USER_DAM = new UserDAModel(TEST_LOGIN, TEST_PASSWORD, TEST_PRIVILEGE_LEVEL,
                TEST_FIRST_NAME, TEST_LAST_NAME, TEST_GENDER, TEST_BIRTH_DATE);
        Session session = UserSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(TEST_USER_DAM);
        transaction.commit();
        session.close();
        DoctorDAModel TEST_DOCTOR_DAM = new DoctorDAModel(TEST_FIRST_NAME, TEST_LAST_NAME,
                TEST_GENDER, TEST_SPECIALIZATION);
        session = DoctorSessionFactory.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.save(TEST_DOCTOR_DAM);
        transaction.commit();
        session.close();
        RecordDAModel TEST_RECORD_DAM = new RecordDAModel(TEST_DOCTOR_DAM.getId(), TEST_USER_DAM.getId(),
                TEST_DATE, TEST_START_TIME, TEST_END_TIME);
        session = RecordSessionFactory.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.save(TEST_RECORD_DAM);
        transaction.commit();
        session.close();

        RecordRepository recordRepository = new RecordRepository();
        ArrayList<Record> records = null;
        try {
            records = recordRepository.getRecordsByUserDoctorDate(TEST_USER_DAM.getId(),
                    TEST_DOCTOR_DAM.getId(), TEST_DATE);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        session = RecordSessionFactory.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.delete(TEST_RECORD_DAM);
        transaction.commit();
        session.close();
        session = UserSessionFactory.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.delete(TEST_USER_DAM);
        transaction.commit();
        session.close();
        session = DoctorSessionFactory.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.delete(TEST_DOCTOR_DAM);
        transaction.commit();
        session.close();

        assertEquals(TEST_RECORD_DAM.getId(), records.get(0).getId());
        assertEquals(TEST_RECORD_DAM.getIdUser(), records.get(0).getIdUser());
        assertEquals(TEST_RECORD_DAM.getIdDoctor(), records.get(0).getIdDoctor());
        assertEquals(TEST_RECORD_DAM.getDate(), records.get(0).getDate());
        assertEquals(TEST_RECORD_DAM.getStartTime(), records.get(0).getStartTime());
        assertEquals(TEST_RECORD_DAM.getEndTime(), records.get(0).getEndTime());
    }
}
