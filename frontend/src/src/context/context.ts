import { createContext } from "react";

interface IContext {
    role: boolean;
    setRole: (b: boolean) => void;
    login: string;
    setLogin: (s: string) => void;
}

export const Context = createContext<IContext>({
    role: false,
    setRole: () => {},
    login: "",
    setLogin: () => {},
});