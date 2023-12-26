import { FC } from "react";
import { useNavigate } from "react-router";
import { useUserData } from "../../hooks/user-data.hook";

export const MainMenuPage: FC = () => {
  const navigate = useNavigate();

  const { isAdmin } = useUserData();

  return (
    <div className="page flex gap-35">
      <button className="large" onClick={() => navigate("/records")}>
        {isAdmin ? "Записи" : "Мои записи"}
      </button>
      {isAdmin && (
        <button className="large" onClick={() => navigate("/patients")}>
          Пациенты
        </button>
      )}
      <button className="large" onClick={() => navigate("/profile")}>
        Мой аккаунт
      </button>
      <button className="large" onClick={() => navigate("/doctors")}>
        Посмотреть список <br /> врачей
      </button>
      <button className="large" onClick={() => navigate("/login")}>
        Выход
      </button>
    </div>
  );
};
