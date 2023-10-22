package com.baeldung.Record;

import com.baeldung.Mocks.DoctorRepositoryMock;
import com.baeldung.Mocks.ScheduleRepositoryMock;
import com.baeldung.Mocks.UserRepositoryMock;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import com.baeldung.Doctor.Doctor;
import com.baeldung.User.User;
import com.baeldung.Schedule.Schedule;

import com.baeldung.Mocks.RecordRepositoryMock;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertFalse;
import static org.mockito.Mockito.times;

class RecordServiceTest {
    private final int TEST_USER_ID = 10;
    private final int TEST_DOCTOR_ID = 20;

    private Record getTestRecord(String date, String startTime, String endTime) {
        Record record = new Record();
        record.setDate(Date.valueOf(date));
        record.setStartTime(Time.valueOf(startTime));
        record.setEndTime(Time.valueOf(endTime));
        record.setIdUser(TEST_USER_ID);
        record.setIdDoctor(TEST_DOCTOR_ID);

        return record;
    }

    @Test
    void addRecordInFreeSpace() {
        DoctorRepositoryMock doctorRepositoryMock = Mockito.mock(DoctorRepositoryMock.class);
        UserRepositoryMock userRepositoryMock = Mockito.mock(UserRepositoryMock.class);
        RecordRepositoryMock recordRepositoryMock = Mockito.mock(RecordRepositoryMock.class);
        ScheduleRepositoryMock scheduleRepositoryMock = Mockito.mock(ScheduleRepositoryMock.class);
        final String TEST_DATE = "2023-03-20";
        Record record = getTestRecord(TEST_DATE, "10:00:00", "10:30:00");
        User user = new User();
        Doctor doctor = new Doctor();
        Schedule schedule = new Schedule();
        ArrayList<Record> TEST_RECORDS_LIST = new ArrayList<>();
        Record TEST_RECORD_FOR_ARR = getTestRecord(TEST_DATE, "11:00:00", "11:20:00");
        TEST_RECORDS_LIST.add(TEST_RECORD_FOR_ARR);

        Mockito.doReturn(user).when(userRepositoryMock).getUserById(Mockito.anyInt());
        Mockito.doReturn(doctor).when(doctorRepositoryMock).getDoctorById(Mockito.anyInt());
        Mockito.doReturn(schedule).when(scheduleRepositoryMock).getSchedule(Mockito.anyInt());
        Mockito.doReturn(TEST_RECORDS_LIST).when(recordRepositoryMock).getRecordsByUserDate(Mockito.anyInt(),
                Mockito.any(Date.class));
        Mockito.doReturn(TEST_RECORDS_LIST).when(recordRepositoryMock).getRecordsByDoctorDate(Mockito.anyInt(),
                Mockito.any(Date.class));
        Mockito.doReturn(true).when(recordRepositoryMock).addRecord(Mockito.any(Record.class));

        RecordService recordService;
        try {
            recordService = new RecordService(recordRepositoryMock, doctorRepositoryMock,
                    scheduleRepositoryMock, userRepositoryMock);
        } catch (Exception e) {
            return;
        }
        final boolean res = recordService.addRecord(record);

        InOrder inOrder = Mockito.inOrder(recordRepositoryMock, userRepositoryMock, scheduleRepositoryMock,
                doctorRepositoryMock);
        inOrder.verify(userRepositoryMock, times(1)).getUserById(Mockito.anyInt());
        inOrder.verify(doctorRepositoryMock, times(1)).getDoctorById(Mockito.anyInt());
        inOrder.verify(scheduleRepositoryMock, times(1)).getSchedule(Mockito.anyInt());
        inOrder.verify(recordRepositoryMock, times(1)).getRecordsByUserDate(Mockito.anyInt(),
                Mockito.any(Date.class));
        inOrder.verify(recordRepositoryMock, times(1)).getRecordsByDoctorDate(Mockito.anyInt(),
                Mockito.any(Date.class));
        inOrder.verify(recordRepositoryMock, times(1)).addRecord(Mockito.any(Record.class));
        Mockito.verifyNoMoreInteractions(recordRepositoryMock, userRepositoryMock, scheduleRepositoryMock,
                doctorRepositoryMock);
        assertTrue(res);
    }

