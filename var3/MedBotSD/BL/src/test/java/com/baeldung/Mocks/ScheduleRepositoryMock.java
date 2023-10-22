package com.baeldung.Mocks;

import com.baeldung.Schedule.IScheduleRepository;
import com.baeldung.Schedule.Schedule;

public class ScheduleRepositoryMock implements IScheduleRepository {
    @Override
    public Schedule getSchedule(int idDoctor) {
        return null;
    }

    @Override
    public Boolean addSchedule(Schedule schedule) {
        return null;
    }

    @Override
    public Boolean updateSchedule(Schedule schedule) {
        return null;
    }

    @Override
    public Boolean deleteSchedule(int id) {
        return null;
    }
}
