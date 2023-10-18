package com.baeldung.Mocks;

import com.baeldung.Doctor.Doctor;
import com.baeldung.Doctor.IDoctorRepository;
import com.baeldung.Doctor.IGetDoctorRepository;

import java.util.ArrayList;

public class DoctorRepositoryMock implements IGetDoctorRepository, IDoctorRepository {

    public ArrayList<Doctor> getDoctorsList(String specialization, int limit, int skipped) {
        return null;
    }

    @Override
    public Doctor getDoctorById(int id) {
        return null;
    }

    @Override
    public Doctor getDoctorByParameters(String firstName, String lastName, Boolean gender, String specialization) {
        return null;
    }

    @Override
    public Boolean addDoctor(Doctor doctor) {
        return null;
    }

    @Override
    public Boolean updateDoctor(Doctor doctor) {
        return null;
    }

    @Override
    public Boolean deleteDoctor(int id) {
        return null;
    }
}
