import { FC, useState } from "react";
import { PatientCard } from "./components/PatientCard";
import { Patient } from "../../interfaces/interface";
import { useNavigate } from "react-router";
import { PatientsService } from "../../services/patients-service";
import { axiosInstance } from "../../axios";
import { useUserData } from "../../hooks/user-data.hook";

export const PatientsPage: FC = () => {
  const [patients, setPatients] = useState<Patient[]>([]);

  const { isAdmin } = useUserData();

  const navigate = useNavigate();

  const patientsService = new PatientsService(axiosInstance);

  const [search, setSearch] = useState("");
  const [gender, setGender] = useState(true);

  const getPatients = async () => {
    try {
      const result = await patientsService.getAllPatients({
        limit: 1000,
        skipped: 0,
        substr: search,
        gender: gender,
      });

      setPatients(result ?? []);
    } catch (err) {}
  };

  const deletePatient = async (login: string) => {
    const confirmed = window.confirm("Вы уверены?");

    if (!confirmed) {
      return;
    }

    try {
      await patientsService.deletePatient(login);

      getPatients();
    } catch (err) {}
  };

  const editPatient = (login: string) => {
    navigate(`/edit-patient/${login}`);
  };

  return (
    <div className="page flex-top pt-20">
      <div className="flex-horizontal">
        <div className="flex-horizontal gap-50">
          {isAdmin && (
            <button
              className="normal"
              onClick={() => navigate("/create-patient")}
            >
              Создать
            </button>
          )}

          <div className="radio-group">
            <div className="flex-horizontal">
              <label htmlFor="male">М</label>
              <input
                type="radio"
                id="male"
                checked={gender}
                onChange={() => setGender(true)}
              />
            </div>

            <div className="flex-horizontal">
              <label htmlFor="female">Ж</label>
              <input
                type="radio"
                id="female"
                checked={!gender}
                onChange={() => setGender(false)}
              />
            </div>
          </div>

          <div className="field">
            <h5>Введите подстроку логина пользователя:</h5>
            <input
              type="text"
              value={search}
              onChange={(e) => setSearch(e.target.value)}
            />
          </div>

          <button className="normal" onClick={getPatients}>
            Найти
          </button>
        </div>
      </div>

      <div className="flex-wrap cards-table large">
        {patients?.map?.((patient) => (
          <PatientCard
            patient={patient}
            key={patient.id}
            onEdit={editPatient}
            onDelete={deletePatient}
          />
        ))}
      </div>
    </div>
  );
};
