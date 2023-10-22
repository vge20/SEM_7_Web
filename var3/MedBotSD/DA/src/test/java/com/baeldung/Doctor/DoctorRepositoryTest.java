package com.baeldung.Doctor;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class DoctorRepositoryTest {
    private final int TEST_ID = 10;
    private final String TEST_FIRST_NAME = "Anton";
    private final String TEST_LAST_NAME = "Petrov";
    private final Boolean TEST_GENDER = true;
    private final String TEST_SPECIALIZATION = "LOR";

    @Test
    void deleteDoctor() {
        DoctorDAModel TEST_DOCTOR_DAM = new DoctorDAModel(TEST_FIRST_NAME, TEST_LAST_NAME,
                TEST_GENDER, TEST_SPECIALIZATION);
        Session session = DoctorSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(TEST_DOCTOR_DAM);
        transaction.commit();
        session.close();

        DoctorRepository doctorRepository = new DoctorRepository();
        Boolean res = doctorRepository.deleteDoctor(TEST_DOCTOR_DAM.getId());

        int countDoctorsInTable = DoctorSessionFactory.getSessionFactory().openSession()
                .createQuery("from DoctorDAModel").list().size();
        assertEquals(countDoctorsInTable, 0);
        assertTrue(res);
    }

    @Test
    void getDoctorById() {
        DoctorDAModel TEST_DOCTOR_DAM = new DoctorDAModel(TEST_FIRST_NAME, TEST_LAST_NAME,
                TEST_GENDER, TEST_SPECIALIZATION);
        Session session = DoctorSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(TEST_DOCTOR_DAM);
        transaction.commit();
        session.close();

        DoctorRepository doctorRepository = new DoctorRepository();
        Doctor TEST_DOCTOR = doctorRepository.getDoctorById(TEST_DOCTOR_DAM.getId());
        System.out.println(TEST_DOCTOR_DAM.getId());
        session = DoctorSessionFactory.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.delete(TEST_DOCTOR_DAM);
        transaction.commit();
        session.close();

        assertEquals(TEST_DOCTOR.getId(), TEST_DOCTOR_DAM.getId());
        assertEquals(TEST_DOCTOR.getFirstName(), TEST_DOCTOR_DAM.getFirstName());
        assertEquals(TEST_DOCTOR.getLastName(), TEST_DOCTOR_DAM.getLastName());
        assertEquals(TEST_DOCTOR.getGender(), TEST_DOCTOR_DAM.getGender());
        assertEquals(TEST_DOCTOR.getSpecialization(), TEST_DOCTOR_DAM.getSpecialization());
    }
}
