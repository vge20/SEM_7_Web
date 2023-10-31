package com.baeldung.Doctor;

public interface IDoctorRepository {
    public Boolean addDoctor(Doctor doctor) throws Exception;
    public Boolean updateDoctor(Doctor doctor) throws Exception;
    public Boolean deleteDoctor(int id) throws Exception;
}