    @Test
    void addRecordInTimeIntervalThatIntersectsWithExistingDoctorRecord() {
        DoctorRepositoryMock doctorRepositoryMock = Mockito.mock(DoctorRepositoryMock.class);
        UserRepositoryMock userRepositoryMock = Mockito.mock(UserRepositoryMock.class);
        RecordRepositoryMock recordRepositoryMock = Mockito.mock(RecordRepositoryMock.class);
        ScheduleRepositoryMock scheduleRepositoryMock = Mockito.mock(ScheduleRepositoryMock.class);
        final String TEST_DATE = "2023-03-20";
        Record record = getTestRecord(TEST_DATE, "10:00:00", "10:30:00");
        User user = new User();
        Doctor doctor = new Doctor();
        Schedule schedule = new Schedule();
        ArrayList<Record> TEST_RECORDS_LIST = new ArrayList<>();
        Record TEST_RECORD_FOR_ARR = getTestRecord(TEST_DATE, "10:20:00", "10:45:00");
        TEST_RECORDS_LIST.add(TEST_RECORD_FOR_ARR);

        Mockito.doReturn(user).when(userRepositoryMock).getUserById(Mockito.anyInt());
        Mockito.doReturn(doctor).when(doctorRepositoryMock).getDoctorById(Mockito.anyInt());
        Mockito.doReturn(schedule).when(scheduleRepositoryMock).getSchedule(Mockito.anyInt());
        Mockito.doReturn(null).when(recordRepositoryMock).getRecordsByUserDate(Mockito.anyInt(),
                Mockito.any(Date.class));
        Mockito.doReturn(TEST_RECORDS_LIST).when(recordRepositoryMock).getRecordsByDoctorDate(Mockito.anyInt(),
                Mockito.any(Date.class));
        Mockito.doReturn(true).when(recordRepositoryMock).addRecord(Mockito.any(Record.class));

        RecordService recordService;
        try {
            recordService = new RecordService(recordRepositoryMock, doctorRepositoryMock,
                    scheduleRepositoryMock, userRepositoryMock);
        } catch (Exception e) {
            return;
        }
        final boolean res = recordService.addRecord(record);

        InOrder inOrder = Mockito.inOrder(recordRepositoryMock, userRepositoryMock, scheduleRepositoryMock,
                doctorRepositoryMock);
        inOrder.verify(userRepositoryMock, times(1)).getUserById(Mockito.anyInt());
        inOrder.verify(doctorRepositoryMock, times(1)).getDoctorById(Mockito.anyInt());
        inOrder.verify(scheduleRepositoryMock, times(1)).getSchedule(Mockito.anyInt());
        inOrder.verify(recordRepositoryMock, times(1)).getRecordsByUserDate(Mockito.anyInt(),
                Mockito.any(Date.class));
        inOrder.verify(recordRepositoryMock, times(1)).getRecordsByDoctorDate(Mockito.anyInt(),
                Mockito.any(Date.class));
        Mockito.verifyNoMoreInteractions(recordRepositoryMock, userRepositoryMock, scheduleRepositoryMock,
                doctorRepositoryMock);
        assertFalse(res);
    }

    @Test
    void addRecordInTimeIntervalThatIntersectsWithExistingUserRecord() {
        DoctorRepositoryMock doctorRepositoryMock = Mockito.mock(DoctorRepositoryMock.class);
        UserRepositoryMock userRepositoryMock = Mockito.mock(UserRepositoryMock.class);
        RecordRepositoryMock recordRepositoryMock = Mockito.mock(RecordRepositoryMock.class);
        ScheduleRepositoryMock scheduleRepositoryMock = Mockito.mock(ScheduleRepositoryMock.class);
        final String TEST_DATE = "2023-03-20";
        Record record = getTestRecord(TEST_DATE, "10:00:00", "10:30:00");
        User user = new User();
        Doctor doctor = new Doctor();
        Schedule schedule = new Schedule();
        ArrayList<Record> TEST_RECORDS_LIST = new ArrayList<>();
        Record TEST_RECORD_FOR_ARR = getTestRecord(TEST_DATE, "10:20:00", "10:45:00");
        TEST_RECORDS_LIST.add(TEST_RECORD_FOR_ARR);

        Mockito.doReturn(user).when(userRepositoryMock).getUserById(Mockito.anyInt());
        Mockito.doReturn(doctor).when(doctorRepositoryMock).getDoctorById(Mockito.anyInt());
        Mockito.doReturn(schedule).when(scheduleRepositoryMock).getSchedule(Mockito.anyInt());
        Mockito.doReturn(TEST_RECORDS_LIST).when(recordRepositoryMock).getRecordsByUserDate(Mockito.anyInt(),
                Mockito.any(Date.class));
        Mockito.doReturn(null).when(recordRepositoryMock).getRecordsByDoctorDate(Mockito.anyInt(),
                Mockito.any(Date.class));
        Mockito.doReturn(true).when(recordRepositoryMock).addRecord(Mockito.any(Record.class));

        RecordService recordService;
        try {
            recordService = new RecordService(recordRepositoryMock, doctorRepositoryMock,
                    scheduleRepositoryMock, userRepositoryMock);
        } catch (Exception e) {
            return;
        }
        final boolean res = recordService.addRecord(record);

        InOrder inOrder = Mockito.inOrder(recordRepositoryMock, userRepositoryMock, scheduleRepositoryMock,
                doctorRepositoryMock);
        inOrder.verify(userRepositoryMock, times(1)).getUserById(Mockito.anyInt());
        inOrder.verify(doctorRepositoryMock, times(1)).getDoctorById(Mockito.anyInt());
        inOrder.verify(scheduleRepositoryMock, times(1)).getSchedule(Mockito.anyInt());
        inOrder.verify(recordRepositoryMock, times(1)).getRecordsByUserDate(Mockito.anyInt(),
                Mockito.any(Date.class));
        Mockito.verifyNoMoreInteractions(recordRepositoryMock, userRepositoryMock, scheduleRepositoryMock,
                doctorRepositoryMock);
        assertFalse(res);
    }

