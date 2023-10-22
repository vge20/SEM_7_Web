package BL.Doctor;

public interface IDoctorRepository {
    public Boolean addDoctor(Doctor doctor);
    public Boolean updateDoctor(Doctor doctor);
    public Boolean deleteDoctor(int id);
}
