import { FC } from "react";
import { Record } from "../../interfaces/interface";
import { useNavigate } from "react-router";
import { axiosInstance } from "../../axios";
import { RecordsService } from "../../services/records-service";
import { RecordForm } from "../../forms/RecordForm";

export const CreateRecordPage: FC = () => {
  const navigate = useNavigate();

  const recordService = new RecordsService(axiosInstance);

  const createRecord = async (record: Record) => {
    try {
      await recordService.createRecord(record);

      navigate("/records");
    } catch (err) {}
  };

  return (
    <div className="page flex">
      <h1>Создание записи</h1>
      <RecordForm submit={createRecord} />
    </div>
  );
};