    @Test
    void addRecordInTimeIntervalThatContainsAnExistingDoctorRecord() {
        DoctorRepositoryMock doctorRepositoryMock = Mockito.mock(DoctorRepositoryMock.class);
        UserRepositoryMock userRepositoryMock = Mockito.mock(UserRepositoryMock.class);
        RecordRepositoryMock recordRepositoryMock = Mockito.mock(RecordRepositoryMock.class);
        ScheduleRepositoryMock scheduleRepositoryMock = Mockito.mock(ScheduleRepositoryMock.class);
        final String TEST_DATE = "2023-03-20";
        Record record = getTestRecord(TEST_DATE, "10:00:00", "10:30:00");
        User user = new User();
        Doctor doctor = new Doctor();
        Schedule schedule = new Schedule();
        ArrayList<Record> TEST_RECORDS_LIST = new ArrayList<>();
        Record TEST_RECORD_FOR_ARR = getTestRecord(TEST_DATE, "10:10:00", "10:20:00");
        TEST_RECORDS_LIST.add(TEST_RECORD_FOR_ARR);

        Mockito.doReturn(user).when(userRepositoryMock).getUserById(Mockito.anyInt());
        Mockito.doReturn(doctor).when(doctorRepositoryMock).getDoctorById(Mockito.anyInt());
        Mockito.doReturn(schedule).when(scheduleRepositoryMock).getSchedule(Mockito.anyInt());
        Mockito.doReturn(null).when(recordRepositoryMock).getRecordsByUserDate(Mockito.anyInt(),
                Mockito.any(Date.class));
        Mockito.doReturn(TEST_RECORDS_LIST).when(recordRepositoryMock).getRecordsByDoctorDate(Mockito.anyInt(),
                Mockito.any(Date.class));
        Mockito.doReturn(true).when(recordRepositoryMock).addRecord(Mockito.any(Record.class));

        RecordService recordService;
        try {
            recordService = new RecordService(recordRepositoryMock, doctorRepositoryMock,
                    scheduleRepositoryMock, userRepositoryMock);
        } catch (Exception e) {
            return;
        }
        final boolean res = recordService.addRecord(record);

        InOrder inOrder = Mockito.inOrder(recordRepositoryMock, userRepositoryMock, scheduleRepositoryMock,
                doctorRepositoryMock);
        inOrder.verify(userRepositoryMock, times(1)).getUserById(Mockito.anyInt());
        inOrder.verify(doctorRepositoryMock, times(1)).getDoctorById(Mockito.anyInt());
        inOrder.verify(scheduleRepositoryMock, times(1)).getSchedule(Mockito.anyInt());
        inOrder.verify(recordRepositoryMock, times(1)).getRecordsByUserDate(Mockito.anyInt(),
                Mockito.any(Date.class));
        inOrder.verify(recordRepositoryMock, times(1)).getRecordsByDoctorDate(Mockito.anyInt(),
                Mockito.any(Date.class));
        Mockito.verifyNoMoreInteractions(recordRepositoryMock, userRepositoryMock, scheduleRepositoryMock,
                doctorRepositoryMock);
        assertFalse(res);
    }

    @Test
    void addRecordInTimeIntervalThatContainsAnExistingUserRecord() {
        DoctorRepositoryMock doctorRepositoryMock = Mockito.mock(DoctorRepositoryMock.class);
        UserRepositoryMock userRepositoryMock = Mockito.mock(UserRepositoryMock.class);
        RecordRepositoryMock recordRepositoryMock = Mockito.mock(RecordRepositoryMock.class);
        ScheduleRepositoryMock scheduleRepositoryMock = Mockito.mock(ScheduleRepositoryMock.class);
        final String TEST_DATE = "2023-03-20";
        Record record = getTestRecord(TEST_DATE, "10:00:00", "10:30:00");
        User user = new User();
        Doctor doctor = new Doctor();
        Schedule schedule = new Schedule();
        ArrayList<Record> TEST_RECORDS_LIST = new ArrayList<>();
        Record TEST_RECORD_FOR_ARR = getTestRecord(TEST_DATE, "10:10:00", "10:20:00");
        TEST_RECORDS_LIST.add(TEST_RECORD_FOR_ARR);

        Mockito.doReturn(user).when(userRepositoryMock).getUserById(Mockito.anyInt());
        Mockito.doReturn(doctor).when(doctorRepositoryMock).getDoctorById(Mockito.anyInt());
        Mockito.doReturn(schedule).when(scheduleRepositoryMock).getSchedule(Mockito.anyInt());
        Mockito.doReturn(TEST_RECORDS_LIST).when(recordRepositoryMock).getRecordsByUserDate(Mockito.anyInt(),
                Mockito.any(Date.class));
        Mockito.doReturn(null).when(recordRepositoryMock).getRecordsByDoctorDate(Mockito.anyInt(),
                Mockito.any(Date.class));
        Mockito.doReturn(true).when(recordRepositoryMock).addRecord(Mockito.any(Record.class));

        RecordService recordService;
        try {
            recordService = new RecordService(recordRepositoryMock, doctorRepositoryMock,
                    scheduleRepositoryMock, userRepositoryMock);
        } catch (Exception e) {
            return;
        }
        final boolean res = recordService.addRecord(record);

        InOrder inOrder = Mockito.inOrder(recordRepositoryMock, userRepositoryMock, scheduleRepositoryMock,
                doctorRepositoryMock);
        inOrder.verify(userRepositoryMock, times(1)).getUserById(Mockito.anyInt());
        inOrder.verify(doctorRepositoryMock, times(1)).getDoctorById(Mockito.anyInt());
        inOrder.verify(scheduleRepositoryMock, times(1)).getSchedule(Mockito.anyInt());
        inOrder.verify(recordRepositoryMock, times(1)).getRecordsByUserDate(Mockito.anyInt(),
                Mockito.any(Date.class));
        Mockito.verifyNoMoreInteractions(recordRepositoryMock, userRepositoryMock, scheduleRepositoryMock,
                doctorRepositoryMock);
        assertFalse(res);
    }

