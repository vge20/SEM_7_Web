package BL.Doctor;

import BL.Schedule.IScheduleRepository;
import BL.Schedule.Schedule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

        boolean res = doctorRep.addDoctor(doctor);

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
        if (getDoctorRep.getDoctorById(id) == null) {
            logger.info("Неудачная попытка удалить пользователя: идентификатор - " + id);
            return false;
        }

        if (!scheduleRep.deleteSchedule(scheduleRep.getSchedule(id).getId())) {
            logger.info("Неудачная попытка удалить пользователя: идентификатор - " + id);
            return false;
        }

        Boolean res = doctorRep.deleteDoctor(id);

        if (res)
            logger.info("Удалён пользователь: идентификатор - " + id);
        else
            logger.info("Неудачная попытка удалить пользователя: идентификатор - " + id);

        return res;
    }

    @Override
    public Boolean updateDoctor(Doctor doctor) {
        if (getDoctorRep.getDoctorById(doctor.getId()) == null) {
            logger.info("Неудачная попытка обновить пользователя: идентификатор - " + doctor.getId() + ", " +
                    doctor.getFirstName() + ", " + doctor.getLastName() + ", пол - " + doctor.getGender() +
                    ", специализация - " + doctor.getSpecialization());
            return false;
        }

        Boolean res = doctorRep.updateDoctor(doctor);

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
        ArrayList<Doctor> arrDoctors = getDoctorRep.getDoctorsList();

        if (arrDoctors != null && arrDoctors.size() != 0)
            logger.info("Получен список из " + arrDoctors.size() + " докторов");
        else
            logger.info("Неудачная поптыка получить список пользователей");

        return arrDoctors;
    }
}

