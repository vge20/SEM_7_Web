import { FC } from "react";
import { useNavigate } from "react-router";

export const StartPage: FC = () => {
  const navigate = useNavigate();

  return (
    <div className="page flex gap-35">
      <button className="large" onClick={() => navigate("/login")}>
        Войти
      </button>
      <button className="large" onClick={() => navigate("/register")}>
        Регистрация
      </button>
      <button className="large" onClick={() => navigate("/doctors")}>
        Посмотреть список <br /> врачей
      </button>
    </div>
  );
};
