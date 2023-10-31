package com.baeldung.Schedule;

public interface IScheduleRepository {
    public Schedule getSchedule(int idDoctor) throws Exception;
    public Boolean addSchedule(Schedule schedule) throws Exception;
    public Boolean updateSchedule(Schedule schedule) throws Exception;
    public Boolean deleteSchedule(int id) throws Exception;
}
