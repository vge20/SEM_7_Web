package DA.Schedule;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class ScheduleRepository implements IScheduleRepository {
    @Override
    public Schedule getSchedule(int idDoctor) {
        ArrayList<ScheduleDAModel> scheduleDAModel;

        try {
            scheduleDAModel = (ArrayList<ScheduleDAModel>) ScheduleSessionFactory.getSessionFactory().openSession()
                    .createQuery("from ScheduleDAModel as s where s.idDoctor = " + Integer.toString(idDoctor)).list();
        } catch (Exception e) {
            throw e;
        }

        Schedule schedule;
        if (scheduleDAModel.size() != 0)
            schedule = new Schedule(scheduleDAModel.get(0).getId(), scheduleDAModel.get(0).getIdDoctor(),
                    scheduleDAModel.get(0).getMondayStartTime(), scheduleDAModel.get(0).getMondayEndTime());
        else
            schedule = null;

        return schedule;
    }

    @Override
    public Boolean addSchedule(Schedule schedule) {
        ScheduleDAModel scheduleDAModel = new ScheduleDAModel(schedule.getIdDoctor(),
                schedule.getStartByDay(0), schedule.getEndByDay(0),
                schedule.getStartByDay(1), schedule.getEndByDay(1),
                schedule.getStartByDay(2), schedule.getEndByDay(2),
                schedule.getStartByDay(3), schedule.getEndByDay(3),
                schedule.getStartByDay(4), schedule.getEndByDay(4));

        try {
            Session session = ScheduleSessionFactory.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(scheduleDAModel);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            throw e;
        }

        return true;
    }

    @Override
    public Boolean updateSchedule(Schedule schedule) {
        ScheduleDAModel scheduleDAModel = new ScheduleDAModel(schedule.getId(), schedule.getIdDoctor(),
                schedule.getStartByDay(0), schedule.getEndByDay(0),
                schedule.getStartByDay(1), schedule.getEndByDay(1),
                schedule.getStartByDay(2), schedule.getEndByDay(2),
                schedule.getStartByDay(3), schedule.getEndByDay(3),
                schedule.getStartByDay(4), schedule.getEndByDay(4));

        try {
            Session session = ScheduleSessionFactory.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.update(scheduleDAModel);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            throw e;
        }

        return true;
    }

    @Override
    public Boolean deleteSchedule(int id) {
        ScheduleDAModel scheduleDAModel = new ScheduleDAModel();
        scheduleDAModel.setId(id);

        try {
            Session session = ScheduleSessionFactory.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(scheduleDAModel);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            throw e;
        }

        return true;
    }
}

