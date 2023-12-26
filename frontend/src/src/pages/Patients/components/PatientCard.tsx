import { FC } from "react";
import { Patient } from "../../../interfaces/interface";
import { genderToString } from "../../../utils/gender";

interface PatientCardProps {
  patient: Patient;

  onEdit?: (id: string) => void;
  onDelete?: (id: string) => void;
}

export const PatientCard: FC<PatientCardProps> = ({
  patient,
  onEdit,
  onDelete,
}) => {
  return (
    <div className="card large card-info">
      <p>Логин: {patient.login}</p>
      <p>Пароль: {patient.password}</p>
      <p>Имя: {patient.firstName}</p>
      <p>Фамилия: {patient.lastName}</p>
      <p>Пол: {genderToString(patient.gender!)}</p>
      <p>Дата рождения: {patient.birthDate}</p>

      <div className="actions flex-between">
        <button onClick={() => onEdit?.(patient.login!)}>Изменить</button>
        <button onClick={() => onDelete?.(patient.login!)}>Удалить</button>
      </div>
    </div>
  );
};
