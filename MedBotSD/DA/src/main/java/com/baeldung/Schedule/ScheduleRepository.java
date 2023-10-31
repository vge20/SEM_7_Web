package com.baeldung.Schedule;

import com.baeldung.DataSource.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ScheduleRepository implements IScheduleRepository {
    @Override
    public Schedule getSchedule(int idDoctor) throws Exception {
        Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from schedules " +
                "where idDoctor = ?");
        statement.setInt(1, idDoctor);
        ResultSet queryRes = statement.executeQuery();

        ScheduleDAModel scheduleDAModel = null;
        if (queryRes.next()) {
            scheduleDAModel = new ScheduleDAModel();
            scheduleDAModel.setId(queryRes.getInt("id"));
            scheduleDAModel.setIdDoctor(queryRes.getInt("idDoctor"));
            scheduleDAModel.setMondayStartTime(queryRes.getTime("mondayStartTime"));
            scheduleDAModel.setMondayEndTime(queryRes.getTime("mondayEndTime"));
            scheduleDAModel.setTuesdayStartTime(queryRes.getTime("tuesdayStartTime"));
            scheduleDAModel.setTuesdayEndTime(queryRes.getTime("tuesdayEndTime"));
            scheduleDAModel.setWednesdayStartTime(queryRes.getTime("wednesdayStartTime"));
            scheduleDAModel.setWednesdayEndTime(queryRes.getTime("wednesdayEndTime"));
            scheduleDAModel.setThursdayStartTime(queryRes.getTime("thursdayStartTime"));
            scheduleDAModel.setThursdayEndTime(queryRes.getTime("thursdayEndTime"));
            scheduleDAModel.setFridayStartTime(queryRes.getTime("fridayStartTime"));
            scheduleDAModel.setFridayEndTime(queryRes.getTime("fridayEndTime"));
        }

        Schedule schedule;
        if (scheduleDAModel != null) {
            schedule = new Schedule(scheduleDAModel.getId(), scheduleDAModel.getIdDoctor(),
                    scheduleDAModel.getMondayStartTime(), scheduleDAModel.getMondayEndTime());
        }
        else {
            schedule = null;
        }

        return schedule;
    }

    @Override
    public Boolean addSchedule(Schedule schedule) throws Exception {
        ScheduleDAModel scheduleDAModel = new ScheduleDAModel(schedule.getId(), schedule.getIdDoctor(),
                schedule.getStartByDay(0), schedule.getEndByDay(0),
                schedule.getStartByDay(1), schedule.getEndByDay(1),
                schedule.getStartByDay(2), schedule.getEndByDay(2),
                schedule.getStartByDay(3), schedule.getEndByDay(3),
                schedule.getStartByDay(4), schedule.getEndByDay(4));

        Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("insert into " +
                "Schedules(idDoctor, mondayStartTime, mondayEndTime, tuesdayStartTime, " +
                "tuesdayEndTime, wednesdayStartTime, wednesdayEndTime, thursdayStartTime, " +
                "thursdayEndTime, fridayStartTime, fridayEndTime) values " +
                "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        statement.setInt(1, scheduleDAModel.getIdDoctor());
        statement.setTime(2, scheduleDAModel.getMondayStartTime());
        statement.setTime(3, scheduleDAModel.getMondayEndTime());
        statement.setTime(4, scheduleDAModel.getTuesdayStartTime());
        statement.setTime(5, scheduleDAModel.getTuesdayEndTime());
        statement.setTime(6, scheduleDAModel.getWednesdayStartTime());
        statement.setTime(7, scheduleDAModel.getWednesdayEndTime());
        statement.setTime(8, scheduleDAModel.getThursdayStartTime());
        statement.setTime(9, scheduleDAModel.getThursdayEndTime());
        statement.setTime(10, scheduleDAModel.getFridayStartTime());
        statement.setTime(11, scheduleDAModel.getFridayEndTime());

        if (statement.executeUpdate() == 0)
            return false;
        else
            return true;
    }

    @Override
    public Boolean updateSchedule(Schedule schedule) throws Exception {
        ScheduleDAModel scheduleDAModel = new ScheduleDAModel(schedule.getId(), schedule.getIdDoctor(),
                schedule.getStartByDay(0), schedule.getEndByDay(0),
                schedule.getStartByDay(1), schedule.getEndByDay(1),
                schedule.getStartByDay(2), schedule.getEndByDay(2),
                schedule.getStartByDay(3), schedule.getEndByDay(3),
                schedule.getStartByDay(4), schedule.getEndByDay(4));

        Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("update schedules " +
                "set idDoctor = ?, mondayStartTime = ?, mondayEndTime = ?, tuesdayStartTime = ?, " +
                "tuesdayEndTime = ?, wednesdayStartTime = ?, wednesdayEndTime = ?, thursdayStartTime = ?, " +
                "thursdayEndTime = ?, fridayStartTime = ?, fridayEndTime = ?" +
                "where id = ?");
        statement.setInt(1, scheduleDAModel.getIdDoctor());
        statement.setTime(2, scheduleDAModel.getMondayStartTime());
        statement.setTime(3, scheduleDAModel.getMondayEndTime());
        statement.setTime(4, scheduleDAModel.getTuesdayStartTime());
        statement.setTime(5, scheduleDAModel.getTuesdayEndTime());
        statement.setTime(6, scheduleDAModel.getWednesdayStartTime());
        statement.setTime(7, scheduleDAModel.getWednesdayEndTime());
        statement.setTime(8, scheduleDAModel.getThursdayStartTime());
        statement.setTime(9, scheduleDAModel.getThursdayEndTime());
        statement.setTime(10, scheduleDAModel.getFridayStartTime());
        statement.setTime(11, scheduleDAModel.getFridayEndTime());
        statement.setInt(12, scheduleDAModel.getId());

        if (statement.executeUpdate() == 0)
            return false;
        else
            return true;
    }

    @Override
    public Boolean deleteSchedule(int id) throws Exception {
        Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("delete from schedules " +
                "where id = ?");
        statement.setInt(1, id);

        if (statement.executeUpdate() == 0)
            return false;
        else
            return true;
    }
}

