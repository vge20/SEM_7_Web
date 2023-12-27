import { FC } from "react";
import { useForm } from "../hooks/form.hook";
import { Patient } from "../interfaces/interface";
import { genderToString, stringToGender } from "../utils/gender";

interface PatientFormProps {
  patient?: Patient;
  submit?: (p: Patient) => void;
}

export const PatientForm: FC<PatientFormProps> = ({ patient, submit }) => {
  const { fields, onSubmit } = useForm({
    initialValue: {
      login: patient?.login || "",
      password: patient?.password || "",
      firstName: patient?.firstName || "",
      lastName: patient?.lastName || "",
      gender: patient?.gender ? genderToString(patient.gender) : "М",
      birthDate: patient?.birthDate || "",
    },
    submit: (result) => {
      submit?.({
        ...result,
        gender: stringToGender(result.gender),
        id: patient?.id || 0,
      });
    },
  });

  return (
    <form className="form" onSubmit={onSubmit}>
      <div className="form-content">
        <table>
          <tbody>
            <tr>
              <td width={180}>Логин</td>
              <td width={300}>
                <input {...fields.login} />
              </td>
            </tr>
            <tr>
              <td>Пароль</td>
              <td>
                <input {...fields.password} type="text" />
              </td>
            </tr>
            <tr>
              <td>Имя</td>
              <td>
                <input {...fields.firstName} type="text" />
              </td>
            </tr>
            <tr>
              <td>Фамилия</td>
              <td>
                <input {...fields.lastName} type="text" />
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
              <td>Дата рождения:</td>
              <td>
                <input {...fields.birthDate} type="text" />
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
