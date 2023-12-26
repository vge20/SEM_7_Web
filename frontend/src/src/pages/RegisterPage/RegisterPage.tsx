import { FC } from "react";
import { PatientForm } from "../../forms/PatientForm";
import { Patient } from "../../interfaces/interface";
import { useNavigate } from "react-router";
import { PatientsService } from "../../services/patients-service";
import { axiosInstance } from "../../axios";

export const RegisterPage: FC = () => {
  const navigate = useNavigate();

  const patientsService = new PatientsService(axiosInstance);

  const createPatients = async (patient: Patient) => {
    try {
      await patientsService.createPatient(patient);

      navigate("/patients");
    } catch (err) {}
  };

  return (
    <div className="page flex">
      <h1>Регистрация</h1>
      <PatientForm submit={createPatients} />
    </div>
  );
};
