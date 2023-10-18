package com.baeldung.Doctor;

import com.baeldung.Mocks.DoctorRepositoryMock;
import com.baeldung.Mocks.ScheduleRepositoryMock;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import com.baeldung.Schedule.Schedule;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.times;

class DoctorServiceTest {
    @Test
    void addDoctor() {
        DoctorRepositoryMock doctorRepositoryMock = Mockito.mock(DoctorRepositoryMock.class);
        ScheduleRepositoryMock scheduleRepositoryMock = Mockito.mock(ScheduleRepositoryMock.class);
        final String TEST_FIRST_NAME = "test_first_name";
        final String TEST_LAST_NAME = "test_last_name";
        final boolean TEST_GENDER = true;
        final String TEST_SPECIALIZATION = "test_specialization";
        Doctor doctor = new Doctor();
        doctor.setFirstName(TEST_FIRST_NAME);
        doctor.setLastName(TEST_LAST_NAME);
        doctor.setGender(TEST_GENDER);
        doctor.setSpecialization(TEST_SPECIALIZATION);

        Mockito.when(doctorRepositoryMock.getDoctorByParameters(Mockito.anyString(),
                Mockito.anyString(), Mockito.anyBoolean(), Mockito.anyString())).thenReturn(null).thenReturn(doctor);
        Mockito.doReturn(true).when(doctorRepositoryMock).addDoctor(Mockito.any(Doctor.class));
        Mockito.doReturn(true).when(scheduleRepositoryMock).addSchedule(Mockito.any(Schedule.class));

        DoctorService doctorService;
        try {
            doctorService = new DoctorService(doctorRepositoryMock, doctorRepositoryMock,
                    scheduleRepositoryMock);
        } catch (Exception e) {
            return;
        }
        final boolean res = doctorService.addDoctor(doctor);

        InOrder inOrder = Mockito.inOrder(doctorRepositoryMock, scheduleRepositoryMock);
        inOrder.verify(doctorRepositoryMock, times(1)).getDoctorByParameters(Mockito.anyString(),
                Mockito.anyString(), Mockito.anyBoolean(), Mockito.anyString());
        inOrder.verify(doctorRepositoryMock, times(1)).addDoctor(Mockito.any(Doctor.class));
        inOrder.verify(doctorRepositoryMock, times(1)).getDoctorByParameters(Mockito.anyString(),
                Mockito.anyString(), Mockito.anyBoolean(), Mockito.anyString());
        inOrder.verify(scheduleRepositoryMock, times(1)).addSchedule(Mockito.any(Schedule.class));
        Mockito.verifyNoMoreInteractions(doctorRepositoryMock, scheduleRepositoryMock);
        assertTrue(res);
    }

    @Test
    void deleteDoctor() {
        DoctorRepositoryMock doctorRepositoryMock = Mockito.mock(DoctorRepositoryMock.class);
        ScheduleRepositoryMock scheduleRepositoryMock = Mockito.mock(ScheduleRepositoryMock.class);
        Doctor doctor = new Doctor();
        final int TEST_ID = 10;
        Schedule schedule = new Schedule();
        schedule.setId(TEST_ID);

        Mockito.doReturn(doctor).when(doctorRepositoryMock).getDoctorById(Mockito.anyInt());
        Mockito.doReturn(true).when(doctorRepositoryMock).deleteDoctor(Mockito.anyInt());
        Mockito.doReturn(schedule).when(scheduleRepositoryMock).getSchedule(Mockito.anyInt());
        Mockito.doReturn(true).when(scheduleRepositoryMock).deleteSchedule(Mockito.anyInt());

        DoctorService doctorService;
        try {
            doctorService = new DoctorService(doctorRepositoryMock, doctorRepositoryMock,
                    scheduleRepositoryMock);
        } catch (Exception e) {
            return;
        }
        final boolean res = doctorService.deleteDoctor(TEST_ID);

        InOrder inOrder = Mockito.inOrder(doctorRepositoryMock, scheduleRepositoryMock);
        inOrder.verify(doctorRepositoryMock, times(1)).getDoctorById(Mockito.anyInt());
        inOrder.verify(scheduleRepositoryMock, times(1)).getSchedule(Mockito.anyInt());
        inOrder.verify(scheduleRepositoryMock, times(1)).deleteSchedule(Mockito.anyInt());
        inOrder.verify(doctorRepositoryMock, times(1)).deleteDoctor(Mockito.anyInt());
        Mockito.verifyNoMoreInteractions(doctorRepositoryMock, scheduleRepositoryMock);
        assertTrue(res);
    }

