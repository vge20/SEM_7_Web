import { useContext } from "react";
import { Context } from "../context/context";

export const useUserData = () => {

    const { role, login } = useContext(Context);

    const isAdmin = role;

    return {
        isAdmin,
        login,
    };
};