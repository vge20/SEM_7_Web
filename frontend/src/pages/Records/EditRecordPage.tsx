import { FC, useEffect, useState } from "react";
import { Record } from "../../interfaces/interface";
import { useNavigate, useParams } from "react-router";
import { axiosInstance } from "../../axios";
import { RecordsService } from "../../services/records-service";
import { RecordForm } from "../../forms/RecordForm";

interface RouteParams {
  recordId: string;
  doctorName: string;
  doctorLastName: string;
  specialization: string;
  [key: string]: string;
}

export const EditRecordPage: FC = () => {
  const { recordId, doctorLastName, doctorName, specialization } = useParams<
    RouteParams
  >();

  const recordsService = new RecordsService(axiosInstance);

  const [record, setRecords] = useState<Record | undefined>();

  const navigate = useNavigate();

  const getRecord = async () => {
    try {
      const id = Number(recordId);

      const result = await recordsService.getRecordById(id);

      setRecords(result);
    } catch (err) {}
  };

  const submit = async (record: Record) => {
    try {
      await recordsService.updateRecord(record);

      navigate("/records");
    } catch (err) {}
  };

  useEffect(() => {
    if (recordId) {
      getRecord();
    }
  }, [recordId]);

  if (!record) {
    return (
      <div className="page flex">
        <h1>Загрузка...</h1>
      </div>
    );
  }

  return (
    <div className="page flex">
      <h1>Редактирование записи</h1>
      <RecordForm
        record={record}
        submit={submit}
        doctor={{
          firstName: doctorName,
          lastName: doctorLastName,
          specialization: specialization,
        }}
      />
    </div>
  );
};
