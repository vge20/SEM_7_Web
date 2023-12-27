import { PatientsService } from "../services/patients-service";

const api = {
    get: jest.fn(),
    post: jest.fn(),
    put: jest.fn(),
    delete: jest.fn(),
}


describe('patients service', () => {
    let service: PatientsService;

    beforeEach(() => {
        service = new PatientsService(api as any);
    });

    it('get all', async () => {
        const spy = jest.spyOn(api, 'get').mockResolvedValue({ data: [] });

        await service.getAllPatients({
            limit: 0,
            skipped: 0,
            gender: false,
            substr: "valid"
        })

        expect(spy).toBeCalledWith("/patients?gender=false&substr=valid&limit=0&skipped=0");
    });

    it('get by id', async () => {
        const spy = jest.spyOn(api, 'get').mockResolvedValue({ data: [] });

        await service.getPatientByLogin("logon")

        expect(spy).toBeCalledWith("/patients/logon");
    });

    it('update', async () => {
        const spy = jest.spyOn(api, 'put').mockResolvedValue({ data: [] });

        await service.updatePatient({
            firstName: "1",
            gender: false,
            id: 0,
            lastName: "2",
            login: "test"
        })

        expect(spy).toBeCalledWith("/patients/test", {"firstName": "1", "gender": false, "id": 0, "lastName": "2", "login": "test"});
    });

    it('delete', async () => {
        const spy = jest.spyOn(api, 'delete').mockResolvedValue({ data: [] });

        await service.deletePatient("log")

        expect(spy).toBeCalledWith("/patients/log");
    });

    it('create', async () => {
        const spy = jest.spyOn(api, 'post').mockResolvedValue({ data: [] });

        await service.createPatient({
            firstName: "1",
            gender: false,
            lastName: "2",
            birthDate: "12c",
            login: "test",
            password: "abc"
        })

        expect(spy).toBeCalledWith("/patients", {
            firstName: "1",
            gender: false,
            lastName: "2",
            birthDate: "12c",
            login: "test",
            password: "abc"
        });
    });
});
