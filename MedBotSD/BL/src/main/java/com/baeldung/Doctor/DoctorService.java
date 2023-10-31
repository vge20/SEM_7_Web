package com.baeldung.Doctor;

import com.baeldung.Schedule.*;
import com.baeldung.Schedule.IScheduleRepository;
import com.baeldung.Schedule.Schedule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.print.Doc;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class DoctorService implements IDoctorService {

    private IGetDoctorRepository getDoctorRep;
    private IDoctorRepository doctorRep;
    private IScheduleRepository scheduleRep;
    private Logger logger;

    public DoctorService(IGetDoctorRepository getDoctorRepository, IDoctorRepository doctorRepository,
                         IScheduleRepository scheduleRepository) throws IOException {
        this.getDoctorRep = getDoctorRepository;
        this.doctorRep = doctorRepository;
        this.scheduleRep = scheduleRepository;
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
    public Boolean addDoctor(Doctor doctor) {
        if (getDoctorRep.getDoctorByParameters(doctor.getFirstName(), doctor.getLastName(),
                doctor.getGender(), doctor.getSpecialization()) != null) {
            logger.info("Неудачная попытка добавить пользователя: " + doctor.getFirstName() + ", " +
                    doctor.getLastName() + ", пол - " + doctor.getGender(), ", специализация - " +
                    doctor.getSpecialization());
            return false;
        }

        boolean res = false;
        try {
            res = doctorRep.addDoctor(doctor);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (res) {
            doctor = getDoctorRep.getDoctorByParameters(doctor.getFirstName(), doctor.getLastName(),
                    doctor.getGender(), doctor.getSpecialization());
            if (doctor == null) {
                logger.info("Неудачная попытка добавить пользователя: " + doctor.getFirstName() + ", " +
                        doctor.getLastName() + ", пол - " + doctor.getGender(), ", специализация - " +
                        doctor.getSpecialization());
                return false;
            }
            Schedule schedule = new Schedule(doctor.getId());
            if (!scheduleRep.addSchedule(schedule)) {
                logger.info("Неудачная попытка добавить пользователя: " + doctor.getFirstName() + ", " +
                        doctor.getLastName() + ", пол - " + doctor.getGender(), ", специализация - " +
                        doctor.getSpecialization());
                return false;
            }
        }

        if (res)
            logger.info("Добавлен пользователь: " + doctor.getFirstName() + ", " + doctor.getLastName() + ", пол - " +
                    doctor.getGender() + ", специализация - " + doctor.getSpecialization());
        else
            logger.info("Неудачная попытка добавить пользователя: " + doctor.getFirstName() + ", " +
                    doctor.getLastName() + ", пол - " + doctor.getGender(), ", специализация - " +
                    doctor.getSpecialization());

        return res;
    }

    @Override
    public Boolean deleteDoctor(int id) {
        Doctor tmpDoctor = null;
        try {
            tmpDoctor = getDoctorRep.getDoctorById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (tmpDoctor == null) {
            logger.info("Неудачная попытка удалить пользователя: идентификатор - " + id);
            return false;
        }

        if (!scheduleRep.deleteSchedule(scheduleRep.getSchedule(id).getId())) {
            logger.info("Неудачная попытка удалить пользователя: идентификатор - " + id);
            return false;
        }

        Boolean res = null;
        try {
            res = doctorRep.deleteDoctor(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (res)
            logger.info("Удалён пользователь: идентификатор - " + id);
        else
            logger.info("Неудачная попытка удалить пользователя: идентификатор - " + id);

        return res;
    }

    @Override
    public Boolean updateDoctor(Doctor doctor) {
        Doctor tmpDoctor = null;
        try {
            tmpDoctor = getDoctorRep.getDoctorById(doctor.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (tmpDoctor == null) {
            logger.info("Неудачная попытка обновить пользователя: идентификатор - " + doctor.getId() + ", " +
                    doctor.getFirstName() + ", " + doctor.getLastName() + ", пол - " + doctor.getGender() +
                    ", специализация - " + doctor.getSpecialization());
            return false;
        }

        Boolean res = null;
        try {
            res = doctorRep.updateDoctor(doctor);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (res)
            logger.info("Обновлён пользователь: идентификатор - " + doctor.getId() + ", " +
                            doctor.getFirstName() + ", " + doctor.getLastName() + ", пол - " + doctor.getGender() +
                    ", специализация - " + doctor.getSpecialization());
        else
            logger.info("Неудачная попытка обновить пользователя: идентификатор - " + doctor.getId() + ", " +
                            doctor.getFirstName() + ", " + doctor.getLastName() + ", пол - " + doctor.getGender() +
                    ", специализация - " + doctor.getSpecialization());

        return res;
    }

    @Override
    public ArrayList<Doctor> getDoctorsList() {
        ArrayList<Doctor> arrDoctors;
        try {
            arrDoctors = getDoctorRep.getDoctorsList();
        } catch (Exception e) {
            arrDoctors = null;
        }

        if (arrDoctors != null && arrDoctors.size() != 0)
            logger.info("Получен список из " + arrDoctors.size() + " докторов");
        else
            logger.info("Неудачная поптыка получить список пользователей");

        return arrDoctors;
    }

    @Override
    public ArrayList<Doctor> getDoctorsList(String specialization, int limit, int skipped) {
        ArrayList<Doctor> arrDoctors;
        try {
            arrDoctors = getDoctorRep.getDoctorsList(specialization, limit, skipped);
        } catch (Exception e) {
            arrDoctors = null;
        }

        if (arrDoctors != null && arrDoctors.size() != 0)
            logger.info("Получен список из " + arrDoctors.size() + " докторов");
        else
            logger.info("Неудачная поптыка получить список докторов");

        return arrDoctors;
    }

    @Override
    public Doctor getDoctorById(int id) {
        Doctor doctor;
        try {
            doctor = getDoctorRep.getDoctorById(id);
        } catch (Exception e) {
            doctor = null;
        }

        if (doctor != null)
            logger.info("Получен доктор");
        else
            logger.info("Неудачная поптыка получить доктора");

        return doctor;
    }
}

