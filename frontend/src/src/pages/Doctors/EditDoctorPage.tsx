import { FC, useEffect, useState } from "react";
import { Doctor } from "../../interfaces/interface";
import { useParams, useNavigate } from "react-router";
import { axiosInstance } from "../../axios";
import { DoctorsService } from "../../services/doctors-service";
import { DoctorForm } from "../../forms/DoctorForm";

interface RouteParams {
  doctorId: string;
  [key: string]: string;
}

export const EditDoctorPage: FC = () => {
  const { doctorId } = useParams<RouteParams>();

  const [doctor, setDoctor] = useState<Doctor | undefined>(undefined);

  const navigate = useNavigate();

  const doctorsService = new DoctorsService(axiosInstance);

  const getDoctor = async () => {
    try {
      const id = Number(doctorId);

      const result = await doctorsService.getDoctorById(id);

      setDoctor(result);
    } catch (err) {}
  };

  const updateDoctor = async (doctor: Doctor) => {
    try {
      await doctorsService.updateDoctor(doctor);

      navigate("/doctors");
    } catch (err) {}
  };

  useEffect(() => {
    getDoctor();
  }, []);

  if (!doctor) {
    return (
      <div className="page flex">
        <h1>Загрузка...</h1>
      </div>
    );
  }

  return (
    <div className="page flex">
      <h1>Редактирование врача</h1>
      <DoctorForm submit={updateDoctor} doctor={doctor} />
    </div>
  );
};
