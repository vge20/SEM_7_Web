package BL.Doctor;

import java.util.ArrayList;

public interface IGetDoctorRepository {
    public ArrayList<Doctor> getDoctorsList();
    public Doctor getDoctorById(int id);
    public Doctor getDoctorByParameters(String firstName, String lastName, Boolean gender,
                                        String specialization);
}
