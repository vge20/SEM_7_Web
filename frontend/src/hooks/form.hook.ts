import React, { useEffect, useState } from "react";

interface Args<T> {
    initialValue: T;
    submit: (values: T) => void;
}

interface InputProps {
    value: string;
    onChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
    className: string;
}

export function useForm<T extends Record<string, string>>({ initialValue, submit }: Args<T>) {

    const [fields, setFields] = useState<Record<keyof T, InputProps>>(() => {
        const result: any = {};

        for (const key of Object.keys(initialValue)) {
            result[key] = {
                className: "",
                value: initialValue[key],
                onChange: (event: React.ChangeEvent<HTMLInputElement>) => {
                    setFields(prev => ({
                        ...prev,
                        [key]: {
                            ...prev[key],
                            value: event.target.value,
                        }
                    }));
                }
            } as InputProps;
        }

        return result;
    });

    const onSubmit = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();

        let isInvalid = false;

        const result: any = {};

        for (const key of Object.keys(initialValue)) {
            result[key] = fields[key].value;

            if (!fields[key].value) {
                setFields(prev => ({
                    ...prev,
                    [key]: {
                        ...prev[key],
                        className: 'invalid'
                    }
                }));
                isInvalid = true;
            } else {
                setFields(prev => ({
                    ...prev,
                    [key]: {
                        ...prev[key],
                        className: ''
                    }
                }));
            }
        }        

        if (isInvalid) {
            return;
        }

        submit(result);
    };

    return {
        fields,
        onSubmit,
    };
}