    @Test
    void addRecordInTimeIntervalThatContainedInExistingDoctorRecord() {
        DoctorRepositoryMock doctorRepositoryMock = Mockito.mock(DoctorRepositoryMock.class);
        UserRepositoryMock userRepositoryMock = Mockito.mock(UserRepositoryMock.class);
        RecordRepositoryMock recordRepositoryMock = Mockito.mock(RecordRepositoryMock.class);
        ScheduleRepositoryMock scheduleRepositoryMock = Mockito.mock(ScheduleRepositoryMock.class);
        final String TEST_DATE = "2023-03-20";
        Record record = getTestRecord(TEST_DATE, "10:00:00", "10:30:00");
        User user = new User();
        Doctor doctor = new Doctor();
        Schedule schedule = new Schedule();
        ArrayList<Record> TEST_RECORDS_LIST = new ArrayList<>();
        Record TEST_RECORD_FOR_ARR = getTestRecord(TEST_DATE, "9:50:00", "10:40:00");
        TEST_RECORDS_LIST.add(TEST_RECORD_FOR_ARR);

        Mockito.doReturn(user).when(userRepositoryMock).getUserById(Mockito.anyInt());
        Mockito.doReturn(doctor).when(doctorRepositoryMock).getDoctorById(Mockito.anyInt());
        Mockito.doReturn(schedule).when(scheduleRepositoryMock).getSchedule(Mockito.anyInt());
        Mockito.doReturn(null).when(recordRepositoryMock).getRecordsByUserDate(Mockito.anyInt(),
                Mockito.any(Date.class));
        Mockito.doReturn(TEST_RECORDS_LIST).when(recordRepositoryMock).getRecordsByDoctorDate(Mockito.anyInt(),
                Mockito.any(Date.class));
        Mockito.doReturn(true).when(recordRepositoryMock).addRecord(Mockito.any(Record.class));

        RecordService recordService;
        try {
            recordService = new RecordService(recordRepositoryMock, doctorRepositoryMock,
                    scheduleRepositoryMock, userRepositoryMock);
        } catch (Exception e) {
            return;
        }
        final boolean res = recordService.addRecord(record);

        InOrder inOrder = Mockito.inOrder(recordRepositoryMock, userRepositoryMock, scheduleRepositoryMock,
                doctorRepositoryMock);
        inOrder.verify(userRepositoryMock, times(1)).getUserById(Mockito.anyInt());
        inOrder.verify(doctorRepositoryMock, times(1)).getDoctorById(Mockito.anyInt());
        inOrder.verify(scheduleRepositoryMock, times(1)).getSchedule(Mockito.anyInt());
        inOrder.verify(recordRepositoryMock, times(1)).getRecordsByUserDate(Mockito.anyInt(),
                Mockito.any(Date.class));
        inOrder.verify(recordRepositoryMock, times(1)).getRecordsByDoctorDate(Mockito.anyInt(),
                Mockito.any(Date.class));
        Mockito.verifyNoMoreInteractions(recordRepositoryMock, userRepositoryMock, scheduleRepositoryMock,
                doctorRepositoryMock);
        assertFalse(res);
    }

    @Test
    void addRecordInTimeIntervalThatContainedInExistingUserRecord() {
        DoctorRepositoryMock doctorRepositoryMock = Mockito.mock(DoctorRepositoryMock.class);
        UserRepositoryMock userRepositoryMock = Mockito.mock(UserRepositoryMock.class);
        RecordRepositoryMock recordRepositoryMock = Mockito.mock(RecordRepositoryMock.class);
        ScheduleRepositoryMock scheduleRepositoryMock = Mockito.mock(ScheduleRepositoryMock.class);
        final String TEST_DATE = "2023-03-20";
        Record record = getTestRecord(TEST_DATE, "10:00:00", "10:30:00");
        User user = new User();
        Doctor doctor = new Doctor();
        Schedule schedule = new Schedule();
        ArrayList<Record> TEST_RECORDS_LIST = new ArrayList<>();
        Record TEST_RECORD_FOR_ARR = getTestRecord(TEST_DATE, "9:50:00", "10:40:00");
        TEST_RECORDS_LIST.add(TEST_RECORD_FOR_ARR);

        Mockito.doReturn(user).when(userRepositoryMock).getUserById(Mockito.anyInt());
        Mockito.doReturn(doctor).when(doctorRepositoryMock).getDoctorById(Mockito.anyInt());
        Mockito.doReturn(schedule).when(scheduleRepositoryMock).getSchedule(Mockito.anyInt());
        Mockito.doReturn(TEST_RECORDS_LIST).when(recordRepositoryMock).getRecordsByUserDate(Mockito.anyInt(),
                Mockito.any(Date.class));
        Mockito.doReturn(null).when(recordRepositoryMock).getRecordsByDoctorDate(Mockito.anyInt(),
                Mockito.any(Date.class));
        Mockito.doReturn(true).when(recordRepositoryMock).addRecord(Mockito.any(Record.class));

        RecordService recordService;
        try {
            recordService = new RecordService(recordRepositoryMock, doctorRepositoryMock,
                    scheduleRepositoryMock, userRepositoryMock);
        } catch (Exception e) {
            return;
        }
        final boolean res = recordService.addRecord(record);

        InOrder inOrder = Mockito.inOrder(recordRepositoryMock, userRepositoryMock, scheduleRepositoryMock,
                doctorRepositoryMock);
        inOrder.verify(userRepositoryMock, times(1)).getUserById(Mockito.anyInt());
        inOrder.verify(doctorRepositoryMock, times(1)).getDoctorById(Mockito.anyInt());
        inOrder.verify(scheduleRepositoryMock, times(1)).getSchedule(Mockito.anyInt());
        inOrder.verify(recordRepositoryMock, times(1)).getRecordsByUserDate(Mockito.anyInt(),
                Mockito.any(Date.class));
        Mockito.verifyNoMoreInteractions(recordRepositoryMock, userRepositoryMock, scheduleRepositoryMock,
                doctorRepositoryMock);
        assertFalse(res);
    }

