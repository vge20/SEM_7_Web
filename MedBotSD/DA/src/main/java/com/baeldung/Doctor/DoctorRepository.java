package com.baeldung.Doctor;

import com.baeldung.DataSource.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DoctorRepository implements IDoctorRepository, IGetDoctorRepository {
    @Override
    public Boolean addDoctor(Doctor doctor) throws Exception {
        DoctorDAModel doctorDAModel = new DoctorDAModel(doctor.getFirstName(), doctor.getLastName(),
                doctor.getGender(), doctor.getSpecialization());

        Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("insert into " +
                "Doctors(firstName, lastName, gender, specialization) values " +
                "(?, ?, ?, ?)");
        statement.setString(1, doctorDAModel.getFirstName());
        statement.setString(2, doctorDAModel.getLastName());
        statement.setBoolean(3, doctorDAModel.getGender());
        statement.setString(4, doctorDAModel.getSpecialization());

        if (statement.executeUpdate() == 0)
            return false;
        else
            return true;
    }

    @Override
    public Boolean updateDoctor(Doctor doctor) throws Exception {
        DoctorDAModel doctorDAModel = new DoctorDAModel(doctor.getFirstName(), doctor.getLastName(),
                doctor.getGender(), doctor.getSpecialization());

        Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("update doctors " +
                "set firstName = ?, lastName = ?, gender = ?, specialization = ? " +
                "where id = ?");
        statement.setString(1, doctorDAModel.getFirstName());
        statement.setString(2, doctorDAModel.getLastName());
        statement.setBoolean(3, doctorDAModel.getGender());
        statement.setString(4, doctorDAModel.getSpecialization());
        statement.setInt(5, doctorDAModel.getId());

        if (statement.executeUpdate() == 0)
            return false;
        else
            return true;
    }

    @Override
    public Boolean deleteDoctor(int id) throws Exception {
        Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("delete from doctors " +
                "where id = ?");
        statement.setInt(1, id);

        if (statement.executeUpdate() == 0)
            return false;
        else
            return true;
    }

    @Override
    public ArrayList<Doctor> getDoctorsList() throws Exception {
        ArrayList<DoctorDAModel> arrDoctorDAModels = new ArrayList<>();

        Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from doctors");
        ResultSet queryRes = statement.executeQuery();

        while (queryRes.next()) {
            DoctorDAModel doctorDAModel = new DoctorDAModel();
            doctorDAModel.setId(queryRes.getInt("id"));
            doctorDAModel.setFirstName(queryRes.getString("firstName"));
            doctorDAModel.setLastName(queryRes.getString("lastName"));
            doctorDAModel.setGender(queryRes.getBoolean("gender"));
            doctorDAModel.setSpecialization(queryRes.getString("specialization"));
            arrDoctorDAModels.add(doctorDAModel);
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
    public ArrayList<Doctor> getDoctorsList(String specialization, int limit, int skipped) throws Exception {
        ArrayList<DoctorDAModel> arrDoctorDAModels = new ArrayList<>();

        Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from doctors " +
                "where specialization = ? limit ? offset ?");
        statement.setString(1, specialization);
        statement.setInt(2, limit);
        statement.setInt(3, skipped);
        ResultSet queryRes = statement.executeQuery();

        while (queryRes.next()) {
            DoctorDAModel doctorDAModel = new DoctorDAModel();
            doctorDAModel.setId(queryRes.getInt("id"));
            doctorDAModel.setFirstName(queryRes.getString("firstName"));
            doctorDAModel.setLastName(queryRes.getString("lastName"));
            doctorDAModel.setGender(queryRes.getBoolean("gender"));
            doctorDAModel.setSpecialization(queryRes.getString("specialization"));
            arrDoctorDAModels.add(doctorDAModel);
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
    public Doctor getDoctorById(int id) throws Exception {
        Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from doctors " +
                "where id = ?");
        statement.setInt(1, id);
        ResultSet queryRes = statement.executeQuery();

        DoctorDAModel doctorDAModel = null;
        if (queryRes.next()) {
            doctorDAModel = new DoctorDAModel();
            doctorDAModel.setId(queryRes.getInt("id"));
            doctorDAModel.setFirstName(queryRes.getString("firstName"));
            doctorDAModel.setLastName(queryRes.getString("lastName"));
            doctorDAModel.setGender(queryRes.getBoolean("gender"));
            doctorDAModel.setSpecialization(queryRes.getString("specialization"));
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
                                        Boolean gender, String specialization) throws Exception {
        Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from doctors " +
                "where firstName = ? and lastName = ? and gender = ? and specialization = ?");
        statement.setString(1, firstName);
        statement.setString(2, lastName);
        statement.setBoolean(3, gender);
        statement.setString(4, specialization);
        ResultSet queryRes = statement.executeQuery();

        DoctorDAModel doctorDAModel = null;
        if (queryRes.next()) {
            doctorDAModel = new DoctorDAModel();
            doctorDAModel.setId(queryRes.getInt("id"));
            doctorDAModel.setFirstName(queryRes.getString("firstName"));
            doctorDAModel.setLastName(queryRes.getString("lastName"));
            doctorDAModel.setGender(queryRes.getBoolean("gender"));
            doctorDAModel.setSpecialization(queryRes.getString("specialization"));
        }

        Doctor doctor;
        if (doctorDAModel != null)
            doctor = new Doctor(doctorDAModel.getId(), doctorDAModel.getFirstName(),
                    doctorDAModel.getLastName(), doctorDAModel.getGender(),
                    doctorDAModel.getSpecialization());
        else
            doctor = null;

        return doctor;
    }
}