    @Test
    void updateDoctor() {
        DoctorRepositoryMock doctorRepositoryMock = Mockito.mock(DoctorRepositoryMock.class);
        ScheduleRepositoryMock scheduleRepositoryMock = Mockito.mock(ScheduleRepositoryMock.class);
        final int TEST_ID = 10;
        Doctor doctor = new Doctor();
        doctor.setId(TEST_ID);

        Mockito.doReturn(doctor).when(doctorRepositoryMock).getDoctorById(Mockito.anyInt());
        Mockito.doReturn(true).when(doctorRepositoryMock).updateDoctor(Mockito.any(Doctor.class));

        DoctorService doctorService;
        try {
            doctorService = new DoctorService(doctorRepositoryMock, doctorRepositoryMock,
                    scheduleRepositoryMock);
        } catch (Exception e) {
            return;
        }
        final boolean res = doctorService.updateDoctor(doctor);

        InOrder inOrder = Mockito.inOrder(doctorRepositoryMock);
        inOrder.verify(doctorRepositoryMock, times(1)).getDoctorById(Mockito.anyInt());
        inOrder.verify(doctorRepositoryMock, times(1)).updateDoctor(Mockito.any(Doctor.class));
        Mockito.verifyNoMoreInteractions(doctorRepositoryMock);
        assertTrue(res);
    }

    @Test
    void getDoctorsList() {
        DoctorRepositoryMock doctorRepositoryMock = Mockito.mock(DoctorRepositoryMock.class);
        ScheduleRepositoryMock scheduleRepositoryMock = Mockito.mock(ScheduleRepositoryMock.class);
        final int DOCTORS_COUNT_IN_TEST_LIST = 5;
        ArrayList<Doctor> doctorsListResultTest = new ArrayList<>(0);
        for (int i = 0; i < DOCTORS_COUNT_IN_TEST_LIST; i++)
            doctorsListResultTest.add(new Doctor());

        Mockito.doReturn(doctorsListResultTest).when(doctorRepositoryMock)
                .getDoctorsList();

        DoctorService doctorService;
        try {
            doctorService = new DoctorService(doctorRepositoryMock, doctorRepositoryMock,
                    scheduleRepositoryMock);
        } catch (Exception e) {
            return;
        }
        final String TEST_SPECIALIZATION = "test_specialization";
        ArrayList<Doctor> doctorsListResult = doctorService.getDoctorsList();
        ArrayList<Doctor> doctorsListExpected = new ArrayList<>(0);
        for (int i = 0; i < DOCTORS_COUNT_IN_TEST_LIST; i++)
            doctorsListExpected.add(new Doctor());

        Mockito.verify(doctorRepositoryMock, times(1))
                .getDoctorsList();
        Mockito.verifyNoMoreInteractions(doctorRepositoryMock);
        assertEquals(doctorsListExpected.size(), doctorsListResult.size());

        for (int i = 0; i < DOCTORS_COUNT_IN_TEST_LIST; i++) {
            assertEquals(doctorsListExpected.get(i).getFirstName(), doctorsListResult.get(i).getFirstName());
            assertEquals(doctorsListExpected.get(i).getLastName(), doctorsListResult.get(i).getLastName());
            assertEquals(doctorsListExpected.get(i).getGender(), doctorsListResult.get(i).getGender());
            assertEquals(doctorsListExpected.get(i).getSpecialization(), doctorsListResult.get(i).getSpecialization());
        }
    }
}