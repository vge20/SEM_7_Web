package com.baeldung.Doctor;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class DoctorRepository implements IDoctorRepository, IGetDoctorRepository {
    @Override
    public Boolean addDoctor(Doctor doctor) {
        DoctorDAModel doctorDAModel = new DoctorDAModel(doctor.getFirstName(), doctor.getLastName(),
                doctor.getGender(), doctor.getSpecialization());
        try {
            Session session = DoctorSessionFactory.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(doctorDAModel);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            throw e;
        }

        return true;
    }

    @Override
    public Boolean updateDoctor(Doctor doctor) {
        DoctorDAModel doctorDAModel = new DoctorDAModel(doctor.getId(), doctor.getFirstName(), doctor.getLastName(),
                doctor.getGender(), doctor.getSpecialization());
        try {
            Session session = DoctorSessionFactory.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.update(doctorDAModel);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            throw e;
        }

        return true;
    }

    @Override
    public Boolean deleteDoctor(int id) {
        DoctorDAModel doctorDAModel = new DoctorDAModel();
        doctorDAModel.setId(id);

        try {
            Session session = DoctorSessionFactory.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(doctorDAModel);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            throw e;
        }

        return true;
    }

    @Override
    public ArrayList<Doctor> getDoctorsList() {
        ArrayList<DoctorDAModel> arrDoctorDAModels;
        try {
            arrDoctorDAModels = (ArrayList<DoctorDAModel>) DoctorSessionFactory.getSessionFactory()
                    .openSession().createQuery("from DoctorDAModel").list();
        } catch (Exception e) {
            throw e;
        }

        ArrayList<Doctor> arrDoctors;
        if (arrDoctorDAModels.size() != 0) {
            arrDoctors = new ArrayList<>(0);
            for (int i = 0; i < arrDoctorDAModels.size(); i++) {
                arrDoctors.add(new Doctor(arrDoctorDAModels.get(i).getId(), arrDoctorDAModels.get(i).getFirstName(),
                        arrDoctorDAModels.get(i).getLastName(), arrDoctorDAModels.get(i).getGender(),
                        arrDoctorDAModels.get(i).getSpecialization()));
            }
        }
        else
            arrDoctors = null;

        return arrDoctors;
    }

    @Override
    public Doctor getDoctorById(int id) {
        DoctorDAModel doctorDAModel;

        try {
            doctorDAModel = DoctorSessionFactory.getSessionFactory().openSession().get(DoctorDAModel.class, id);
        } catch (Exception e) {
            throw e;
        }

        Doctor doctor;
        if (doctorDAModel != null)
            doctor = new Doctor(doctorDAModel.getId(), doctorDAModel.getFirstName(),
                    doctorDAModel.getLastName(), doctorDAModel.getGender(), doctorDAModel.getSpecialization());
        else
            doctor = null;

        return doctor;
    }

    @Override
    public Doctor getDoctorByParameters(String firstName, String lastName,
                                        Boolean gender, String specialization) {
        ArrayList<DoctorDAModel> doctorDAModel;

        try {
            doctorDAModel = (ArrayList<DoctorDAModel>) DoctorSessionFactory.getSessionFactory().openSession().
                    createQuery("from DoctorDAModel as m where m.firstName = '" + firstName + "' and m.lastName = '"
                            + lastName + "' and m.gender = " + gender.toString() + " and m.specialization = '" +
                            specialization + "'").list();
        } catch (Exception e) {
            throw e;
        }

        Doctor doctor;
        if (doctorDAModel != null && doctorDAModel.size() != 0)
            doctor = new Doctor(doctorDAModel.get(0).getId(), doctorDAModel.get(0).getFirstName(),
                    doctorDAModel.get(0).getLastName(), doctorDAModel.get(0).getGender(),
                    doctorDAModel.get(0).getSpecialization());
        else
            doctor = null;

        return doctor;
    }
}
