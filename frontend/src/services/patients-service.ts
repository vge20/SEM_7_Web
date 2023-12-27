import { Axios } from "axios";
import { Patient, PostPatient } from "../interfaces/interface";

export class PatientsService {

    constructor(
        private readonly api: Axios,
    ) {}

    async createPatient(dto: PostPatient) {
        const {data} = await this.api.post<void>('/patients', dto);

        return data;
    }

    async updatePatient(dto: Patient) {
        const {data} = await this.api.put<void>(`/patients/${dto.login}`, dto);

        return data;
    }

    async updateAdmin(dto: Patient) {
        const {data} = await this.api.put<void>(`/admins/${dto.id}`, dto);

        return data;
    }

    async getPatientByLogin(login: string) {
        const {data} = await this.api.get<Patient>(`/patients/${login}`);

        return data;
    }

    async getAllPatients(args: { gender: boolean, substr: string, limit: number, skipped: number }) {
        const query = new URLSearchParams({
            gender: String(args.gender),
            substr: args.substr,
            limit: String(args.limit),
            skipped: String(args.skipped),
        });

        const {data} = await this.api.get<Patient[] | undefined>(`/patients?${query.toString()}`);

        return data;
    }

    async deletePatient(login: string) {
        const {data} = await this.api.delete<void>(`/patients/${login}`);

        return data;
    }


}