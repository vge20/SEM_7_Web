import { FC, useState } from "react";

import { Doctor } from "../../interfaces/interface";
import { DoctorCard } from "./components/DoctorCard";
import { DoctorsService } from "../../services/doctors-service";
import { axiosInstance } from "../../axios";
import { useNavigate } from "react-router";
import { useUserData } from "../../hooks/user-data.hook";

export const DoctorsPage: FC = () => {
  const [doctors, setDoctors] = useState<Doctor[]>([]);

  const { isAdmin } = useUserData();

  const navigate = useNavigate();

  const doctorsService = new DoctorsService(axiosInstance);

  const [specialization, setSpecialization] = useState("");

  const getDoctors = async () => {
    try {
      const doctors = await doctorsService.getAllDoctors({
        specialization: specialization,
        limit: 1000,
        skipped: 0,
      });

      setDoctors(doctors ?? []);
    } catch (err) {}
  };

  const editDoctor = (id: number) => {
    navigate(`/edit-doctor/${id}`);
  };

  const deleteDoctor = async (id: number) => {
    const confirmed = window.confirm("Вы уверены?");

    if (!confirmed) {
      return;
    }

    try {
      await doctorsService.deleteDoctor(id);

      getDoctors();
    } catch (err) {}
  };

  return (
    <div className="page flex-top pt-20">
      <div className="flex-horizontal gap-50">
        {isAdmin && (
          <button className="normal" onClick={() => navigate("/create-doctor")}>
            Создать
          </button>
        )}

        <div className="field">
          <h5>Введите специализацию врача:</h5>
          <input
            value={specialization}
            onChange={(e) => setSpecialization(e.target.value)}
          />
        </div>

        <button className="normal" onClick={getDoctors}>
          Найти
        </button>
      </div>

      <div className="flex-wrap cards-table">
        {doctors?.map?.((doctor) => (
          <DoctorCard
            doctor={doctor}
            key={doctor.id}
            onDelete={deleteDoctor}
            onEdit={editDoctor}
          />
        ))}
      </div>
    </div>
  );
};
