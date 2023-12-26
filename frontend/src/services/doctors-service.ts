import { Axios } from "axios";
import { Doctor, PostDoctor } from "../interfaces/interface";

export class DoctorsService {

    constructor(
        private readonly api: Axios,
    ) {}

    async createDoctor(dto: PostDoctor) {
        return (await this.api.post<void>('/doctors', dto)).data;
    }

    async updateDoctor(dto: Doctor) {
        return (await this.api.put<void>(`/doctors/${dto.id}`, dto)).data;
    }

    async getDoctorById(id: number) {
        return (await this.api.get<Doctor>(`/doctors/${id}`)).data;
    }

    async getAllDoctors(params:  {
      specialization: string;
      limit: number;
      skipped: number;
    }) {
        const query = new URLSearchParams({
            specialization: params.specialization,
            limit: String(params.limit),
            skipped: String(params.skipped),
        });

        return (await this.api.get<Doctor[]>(`/doctors?${query.toString()}`)).data;
    }

    async deleteDoctor(id: number) {
        return (await this.api.delete<Doctor>(`/doctors/${id}`)).data;
    }


}