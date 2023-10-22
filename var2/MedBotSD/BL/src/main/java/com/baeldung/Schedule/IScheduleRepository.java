package com.baeldung.Schedule;

public interface IScheduleRepository {
    public Schedule getSchedule(int idDoctor);
    public Boolean addSchedule(Schedule schedule);
    public Boolean updateSchedule(Schedule schedule);
    public Boolean deleteSchedule(int id);
}
