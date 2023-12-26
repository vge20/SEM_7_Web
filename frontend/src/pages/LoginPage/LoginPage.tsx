import { FC, useContext, useEffect, useState } from "react";
import { useForm } from "../../hooks/form.hook";
import { Context } from "../../context/context";
import { AuthService } from "../../services/auth-service";
import { axiosInstance } from "../../axios";
import { useNavigate } from "react-router";

export const LoginPage: FC = () => {
  const { setLogin, setRole } = useContext(Context);

  const authService = new AuthService(axiosInstance);

  const navigate = useNavigate();

  const { fields, onSubmit } = useForm({
    initialValue: {
      login: "",
      password: "",
    },
    submit: async (data) => {
      try {
        const result = await authService.login(data);

        setLogin(data.login);
        setRole(result.role);

        navigate("/main");
      } catch (err) {}
    },
  });

  return (
    <form className="page flex gap-50" onSubmit={onSubmit}>
      <div className="field">
        <h5>Введите логин</h5>
        <input {...fields.login} />
      </div>
      <div className="field">
        <h5>Введите пароль</h5>
        <input {...fields.password} />
      </div>
      <button className="large" type="submit">
        Войти
      </button>
    </form>
  );
};
