package com.baeldung.Schedule;

import com.baeldung.Doctor.DoctorDAModel;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;

import java.sql.Time;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class ScheduleRepositoryTest {
    private final int TEST_ID = 10;
    private final int TEST_ID_DOCTOR = 10;
    private final Time TEST_START_TIME = Time.valueOf("12:00:00");
    private final Time TEST_END_TIME = Time.valueOf("12:30:00");
    private final String TEST_FIRST_NAME = "Anton";
    private final String TEST_LAST_NAME = "Petrov";
    private final Boolean TEST_GENDER = true;
    private final String TEST_SPECIALIZATION = "LOR";
    @Test
    void getSchedule() {
        DoctorDAModel TEST_DOCTOR_DAM = new DoctorDAModel(TEST_FIRST_NAME, TEST_LAST_NAME,
                TEST_GENDER, TEST_SPECIALIZATION);
        Session session = DoctorSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(TEST_DOCTOR_DAM);
        transaction.commit();
        session.close();
        ScheduleDAModel TEST_SCHEDULE_DAM = new ScheduleDAModel(TEST_DOCTOR_DAM.getId(), TEST_START_TIME,
                TEST_END_TIME, TEST_START_TIME, TEST_END_TIME, TEST_START_TIME, TEST_END_TIME,
                TEST_START_TIME, TEST_END_TIME, TEST_START_TIME, TEST_END_TIME);
        session = ScheduleSessionFactory.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.save(TEST_SCHEDULE_DAM);
        transaction.commit();
        session.close();

        ScheduleRepository scheduleRepository = new ScheduleRepository();
        Schedule TEST_SCHEDULE = null;
        try {
            TEST_SCHEDULE = scheduleRepository.getSchedule(TEST_DOCTOR_DAM.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        session = ScheduleSessionFactory.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.delete(TEST_SCHEDULE_DAM);
        transaction.commit();
        session.close();
        session = DoctorSessionFactory.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.delete(TEST_DOCTOR_DAM);
        transaction.commit();
        session.close();

        assertEquals(TEST_SCHEDULE.getId(), TEST_SCHEDULE_DAM.getId());
        assertEquals(TEST_SCHEDULE.getIdDoctor(), TEST_SCHEDULE_DAM.getIdDoctor());
    }

    @Test
    void deleteSchedule() {
        DoctorDAModel TEST_DOCTOR_DAM = new DoctorDAModel(TEST_FIRST_NAME, TEST_LAST_NAME,
                TEST_GENDER, TEST_SPECIALIZATION);
        Session session = DoctorSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(TEST_DOCTOR_DAM);
        transaction.commit();
        session.close();
        ScheduleDAModel TEST_SCHEDULE_DAM = new ScheduleDAModel(TEST_DOCTOR_DAM.getId(), TEST_START_TIME,
                TEST_END_TIME, TEST_START_TIME, TEST_END_TIME, TEST_START_TIME, TEST_END_TIME,
                TEST_START_TIME, TEST_END_TIME, TEST_START_TIME, TEST_END_TIME);
        session = ScheduleSessionFactory.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.save(TEST_SCHEDULE_DAM);
        transaction.commit();
        session.close();

        ScheduleRepository scheduleRepository = new ScheduleRepository();
        boolean res = false;
        try {
            res = scheduleRepository.deleteSchedule(TEST_SCHEDULE_DAM.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        session = DoctorSessionFactory.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.delete(TEST_DOCTOR_DAM);
        transaction.commit();
        session.close();

        int countSchedulesInTable = ScheduleSessionFactory.getSessionFactory().openSession()
                .createQuery("from ScheduleDAModel ").list().size();
        assertEquals(countSchedulesInTable, 0);
        assertTrue(res);
    }
}
