import { FC, useEffect, useState } from "react";
import { Doctor, Record } from "../../interfaces/interface";
import { useNavigate } from "react-router";
import { axiosInstance } from "../../axios";
import { RecordCard } from "./components/RecordCard";
import { RecordsService } from "../../services/records-service";
import { DoctorsService } from "../../services/doctors-service";
import { useUserData } from "../../hooks/user-data.hook";

export const RecordsPage: FC = () => {
  const [records, setRecords] = useState<Record[]>([]);

  const { isAdmin, login } = useUserData();

  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");
  const [specialization, setSpecialization] = useState("");

  const [loginSearch, setLoginSearch] = useState(() => {
    return isAdmin ? "" : login;
  });

  const [doctors, setDoctors] = useState<Doctor[]>([]);

  const navigate = useNavigate();

  const recordsService = new RecordsService(axiosInstance);
  const doctorsService = new DoctorsService(axiosInstance);

  const getDoctors = async () => {
    try {
      const result = await doctorsService.getAllDoctors({
        limit: 1000,
        skipped: 0,
        specialization: specialization,
      });

      setDoctors(result ?? []);
    } catch (err) {}
  };

  const getRecords = async () => {
    if (!login || !startDate || !endDate) {
      return;
    }

    try {
      await getDoctors();

      const result = await recordsService.getAllRecords({
        limit: 1000,
        skipped: 0,
        patientLogin: loginSearch,
        endDate: endDate,
        startDate: startDate,
      });

      setRecords(result ?? []);
    } catch (err) {}
  };

  const deleteRecord = async (record: Record) => {
    const confirmed = window.confirm("Вы уверены?");

    if (!confirmed) {
      return;
    }

    try {
      await recordsService.deleteRecord(record);

      getRecords();
    } catch (err) {}
  };

  const editRecord = (id: number, doctor?: Doctor) => {
    navigate(
      `/edit-record/${id}/${doctor?.firstName}/${doctor?.lastName}/${specialization}`
    );
  };

  useEffect(() => {
    getDoctors();
  }, []);

  return (
    <div className="page flex-top pt-20">
      <div className="flex-horizontal">
        <div className="flex-horizontal gap-50 records-filters">
          <button className="normal" onClick={() => navigate("/create-record")}>
            Создать запись
          </button>

          <div className="flex-horizontal gap-35">
            <div className="flex gap-35">
              <div className="flex-horizontal">
                <label htmlFor="start">Начало (дата): </label>
                <input
                  type="text"
                  id="start"
                  value={startDate}
                  onChange={(e) => setStartDate(e.target.value)}
                />
              </div>

              <div className="flex-horizontal">
                <label htmlFor="end">Конец (дата):</label>
                <input
                  type="text"
                  id="end"
                  value={endDate}
                  onChange={(e) => setEndDate(e.target.value)}
                />
              </div>
            </div>

            <div className="flex gap-35">
              <div className="flex-horizontal">
                <label htmlFor="end">Специализация*:</label>
                <input
                  type="text"
                  id="end"
                  value={specialization}
                  onChange={(e) => setSpecialization(e.target.value)}
                />
              </div>
            </div>

            {isAdmin && (
              <div className="flex-horizontal">
                <label htmlFor="login">Логин</label>
                <input
                  type="text"
                  id="login"
                  value={loginSearch}
                  onChange={(e) => setLoginSearch(e.target.value)}
                />
              </div>
            )}
          </div>

          <button className="normal" onClick={getRecords}>
            Найти
          </button>
        </div>
      </div>

      <div className="flex-wrap cards-table large">
        {records?.map?.((record) => (
          <RecordCard
            record={record}
            key={record.id}
            onEdit={editRecord}
            onDelete={deleteRecord}
            doctors={doctors}
          />
        ))}
      </div>
    </div>
  );
};
