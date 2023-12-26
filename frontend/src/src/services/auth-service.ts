import { Axios } from "axios";
import {AuthenticationParameters} from '../interfaces/interface';

export class AuthService {

    constructor(
        private readonly api: Axios,
    ) {}

    async login(dto: AuthenticationParameters) {
        const { data } = await this.api.post<{ role: boolean }>('/authentications', dto);

        return data;
    }
}