    @Test
    void addRecordThereIsNoUser() {
        DoctorRepositoryMock doctorRepositoryMock = Mockito.mock(DoctorRepositoryMock.class);
        UserRepositoryMock userRepositoryMock = Mockito.mock(UserRepositoryMock.class);
        RecordRepositoryMock recordRepositoryMock = Mockito.mock(RecordRepositoryMock.class);
        ScheduleRepositoryMock scheduleRepositoryMock = Mockito.mock(ScheduleRepositoryMock.class);
        final String TEST_DATE = "2023-03-20";
        Record record = getTestRecord(TEST_DATE, "10:00:00", "10:30:00");
        User user = new User();
        Doctor doctor = new Doctor();
        Schedule schedule = new Schedule();
        ArrayList<Record> TEST_RECORDS_LIST = new ArrayList<>();
        Record TEST_RECORD_FOR_ARR = getTestRecord(TEST_DATE, "10:40:00", "11:00:00");
        TEST_RECORDS_LIST.add(TEST_RECORD_FOR_ARR);

        Mockito.doReturn(null).when(userRepositoryMock).getUserById(Mockito.anyInt());
        Mockito.doReturn(doctor).when(doctorRepositoryMock).getDoctorById(Mockito.anyInt());
        Mockito.doReturn(schedule).when(scheduleRepositoryMock).getSchedule(Mockito.anyInt());
        Mockito.doReturn(TEST_RECORDS_LIST).when(recordRepositoryMock).getRecordsByUserDate(Mockito.anyInt(),
                Mockito.any(Date.class));
        Mockito.doReturn(TEST_RECORDS_LIST).when(recordRepositoryMock).getRecordsByDoctorDate(Mockito.anyInt(),
                Mockito.any(Date.class));
        Mockito.doReturn(true).when(recordRepositoryMock).addRecord(Mockito.any(Record.class));

        RecordService recordService;
        try {
            recordService = new RecordService(recordRepositoryMock, doctorRepositoryMock,
                    scheduleRepositoryMock, userRepositoryMock);
        } catch (Exception e) {
            return;
        }
        final boolean res = recordService.addRecord(record);

        Mockito.verify(userRepositoryMock, times(1)).getUserById(Mockito.anyInt());
        Mockito.verifyNoMoreInteractions(recordRepositoryMock, userRepositoryMock, scheduleRepositoryMock,
                doctorRepositoryMock);
        assertFalse(res);
    }

    @Test
    void addRecordThereIsNoDoctor() {
        DoctorRepositoryMock doctorRepositoryMock = Mockito.mock(DoctorRepositoryMock.class);
        UserRepositoryMock userRepositoryMock = Mockito.mock(UserRepositoryMock.class);
        RecordRepositoryMock recordRepositoryMock = Mockito.mock(RecordRepositoryMock.class);
        ScheduleRepositoryMock scheduleRepositoryMock = Mockito.mock(ScheduleRepositoryMock.class);
        final String TEST_DATE = "2023-03-20";
        Record record = getTestRecord(TEST_DATE, "10:00:00", "10:30:00");
        User user = new User();
        Doctor doctor = new Doctor();
        Schedule schedule = new Schedule();
        ArrayList<Record> TEST_RECORDS_LIST = new ArrayList<>();
        Record TEST_RECORD_FOR_ARR = getTestRecord(TEST_DATE, "10:40:00", "11:00:00");
        TEST_RECORDS_LIST.add(TEST_RECORD_FOR_ARR);

        Mockito.doReturn(user).when(userRepositoryMock).getUserById(Mockito.anyInt());
        Mockito.doReturn(null).when(doctorRepositoryMock).getDoctorById(Mockito.anyInt());
        Mockito.doReturn(schedule).when(scheduleRepositoryMock).getSchedule(Mockito.anyInt());
        Mockito.doReturn(TEST_RECORDS_LIST).when(recordRepositoryMock).getRecordsByUserDate(Mockito.anyInt(),
                Mockito.any(Date.class));
        Mockito.doReturn(TEST_RECORDS_LIST).when(recordRepositoryMock).getRecordsByDoctorDate(Mockito.anyInt(),
                Mockito.any(Date.class));
        Mockito.doReturn(true).when(recordRepositoryMock).addRecord(Mockito.any(Record.class));

        RecordService recordService;
        try {
            recordService = new RecordService(recordRepositoryMock, doctorRepositoryMock,
                    scheduleRepositoryMock, userRepositoryMock);
        } catch (Exception e) {
            return;
        }
        final boolean res = recordService.addRecord(record);

        InOrder inOrder = Mockito.inOrder(recordRepositoryMock, userRepositoryMock, scheduleRepositoryMock,
                doctorRepositoryMock);
        inOrder.verify(userRepositoryMock, times(1)).getUserById(Mockito.anyInt());
        inOrder.verify(doctorRepositoryMock, times(1)).getDoctorById(Mockito.anyInt());
        Mockito.verifyNoMoreInteractions(recordRepositoryMock, userRepositoryMock, scheduleRepositoryMock,
                doctorRepositoryMock);
        assertFalse(res);
    }

