import { FC } from "react";
import { Doctor, Record } from "../../../interfaces/interface";
import { useUserData } from "../../../hooks/user-data.hook";

interface RecordCardProps {
  record: Record;
  doctors: Doctor[];

  onEdit?: (id: number, doctor: Doctor) => void;
  onDelete?: (id: Record) => void;
}

export const RecordCard: FC<RecordCardProps> = ({
  record,
  onDelete,
  onEdit,
  doctors,
}) => {
  const { isAdmin } = useUserData();

  const doctor = doctors?.find?.((d) => d.id === record.doctorId);

  if (!doctor) {
    return null;
  }

  return (
    <div className="card large">
      <p>Имя врача: {doctor?.firstName}</p>
      <p>Фамилия врача: {doctor?.lastName}</p>
      <p>Дата: {record.date}</p>
      <p>Начало: {record.startTime}</p>
      <p>Конец: {record.endTime}</p>

      {isAdmin && (
        <div className="actions flex-between">
          <button onClick={() => onEdit?.(record.id!, doctor!)}>
            Изменить
          </button>
          <button onClick={() => onDelete?.(record)}>Удалить</button>
        </div>
      )}
    </div>
  );
};
