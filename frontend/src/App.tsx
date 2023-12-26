import { FC, useState } from "react";
import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import { LoginPage } from "./pages/LoginPage/LoginPage";
import { RegisterPage } from "./pages/RegisterPage/RegisterPage";
import { StartPage } from "./pages/StartPage/StartPage";
import { CreatePatientPage } from "./pages/Patients/CreatePatientPage";
import { DoctorsPage } from "./pages/Doctors/DoctorsPage";
import { PatientsPage } from "./pages/Patients/PatientsPage";
import { EditPatientPage } from "./pages/Patients/EditPatientPage";
import { CreateDoctorPage } from "./pages/Doctors/CreateDoctorPage";
import { EditDoctorPage } from "./pages/Doctors/EditDoctorPage";
import { MainMenuPage } from "./pages/MainMenuPage/MainMenuPage";
import { ProfilePage } from "./pages/ProfilePage/ProfilePage";
import { Context } from "./context/context";
import { GoBackButton } from "./components/GoBackButton";
import { RecordsPage } from "./pages/Records/RecordsPage";
import { CreateRecordPage } from "./pages/Records/CreateRecordPage";
import { EditRecordPage } from "./pages/Records/EditRecordPage";

const App: FC = () => {
  const [role, setRole] = useState(false);
  const [login, setLogin] = useState("");

  return (
    <Context.Provider value={{ role, setRole, login, setLogin }}>
      <BrowserRouter>
        <GoBackButton />
        <Routes>
          <Route path="/start" element={<StartPage />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/register" element={<RegisterPage />} />

          <Route path="/main" element={<MainMenuPage />} />
          <Route path="/profile" element={<ProfilePage />} />

          <Route path="/patients" element={<PatientsPage />} />
          <Route path="/create-patient" element={<CreatePatientPage />} />
          <Route
            path="/edit-patient/:patientLogin"
            element={<EditPatientPage />}
          />

          <Route path="/doctors" element={<DoctorsPage />} />
          <Route path="/create-doctor" element={<CreateDoctorPage />} />
          <Route path="/edit-doctor/:doctorId" element={<EditDoctorPage />} />

          <Route path="/records" element={<RecordsPage />} />
          <Route path="/create-record" element={<CreateRecordPage />} />
          <Route
            path="/edit-record/:recordId/:doctorName/:doctorLastName"
            element={<EditRecordPage />}
          />

          <Route path="*" element={<Navigate to="/start" />} />
        </Routes>
      </BrowserRouter>
    </Context.Provider>
  );
};

export default App;