    @Test
    void addRecordInWeekendDay() {
        DoctorRepositoryMock doctorRepositoryMock = Mockito.mock(DoctorRepositoryMock.class);
        UserRepositoryMock userRepositoryMock = Mockito.mock(UserRepositoryMock.class);
        RecordRepositoryMock recordRepositoryMock = Mockito.mock(RecordRepositoryMock.class);
        ScheduleRepositoryMock scheduleRepositoryMock = Mockito.mock(ScheduleRepositoryMock.class);
        final String TEST_DATE = "2023-04-01";
        Record record = getTestRecord(TEST_DATE, "10:10:00", "10:20:00");
        User user = new User();
        Doctor doctor = new Doctor();
        Schedule schedule = new Schedule();
        ArrayList<Record> TEST_RECORDS_LIST = new ArrayList<>();
        Record TEST_RECORD_FOR_ARR = getTestRecord(TEST_DATE, "11:00:00", "11:20:00");
        TEST_RECORDS_LIST.add(TEST_RECORD_FOR_ARR);

        Mockito.doReturn(user).when(userRepositoryMock).getUserById(Mockito.anyInt());
        Mockito.doReturn(doctor).when(doctorRepositoryMock).getDoctorById(Mockito.anyInt());
        Mockito.doReturn(schedule).when(scheduleRepositoryMock).getSchedule(Mockito.anyInt());
        Mockito.doReturn(TEST_RECORDS_LIST).when(recordRepositoryMock).getRecordsByUserDate(Mockito.anyInt(),
                Mockito.any(Date.class));
        Mockito.doReturn(TEST_RECORDS_LIST).when(recordRepositoryMock).getRecordsByDoctorDate(Mockito.anyInt(),
                Mockito.any(Date.class));
        Mockito.doReturn(true).when(recordRepositoryMock).addRecord(Mockito.any(Record.class));

        RecordService recordService;
        try {
            recordService = new RecordService(recordRepositoryMock, doctorRepositoryMock,
                    scheduleRepositoryMock, userRepositoryMock);
        } catch (Exception e) {
            return;
        }
        final boolean res = recordService.addRecord(record);

        InOrder inOrder = Mockito.inOrder(recordRepositoryMock, userRepositoryMock, scheduleRepositoryMock,
                doctorRepositoryMock);
        inOrder.verify(userRepositoryMock, times(1)).getUserById(Mockito.anyInt());
        inOrder.verify(doctorRepositoryMock, times(1)).getDoctorById(Mockito.anyInt());
        Mockito.verifyNoMoreInteractions(recordRepositoryMock, userRepositoryMock, scheduleRepositoryMock,
                doctorRepositoryMock);
        assertFalse(res);
    }

    @Test
    void addRecordWithEndTimeLessThanStartTime() {
        DoctorRepositoryMock doctorRepositoryMock = Mockito.mock(DoctorRepositoryMock.class);
        UserRepositoryMock userRepositoryMock = Mockito.mock(UserRepositoryMock.class);
        RecordRepositoryMock recordRepositoryMock = Mockito.mock(RecordRepositoryMock.class);
        ScheduleRepositoryMock scheduleRepositoryMock = Mockito.mock(ScheduleRepositoryMock.class);
        final String TEST_DATE = "2023-04-03";
        Record record = getTestRecord(TEST_DATE, "10:20:00", "10:10:00");
        User user = new User();
        Doctor doctor = new Doctor();
        Schedule schedule = new Schedule();
        ArrayList<Record> TEST_RECORDS_LIST = new ArrayList<>();
        Record TEST_RECORD_FOR_ARR = getTestRecord(TEST_DATE, "11:00:00", "11:20:00");
        TEST_RECORDS_LIST.add(TEST_RECORD_FOR_ARR);

        Mockito.doReturn(user).when(userRepositoryMock).getUserById(Mockito.anyInt());
        Mockito.doReturn(doctor).when(doctorRepositoryMock).getDoctorById(Mockito.anyInt());
        Mockito.doReturn(schedule).when(scheduleRepositoryMock).getSchedule(Mockito.anyInt());
        Mockito.doReturn(TEST_RECORDS_LIST).when(recordRepositoryMock).getRecordsByUserDate(Mockito.anyInt(),
                Mockito.any(Date.class));
        Mockito.doReturn(TEST_RECORDS_LIST).when(recordRepositoryMock).getRecordsByDoctorDate(Mockito.anyInt(),
                Mockito.any(Date.class));
        Mockito.doReturn(true).when(recordRepositoryMock).addRecord(Mockito.any(Record.class));

        RecordService recordService;
        try {
            recordService = new RecordService(recordRepositoryMock, doctorRepositoryMock,
                    scheduleRepositoryMock, userRepositoryMock);
        } catch (Exception e) {
            return;
        }
        final boolean res = recordService.addRecord(record);

        InOrder inOrder = Mockito.inOrder(recordRepositoryMock, userRepositoryMock, scheduleRepositoryMock,
                doctorRepositoryMock);
        inOrder.verify(userRepositoryMock, times(1)).getUserById(Mockito.anyInt());
        inOrder.verify(doctorRepositoryMock, times(1)).getDoctorById(Mockito.anyInt());
        inOrder.verify(scheduleRepositoryMock, times(1)).getSchedule(Mockito.anyInt());
        Mockito.verifyNoMoreInteractions(recordRepositoryMock, userRepositoryMock, scheduleRepositoryMock,
                doctorRepositoryMock);
        assertFalse(res);
    }

