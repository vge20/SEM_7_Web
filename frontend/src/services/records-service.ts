import { Axios } from "axios";
import { Record, PostRecord } from "../interfaces/interface";

export class RecordsService {

    constructor(
        private readonly api: Axios,
    ) {}

    async createRecord(dto: PostRecord) {
        const {data} = await this.api.post<void>('/records', dto);

        return data;
    }

    async updateRecord(dto: Record) {
        const {data} = await this.api.patch<void>(`/records/${dto.id}`, dto);

        return data;
    }

    async getRecordById(id: number) {
        const {data} = await this.api.get<Record>(`/records/${id}`);

        return data;
    }

    async getAllRecords(params: {
      patientLogin: string;
      startDate: string;
      endDate: string;
      limit: number;
      skipped: number;
    }) {
        const query = new URLSearchParams({
            patientLogin: params.patientLogin,
            startDate: params.startDate,
            endDate: params.endDate,
            limit: String(params.limit),
            skipped: String(params.skipped),
        });

        const {data} = await this.api.get<Record[] | undefined>(`/records?${query.toString()}`);

        return data;
    }

    async deleteRecord(record: Record) {
        const query = new URLSearchParams({
            doctorId: String(record.doctorId),
            patientLogin: record.patientLogin!,
            date: String(record.date),
            startTime: String(record.startTime),
            endTime: String(record.endTime),
        });

        const {data} = await this.api.delete<void>(`/records?${query.toString()}`);

        return data;
    }


}