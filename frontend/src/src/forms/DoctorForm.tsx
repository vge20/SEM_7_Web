import { FC } from "react";
import { useForm } from "../hooks/form.hook";
import { Doctor } from "../interfaces/interface";
import { genderToString, stringToGender } from "../utils/gender";

interface DoctorFormProps {
  doctor?: Doctor;

  submit: (d: Doctor) => void;
}

export const DoctorForm: FC<DoctorFormProps> = ({ doctor, submit }) => {
  const { fields, onSubmit } = useForm({
    initialValue: {
      firstName: doctor?.firstName || "",
      lastName: doctor?.lastName || "",
      gender: doctor?.gender ? genderToString(doctor.gender) : "М",
      specialization: doctor?.specialization || "",
    },
    submit: (result) => {
      submit?.({
        ...result,
        gender: stringToGender(result.gender),
        id: doctor?.id || 0,
      });
    },
  });

  return (
    <form className="form" onSubmit={onSubmit}>
      <div className="form-content">
        <table>
          <tbody>
            <tr>
              <td width={180}>Имя</td>
              <td width={300}>
                <input {...fields.firstName} />
              </td>
            </tr>
            <tr>
              <td>Фамилия</td>
              <td>
                <input {...fields.lastName} />
              </td>
            </tr>
            <tr>
              <td>Пол</td>
              <td>
                <select
                  value={fields.gender.value}
                  onChange={(event) => fields.gender.onChange(event as any)}
                >
                  <option value="М">М</option>
                  <option value="Ж">Ж</option>
                </select>
              </td>
            </tr>
            <tr>
              <td>Специализация</td>
              <td>
                <input {...fields.specialization} />
              </td>
            </tr>
          </tbody>
        </table>
      </div>

      <div className="form-actions">
        <button className="normal" type="submit">
          Сохранить
        </button>
      </div>
    </form>
  );
};