    @Test
    void addRecordNotAccordingTheSchedule() {
        DoctorRepositoryMock doctorRepositoryMock = Mockito.mock(DoctorRepositoryMock.class);
        UserRepositoryMock userRepositoryMock = Mockito.mock(UserRepositoryMock.class);
        RecordRepositoryMock recordRepositoryMock = Mockito.mock(RecordRepositoryMock.class);
        ScheduleRepositoryMock scheduleRepositoryMock = Mockito.mock(ScheduleRepositoryMock.class);
        final String TEST_DATE = "2023-04-03";
        Record record = getTestRecord(TEST_DATE, "8:20:00", "8:30:00");
        User user = new User();
        Doctor doctor = new Doctor();
        Schedule schedule = new Schedule();
        ArrayList<Record> TEST_RECORDS_LIST = new ArrayList<>();
        Record TEST_RECORD_FOR_ARR = getTestRecord(TEST_DATE, "11:00:00", "11:20:00");
        TEST_RECORDS_LIST.add(TEST_RECORD_FOR_ARR);

        Mockito.doReturn(user).when(userRepositoryMock).getUserById(Mockito.anyInt());
        Mockito.doReturn(doctor).when(doctorRepositoryMock).getDoctorById(Mockito.anyInt());
        Mockito.doReturn(schedule).when(scheduleRepositoryMock).getSchedule(Mockito.anyInt());
        Mockito.doReturn(TEST_RECORDS_LIST).when(recordRepositoryMock).getRecordsByUserDate(Mockito.anyInt(),
                Mockito.any(Date.class));
        Mockito.doReturn(TEST_RECORDS_LIST).when(recordRepositoryMock).getRecordsByDoctorDate(Mockito.anyInt(),
                Mockito.any(Date.class));
        Mockito.doReturn(true).when(recordRepositoryMock).addRecord(Mockito.any(Record.class));

        RecordService recordService;
        try {
            recordService = new RecordService(recordRepositoryMock, doctorRepositoryMock,
                    scheduleRepositoryMock, userRepositoryMock);
        } catch (Exception e) {
            return;
        }
        final boolean res = recordService.addRecord(record);

        InOrder inOrder = Mockito.inOrder(recordRepositoryMock, userRepositoryMock, scheduleRepositoryMock,
                doctorRepositoryMock);
        inOrder.verify(userRepositoryMock, times(1)).getUserById(Mockito.anyInt());
        inOrder.verify(doctorRepositoryMock, times(1)).getDoctorById(Mockito.anyInt());
        inOrder.verify(scheduleRepositoryMock, times(1)).getSchedule(Mockito.anyInt());
        Mockito.verifyNoMoreInteractions(recordRepositoryMock, userRepositoryMock, scheduleRepositoryMock,
                doctorRepositoryMock);
        assertFalse(res);
    }

    @Test
    void deleteRecord() {
        DoctorRepositoryMock doctorRepositoryMock = Mockito.mock(DoctorRepositoryMock.class);
        UserRepositoryMock userRepositoryMock = Mockito.mock(UserRepositoryMock.class);
        RecordRepositoryMock recordRepositoryMock = Mockito.mock(RecordRepositoryMock.class);
        ScheduleRepositoryMock scheduleRepositoryMock = Mockito.mock(ScheduleRepositoryMock.class);
        final String TEST_DATE = "2023-03-20";
        Record record = getTestRecord(TEST_DATE, "11:00:00", "11:20:00");
        Doctor doctor = new Doctor();
        User user = new User();
        ArrayList<Record> TEST_RECORDS_LIST = new ArrayList<>();
        Record TEST_RECORD_FOR_ARR = getTestRecord(TEST_DATE, "11:00:00", "11:20:00");
        TEST_RECORDS_LIST.add(TEST_RECORD_FOR_ARR);

        Mockito.doReturn(user).when(userRepositoryMock).getUserById(Mockito.anyInt());
        Mockito.doReturn(doctor).when(doctorRepositoryMock).getDoctorById(Mockito.anyInt());
        Mockito.doReturn(TEST_RECORDS_LIST).when(recordRepositoryMock).getRecordsByUserDoctorDate(Mockito.anyInt(),
                Mockito.anyInt(), Mockito.any(Date.class));
        Mockito.doReturn(true).when(recordRepositoryMock).deleteRecord(Mockito.anyInt());

        RecordService recordService;
        try {
            recordService = new RecordService(recordRepositoryMock, doctorRepositoryMock,
                    scheduleRepositoryMock, userRepositoryMock);
        } catch (Exception e) {
            return;
        }
        final boolean res = recordService.deleteRecord(record);

        InOrder inOrder = Mockito.inOrder(recordRepositoryMock, userRepositoryMock, doctorRepositoryMock);
        inOrder.verify(userRepositoryMock, times(1)).getUserById(Mockito.anyInt());
        inOrder.verify(doctorRepositoryMock, times(1)).getDoctorById(Mockito.anyInt());
        inOrder.verify(recordRepositoryMock, times(1)).getRecordsByUserDoctorDate(
                Mockito.anyInt(), Mockito.anyInt(), Mockito.any(Date.class));
        inOrder.verify(recordRepositoryMock, times(1)).deleteRecord(Mockito.anyInt());
        Mockito.verifyNoMoreInteractions(recordRepositoryMock, userRepositoryMock, doctorRepositoryMock);
        assertTrue(res);
    }

