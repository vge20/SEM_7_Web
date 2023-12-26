import { FC } from "react";
import { Doctor } from "../../interfaces/interface";
import { useNavigate } from "react-router";
import { axiosInstance } from "../../axios";
import { DoctorsService } from "../../services/doctors-service";
import { DoctorForm } from "../../forms/DoctorForm";

export const CreateDoctorPage: FC = () => {
  const navigate = useNavigate();

  const doctorsService = new DoctorsService(axiosInstance);

  const createDoctor = async (doctor: Doctor) => {
    try {
      const { id, ...dto } = doctor;

      await doctorsService.createDoctor(dto);

      navigate("/doctors");
    } catch (err) {}
  };

  return (
    <div className="page flex">
      <h1>Создание врача</h1>
      <DoctorForm submit={createDoctor} />
    </div>
  );
};
