package com.baeldung.Record;

import com.baeldung.Doctor.Doctor;
import com.baeldung.Doctor.IGetDoctorRepository;
import com.baeldung.Schedule.IScheduleRepository;
import com.baeldung.Schedule.Schedule;
import com.baeldung.User.IGetUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;

public class RecordService implements IRecordService {

    private IRecordRepository recordRep;
    private IGetDoctorRepository getDoctorRep;
    private IScheduleRepository scheduleRep;
    private IGetUserRepository getUserRep;
    private Logger logger;

    public RecordService(IRecordRepository recordRepository, IGetDoctorRepository getDoctorRepository,
                         IScheduleRepository scheduleRepository, IGetUserRepository getUserRepository) throws IOException {
        this.recordRep = recordRepository;
        this.getDoctorRep = getDoctorRepository;
        this.scheduleRep = scheduleRepository;
        this.getUserRep = getUserRepository;
        try {
            FileInputStream in = new FileInputStream("src/main/resources/config.properties");
            Properties props = new Properties();
            props.load(in);

            if (props.getProperty("log_level").equals("info"))
                this.logger = LoggerFactory.getLogger("info.logger");
            else
                this.logger = LoggerFactory.getLogger("error.logger");

        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }
    }

    @Override
    public Boolean addRecord(Record record) {
        Doctor tmpDoctor;
        try {
            tmpDoctor = getDoctorRep.getDoctorById(record.getIdDoctor());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (getUserRep.getUserById(record.getIdUser()) == null) {
            logger.info("Неудачаная попытка добавить запись: id доктора - " + record.getIdDoctor() +
                    ", id пользователя - " + record.getIdUser() + ", дата - " + record.getDate() +
                    ", время начала - " + record.getStartTime() + ", время окончания - " + record.getEndTime());
            return false;
        }
        else if (tmpDoctor == null) {
            logger.info("Неудачаная попытка добавить запись: id доктора - " + record.getIdDoctor() +
                    ", id пользователя - " + record.getIdUser() + ", дата - " + record.getDate() +
                    ", время начала - " + record.getStartTime() + ", время окончания - " + record.getEndTime());
            return false;
        }

        Calendar c = Calendar.getInstance();
        c.setTime(record.getDate());
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        if (dayOfWeek == 1 || dayOfWeek == 7) {
            logger.info("Неудачаная попытка добавить запись: id доктора - " + record.getIdDoctor() +
                    ", id пользователя - " + record.getIdUser() + ", дата - " + record.getDate() +
                    ", время начала - " + record.getStartTime() + ", время окончания - " + record.getEndTime());
            return false;
        }

        Schedule schedule;
        try {
            schedule = scheduleRep.getSchedule(record.getIdDoctor());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (record.getStartTime().compareTo(record.getEndTime()) >= 0) {
            logger.info("Неудачаная попытка добавить запись: id доктора - " + record.getIdDoctor() +
                    ", id пользователя - " + record.getIdUser() + ", дата - " + record.getDate() +
                    ", время начала - " + record.getStartTime() + ", время окончания - " + record.getEndTime());
            return false;
        }

        if (schedule.getStartByDay(dayOfWeek - 2).compareTo(record.getStartTime()) > 0
                || schedule.getEndByDay(dayOfWeek - 2).compareTo(record.getEndTime()) < 0) {
            logger.info("Неудачаная попытка добавить запись: id доктора - " + record.getIdDoctor() +
                    ", id пользователя - " + record.getIdUser() + ", дата - " + record.getDate() +
                    ", время начала - " + record.getStartTime() + ", время окончания - " + record.getEndTime());
            return false;
        }

        ArrayList<Record> recordsUserByDate = null;
        try {
            recordsUserByDate = recordRep.getRecordsByUserDate(record.getIdUser(), record.getDate());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (recordsUserByDate != null)
            for (int i = 0; i < recordsUserByDate.size(); i++) {
                if (record.getStartTime().compareTo(recordsUserByDate.get(i).getStartTime()) <= 0 &&
                        record.getEndTime().compareTo(recordsUserByDate.get(i).getEndTime()) >= 0) {
                    logger.info("Неудачаная попытка добавить запись: id доктора - " + record.getIdDoctor() +
                            ", id пользователя - " + record.getIdUser() + ", дата - " + record.getDate() +
                            ", время начала - " + record.getStartTime() + ", время окончания - " + record.getEndTime());
                    return false;
                }
                else if (record.getStartTime().compareTo(recordsUserByDate.get(i).getStartTime()) >= 0 &&
                        record.getStartTime().compareTo(recordsUserByDate.get(i).getEndTime()) <= 0) {
                    logger.info("Неудачаная попытка добавить запись: id доктора - " + record.getIdDoctor() +
                            ", id пользователя - " + record.getIdUser() + ", дата - " + record.getDate() +
                            ", время начала - " + record.getStartTime() + ", время окончания - " + record.getEndTime());
                    return false;
                }
                else if (record.getEndTime().compareTo(recordsUserByDate.get(i).getStartTime()) >= 0 &&
                        record.getEndTime().compareTo(recordsUserByDate.get(i).getEndTime()) <= 0) {
                    logger.info("Неудачаная попытка добавить запись: id доктора - " + record.getIdDoctor() +
                            ", id пользователя - " + record.getIdUser() + ", дата - " + record.getDate() +
                            ", время начала - " + record.getStartTime() + ", время окончания - " + record.getEndTime());
                    return false;
                }
            }

        ArrayList<Record> recordsDoctorByDate = null;
        try {
            recordsDoctorByDate = recordRep.getRecordsByDoctorDate(record.getIdDoctor(),
                    record.getDate());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (recordsDoctorByDate != null)
            for (int i = 0; i < recordsDoctorByDate.size(); i++) {
                if (record.getStartTime().compareTo(recordsDoctorByDate.get(i).getStartTime()) <= 0 &&
                        record.getEndTime().compareTo(recordsDoctorByDate.get(i).getEndTime()) >= 0) {
                    logger.info("Неудачаная попытка добавить запись: id доктора - " + record.getIdDoctor() +
                            ", id пользователя - " + record.getIdUser() + ", дата - " + record.getDate() +
                            ", время начала - " + record.getStartTime() + ", время окончания - " + record.getEndTime());
                    return false;
                }
                else if (record.getStartTime().compareTo(recordsDoctorByDate.get(i).getStartTime()) >= 0 &&
                        record.getStartTime().compareTo(recordsDoctorByDate.get(i).getEndTime()) <= 0) {
                    logger.info("Неудачаная попытка добавить запись: id доктора - " + record.getIdDoctor() +
                            ", id пользователя - " + record.getIdUser() + ", дата - " + record.getDate() +
                            ", время начала - " + record.getStartTime() + ", время окончания - " + record.getEndTime());
                    return false;
                }
                else if (record.getEndTime().compareTo(recordsDoctorByDate.get(i).getStartTime()) >= 0 &&
                        record.getEndTime().compareTo(recordsDoctorByDate.get(i).getEndTime()) <= 0) {
                    logger.info("Неудачаная попытка добавить запись: id доктора - " + record.getIdDoctor() +
                            ", id пользователя - " + record.getIdUser() + ", дата - " + record.getDate() +
                            ", время начала - " + record.getStartTime() + ", время окончания - " + record.getEndTime());
                    return false;
                }
            }

        Boolean res = null;
        try {
            res = recordRep.addRecord(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (res)
            logger.info("Добавлена запись: id доктора - " + record.getIdDoctor() +
                    ", id пользователя - " + record.getIdUser() + ", дата - " + record.getDate() +
                    ", время начала - " + record.getStartTime() + ", время окончания - " + record.getEndTime());
        else
            logger.info("Неудачаная попытка добавить запись: id доктора - " + record.getIdDoctor() +
                    ", id пользователя - " + record.getIdUser() + ", дата - " + record.getDate() +
                    ", время начала - " + record.getStartTime() + ", время окончания - " + record.getEndTime());

        return res;
    }

    @Override
    public Boolean deleteRecord(Record record) {
        Doctor tmpDoctor = null;
        try {
            tmpDoctor = getDoctorRep.getDoctorById(record.getIdDoctor());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (getUserRep.getUserById(record.getIdUser()) == null) {
            logger.info("Неудачаная попытка удалить запись: id доктора - " + record.getIdDoctor() +
                    ", id пользователя - " + record.getIdUser() + ", дата - " + record.getDate() +
                    ", время начала - " + record.getStartTime() + ", время окончания - " + record.getEndTime());
            return false;
        }
        else if (tmpDoctor == null) {
            logger.info("Неудачаная попытка удалить запись: id доктора - " + record.getIdDoctor() +
                    ", id пользователя - " + record.getIdUser() + ", дата - " + record.getDate() +
                    ", время начала - " + record.getStartTime() + ", время окончания - " + record.getEndTime());
            return false;
        }

        ArrayList<Record> recordsUserDoctorByDate = null;
        try {
            recordsUserDoctorByDate = recordRep.getRecordsByUserDoctorDate(record.getIdUser(),
                    record.getIdDoctor(), record.getDate());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Boolean res = false;

        for (int i = 0; i < recordsUserDoctorByDate.size(); i++) {
            if (record.getStartTime().compareTo(recordsUserDoctorByDate.get(i).getStartTime()) == 0 &&
                    record.getEndTime().compareTo(recordsUserDoctorByDate.get(i).getEndTime()) == 0 &&
                    record.getIdUser() == recordsUserDoctorByDate.get(i).getIdUser() &&
                    record.getIdDoctor() == recordsUserDoctorByDate.get(i).getIdDoctor()) {
                try {
                    res = recordRep.deleteRecord(record.getId());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        if (res)
            logger.info("Удалена запись: id доктора - " + record.getIdDoctor() +
                    ", id пользователя - " + record.getIdUser() + ", дата - " + record.getDate() +
                    ", время начала - " + record.getStartTime() + ", время окончания - " + record.getEndTime());
        else
            logger.info("Неудачаная попытка удалить запись: id доктора - " + record.getIdDoctor() +
                    ", id пользователя - " + record.getIdUser() + ", дата - " + record.getDate() +
                    ", время начала - " + record.getStartTime() + ", время окончания - " + record.getEndTime());

        return res;
    }

    @Override
    public Boolean updateRecord(Record record) {
        Doctor tmpDoctor = null;
        try {
            tmpDoctor = getDoctorRep.getDoctorById(record.getIdDoctor());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (getUserRep.getUserById(record.getIdUser()) == null) {
            logger.info("Неудачаная попытка обновить запись: id записи - " + record.getId() +  ", id доктора - " +
                    record.getIdDoctor() + ", id пользователя - " + record.getIdUser() + ", дата - " +
                    record.getDate() + ", время начала - " + record.getStartTime() + ", время окончания - " +
                    record.getEndTime());
            return false;
        }
        else if (tmpDoctor == null) {
            logger.info("Неудачаная попытка обновить запись: id записи - " + record.getId() +  ", id доктора - " +
                    record.getIdDoctor() + ", id пользователя - " + record.getIdUser() + ", дата - " +
                    record.getDate() + ", время начала - " + record.getStartTime() + ", время окончания - " +
                    record.getEndTime());
            return false;
        }

        ArrayList<Record> recordsUserDoctorByDate = null;
        try {
            recordsUserDoctorByDate = recordRep.getRecordsByUserDoctorDate(record.getIdUser(),
                    record.getIdDoctor(), record.getDate());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (recordsUserDoctorByDate != null) {
            for (int i = 0; i < recordsUserDoctorByDate.size(); i++) {
                if (record.getStartTime().compareTo(recordsUserDoctorByDate.get(i).getStartTime()) <= 0 &&
                        record.getEndTime().compareTo(recordsUserDoctorByDate.get(i).getEndTime()) >= 0 &&
                        recordsUserDoctorByDate.get(i).getId() != record.getId()) {
                    logger.info("Неудачаная попытка обновить запись: id записи - " + record.getId() + ", id доктора - " +
                            record.getIdDoctor() + ", id пользователя - " + record.getIdUser() + ", дата - " +
                            record.getDate() + ", время начала - " + record.getStartTime() + ", время окончания - " +
                            record.getEndTime());
                    return false;
                } else if (record.getStartTime().compareTo(recordsUserDoctorByDate.get(i).getStartTime()) >= 0 &&
                        record.getStartTime().compareTo(recordsUserDoctorByDate.get(i).getEndTime()) <= 0 &&
                        recordsUserDoctorByDate.get(i).getId() != record.getId()) {
                    logger.info("Неудачаная попытка обновить запись: id записи - " + record.getId() + ", id доктора - " +
                            record.getIdDoctor() + ", id пользователя - " + record.getIdUser() + ", дата - " +
                            record.getDate() + ", время начала - " + record.getStartTime() + ", время окончания - " +
                            record.getEndTime());
                    return false;
                } else if (record.getEndTime().compareTo(recordsUserDoctorByDate.get(i).getStartTime()) >= 0 &&
                        record.getEndTime().compareTo(recordsUserDoctorByDate.get(i).getEndTime()) <= 0 &&
                        recordsUserDoctorByDate.get(i).getId() != record.getId()) {
                    logger.info("Неудачаная попытка обновить запись: id записи - " + record.getId() + ", id доктора - " +
                            record.getIdDoctor() + ", id пользователя - " + record.getIdUser() + ", дата - " +
                            record.getDate() + ", время начала - " + record.getStartTime() + ", время окончания - " +
                            record.getEndTime());
                    return false;
                }
            }
        }

        Boolean res = null;
        try {
            res = recordRep.updateRecord(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (res)
            logger.info("Обновлена запись: id записи - " + record.getId() +  ", id доктора - " +
                    record.getIdDoctor() + ", id пользователя - " + record.getIdUser() + ", дата - " +
                    record.getDate() + ", время начала - " + record.getStartTime() + ", время окончания - " +
                    record.getEndTime());
        else
            logger.info("Неудачаная попытка обновить запись: id записи - " + record.getId() +  ", id доктора - " +
                    record.getIdDoctor() + ", id пользователя - " + record.getIdUser() + ", дата - " +
                    record.getDate() + ", время начала - " + record.getStartTime() + ", время окончания - " +
                    record.getEndTime());

        return res;
    }

    @Override
    public ArrayList<Record> getRecordsByDate(int idDoctor, Date date) {
        ArrayList<Record> arrRecords;
        try {
            arrRecords = recordRep.getRecordsByDoctorDate(idDoctor, date);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (arrRecords != null && arrRecords.size() != 0)
            logger.info("Получен список из - " + arrRecords.size() + " записей к доктору с id - " +
                    idDoctor + " на дату - " + date);
        else
            logger.info("Неудачная попытка получить записи к доктору с id - " +
                    idDoctor + " на дату - " + date);

        return arrRecords;
    }
}