    @Test
    void updateRecord() {
        DoctorRepositoryMock doctorRepositoryMock = Mockito.mock(DoctorRepositoryMock.class);
        UserRepositoryMock userRepositoryMock = Mockito.mock(UserRepositoryMock.class);
        RecordRepositoryMock recordRepositoryMock = Mockito.mock(RecordRepositoryMock.class);
        ScheduleRepositoryMock scheduleRepositoryMock = Mockito.mock(ScheduleRepositoryMock.class);
        final String TEST_DATE = "2023-03-20";
        Record record = getTestRecord(TEST_DATE, "10:20:00", "10:30:00");
        Doctor doctor = new Doctor();
        User user = new User();
        ArrayList<Record> TEST_RECORDS_LIST = new ArrayList<>();
        Record TEST_RECORD_FOR_ARR = getTestRecord(TEST_DATE, "11:00:00", "11:20:00");
        TEST_RECORDS_LIST.add(TEST_RECORD_FOR_ARR);

        Mockito.doReturn(user).when(userRepositoryMock).getUserById(Mockito.anyInt());
        Mockito.doReturn(doctor).when(doctorRepositoryMock).getDoctorById(Mockito.anyInt());
        Mockito.doReturn(TEST_RECORDS_LIST).when(recordRepositoryMock).getRecordsByUserDoctorDate(Mockito.anyInt(),
                Mockito.anyInt(), Mockito.any(Date.class));
        Mockito.doReturn(true).when(recordRepositoryMock).updateRecord(Mockito.any(Record.class));

        RecordService recordService;
        try {
            recordService = new RecordService(recordRepositoryMock, doctorRepositoryMock,
                    scheduleRepositoryMock, userRepositoryMock);
        } catch (Exception e) {
            return;
        }
        final boolean res = recordService.updateRecord(record);

        InOrder inOrder = Mockito.inOrder(recordRepositoryMock, userRepositoryMock, doctorRepositoryMock);
        inOrder.verify(userRepositoryMock, times(1)).getUserById(Mockito.anyInt());
        inOrder.verify(doctorRepositoryMock, times(1)).getDoctorById(Mockito.anyInt());
        inOrder.verify(recordRepositoryMock, times(1)).getRecordsByUserDoctorDate(
                Mockito.anyInt(), Mockito.anyInt(), Mockito.any(Date.class));
        inOrder.verify(recordRepositoryMock, times(1)).updateRecord(Mockito.any(Record.class));
        Mockito.verifyNoMoreInteractions(recordRepositoryMock, userRepositoryMock, doctorRepositoryMock);
        assertTrue(res);
    }

    @Test
    void getRecordsByDate() {
        DoctorRepositoryMock doctorRepositoryMock = Mockito.mock(DoctorRepositoryMock.class);
        UserRepositoryMock userRepositoryMock = Mockito.mock(UserRepositoryMock.class);
        RecordRepositoryMock recordRepositoryMock = Mockito.mock(RecordRepositoryMock.class);
        ScheduleRepositoryMock scheduleRepositoryMock = Mockito.mock(ScheduleRepositoryMock.class);
        final int RECORDS_COUNT_IN_TEST_LIST = 5;
        Record record = getTestRecord("2023-03-21", "10:20:00", "10:30:00");
        ArrayList<Record> recordsListResultTest = new ArrayList<>(RECORDS_COUNT_IN_TEST_LIST);
        recordsListResultTest.add(record);

        Mockito.doReturn(recordsListResultTest).when(recordRepositoryMock).getRecordsByDoctorDate(Mockito.anyInt(),
                Mockito.any(Date.class));

        RecordService recordService;
        try {
            recordService = new RecordService(recordRepositoryMock, doctorRepositoryMock,
                    scheduleRepositoryMock, userRepositoryMock);
        } catch (Exception e) {
            return;
        }
        ArrayList<Record> recordsListExpected = new ArrayList<>(RECORDS_COUNT_IN_TEST_LIST);
        recordsListExpected.add(record);

        final int TEST_DOCTOR_ID = 10;
        final Date TEST_DATE = Date.valueOf("2023-03-21");
        ArrayList<Record> recordsListResult = recordService.getRecordsByDate(TEST_DOCTOR_ID, TEST_DATE);

        Mockito.verify(recordRepositoryMock, times(1)).getRecordsByDoctorDate(Mockito.anyInt(),
                Mockito.any(Date.class));
        Mockito.verifyNoMoreInteractions(recordRepositoryMock);
        assertEquals(recordsListResult.size(), recordsListExpected.size());

        for (int i = 0; i < recordsListExpected.size(); i++) {
            assertEquals(recordsListExpected.get(i).getId(), recordsListResult.get(i).getId());
            assertEquals(recordsListExpected.get(i).getIdUser(), recordsListResult.get(i).getIdUser());
            assertEquals(recordsListExpected.get(i).getIdDoctor(), recordsListResult.get(i).getIdDoctor());
        }
    }
}