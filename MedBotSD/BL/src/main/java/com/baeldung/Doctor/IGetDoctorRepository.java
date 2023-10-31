package com.baeldung.Doctor;

import java.util.ArrayList;

public interface IGetDoctorRepository {
    public ArrayList<Doctor> getDoctorsList() throws Exception;
    public ArrayList<Doctor> getDoctorsList(String specialization, int limit, int skipped) throws Exception;
    public Doctor getDoctorById(int id) throws Exception;
    public Doctor getDoctorByParameters(String firstName, String lastName, Boolean gender,
                                        String specialization) throws Exception;
}
