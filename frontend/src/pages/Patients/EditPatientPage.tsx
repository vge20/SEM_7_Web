import { FC, useEffect, useState } from "react";
import { PatientForm } from "../../forms/PatientForm";
import { Patient } from "../../interfaces/interface";
import { useNavigate, useParams } from "react-router";
import { PatientsService } from "../../services/patients-service";
import { axiosInstance } from "../../axios";

interface RouteParams {
  patientLogin: string;
  [key: string]: string;
}

export const EditPatientPage: FC = () => {
  const { patientLogin } = useParams<RouteParams>();

  const patientsService = new PatientsService(axiosInstance);

  const [patient, setPatient] = useState<Patient | undefined>();

  const navigate = useNavigate();

  const getPatient = async () => {
    try {
      const result = await patientsService.getPatientByLogin(patientLogin!);

      setPatient(result);
    } catch (err) {}
  };

  const submit = async (patient: Patient) => {
    try {
      await patientsService.updatePatient(patient);

      navigate("/patients");
    } catch (err) {}
  };

  useEffect(() => {
    if (patientLogin) {
      getPatient();
    }
  }, [patientLogin]);

  if (!patient) {
    return (
      <div className="page flex">
        <h1>Загрузка...</h1>
      </div>
    );
  }

  return (
    <div className="page flex">
      <h1>Редактирование пациента</h1>
      <PatientForm patient={patient} submit={submit} />
    </div>
  );
};
