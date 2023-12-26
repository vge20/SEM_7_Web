import { FC } from "react";
import { Doctor } from "../../../interfaces/interface";
import { useUserData } from "../../../hooks/user-data.hook";
import { genderToString } from "../../../utils/gender";

interface DoctorCardProps {
  doctor: Doctor;

  onEdit?: (id: number) => void;
  onDelete?: (id: number) => void;
}

export const DoctorCard: FC<DoctorCardProps> = ({
  doctor,
  onDelete,
  onEdit,
}) => {
  const { isAdmin } = useUserData();

  return (
    <div className="card normal">
      <p>Имя: {doctor.firstName}</p>
      <p>Фамилия: {doctor.lastName}</p>
      <p>Пол: {genderToString(doctor.gender!)}</p>
      <p>Специализация: {doctor.specialization}</p>

      {isAdmin && (
        <div className="actions flex-between">
          <button onClick={() => onEdit?.(doctor.id!)}>Изменить</button>
          <button onClick={() => onDelete?.(doctor.id!)}>Удалить</button>
        </div>
      )}
    </div>
  );
};
