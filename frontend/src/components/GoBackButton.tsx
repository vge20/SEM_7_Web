import { FC } from "react";
import { useNavigate } from "react-router";

export const GoBackButton: FC = () => {
  const navigate = useNavigate();

  return (
    <button onClick={() => navigate(-1)} className="go-back-button">
      {"❮"}
    </button>
  );
};
