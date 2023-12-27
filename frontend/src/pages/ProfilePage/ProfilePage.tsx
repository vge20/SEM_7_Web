import { FC, useContext, useEffect, useState } from "react";
import { PatientForm } from "../../forms/PatientForm";
import { Patient } from "../../interfaces/interface";
import { useNavigate } from "react-router";
import { PatientsService } from "../../services/patients-service";
import { axiosInstance } from "../../axios";
import { Context } from "../../context/context";
import { useUserData } from "../../hooks/user-data.hook";

export const ProfilePage: FC = () => {
  const patientsService = new PatientsService(axiosInstance);

  const [patient, setPatient] = useState<Patient | undefined>();

  const { login } = useContext(Context);

  const { isAdmin } = useUserData();

  const navigate = useNavigate();

  const getPatient = async () => {
    try {
      const result = await patientsService.getPatientByLogin(login);

      setPatient(result);
    } catch (err) {}
  };

  const updateProfile = async (patient: Patient) => {
    try {
      if (isAdmin) {
        await patientsService.updateAdmin(patient);
      } else {
        await patientsService.updatePatient(patient);
      }

      navigate("/main");
    } catch (err) {}
  };

  useEffect(() => {
    if (login) {
      getPatient();
    }
  }, [login]);

  if (!patient) {
    return (
      <div className="page flex">
        <h1>Загрузка...</h1>
      </div>
    );
  }

  return (
    <div className="page flex">
      <h1>Профиль</h1>
      <PatientForm patient={patient} submit={updateProfile} />
    </div>
  );
};
