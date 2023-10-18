package com.baeldung.Doctor;

import java.util.ArrayList;

public interface IDoctorService {
    public Boolean addDoctor(Doctor doctor);
    public Boolean deleteDoctor(int id);
    public Boolean updateDoctor(Doctor doctor);
    public ArrayList<Doctor> getDoctorsList();
}
