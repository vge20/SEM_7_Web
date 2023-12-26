import { FC } from "react";
import { useForm } from "../hooks/form.hook";
import { Doctor, Record } from "../interfaces/interface";
import { axiosInstance } from "../axios";
import { DoctorsService } from "../services/doctors-service";

interface RecordFormProps {
  record?: Record;
  doctor?: Doctor;
  submit: (r: Record) => void;
}

export const RecordForm: FC<RecordFormProps> = ({ record, submit, doctor }) => {
  const doctorsService = new DoctorsService(axiosInstance);

  const { fields, onSubmit } = useForm({
    initialValue: {
      doctorName: doctor?.firstName || "",
      doctorLastName: doctor?.lastName || "",
      login: record?.patientLogin || "",
      date: record?.date || "",
      start: record?.startTime || "",
      end: record?.endTime || "",
      specialization: "",
    },
    submit: async (data) => {
      try {
        const doctors = await doctorsService.getAllDoctors({
          limit: 1000,
          skipped: 0,
          specialization: data.specialization,
        });

        const doctor = doctors.find(
          (d) =>
            d.firstName === data.doctorName &&
            d.lastName === data.doctorLastName
        );

        if (!doctor) {
          alert("Врач не найден");
          return;
        }

        submit({
          date: data.date,
          doctorId: doctor.id,
          endTime: data.end,
          patientLogin: data.login,
          startTime: data.start,
          id: record?.id ?? undefined,
        });
      } catch (err) {}
    },
  });

  return (
    <form className="form" onSubmit={onSubmit}>
      <div className="form-content">
        <table>
          <tbody>
            <tr>
              <td width={180}>Имя врача</td>
              <td width={300}>
                <input {...fields.doctorName} />
              </td>
            </tr>
            <tr>
              <td>Фамилия врача</td>
              <td>
                <input {...fields.doctorLastName} type="text" />
              </td>
            </tr>
            <tr>
              <td>Специализация</td>
              <td>
                <input {...fields.specialization} type="text" />
              </td>
            </tr>
            <tr>
              <td>Логин</td>
              <td>
                <input {...fields.login} type="text" />
              </td>
            </tr>
            <tr>
              <td>Дата</td>
              <td>
                <input {...fields.date} type="text" />
              </td>
            </tr>
            <tr>
              <td>Начало</td>
              <td>
                <input {...fields.start} type="text" />
              </td>
            </tr>
            <tr>
              <td>Конец</td>
              <td>
                <input {...fields.end} type="text" />
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
