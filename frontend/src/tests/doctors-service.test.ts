import { DoctorsService } from "../services/doctors-service";

const api = {
    get: jest.fn().mockResolvedValue({ data: [] }),
    post: jest.fn().mockResolvedValue({ data: {} }),
    put: jest.fn().mockResolvedValue({ data: {} }),
    delete: jest.fn().mockResolvedValue({ data: {} }),
}


describe('doctors service', () => {
    let service: DoctorsService;

    beforeEach(() => {
        service = new DoctorsService(api as any);
    });

    it('get doctors', async () => {
        const spy = jest.spyOn(api, 'get').mockResolvedValue({ data: [] });

        await service.getAllDoctors({
            limit: 0,
            skipped: 0,
            specialization: "abc"
        })

        expect(spy).toBeCalledWith("/doctors?specialization=abc&limit=0&skipped=0");
    });

    it('get by id', async () => {
        const spy = jest.spyOn(api, 'get').mockResolvedValue({ data: [] });

        await service.getDoctorById(10)

        expect(spy).toBeCalledWith("/doctors/10");
    });

    it('update', async () => {
        const spy = jest.spyOn(api, 'put').mockResolvedValue({ data: [] });

        await service.updateDoctor({
            firstName: "1",
            gender: false,
            id: 0,
            lastName: "2",
            specialization: "test"
        })

        expect(spy).toBeCalledWith("/doctors/0", {"firstName": "1", "gender": false, "id": 0, "lastName": "2", "specialization": "test"});
    });

    it('delete', async () => {
        const spy = jest.spyOn(api, 'delete').mockResolvedValue({ data: [] });

        await service.deleteDoctor(20)

        expect(spy).toBeCalledWith("/doctors/20");
    });

    it('create', async () => {
        const spy = jest.spyOn(api, 'post').mockResolvedValue({ data: [] });

        await service.createDoctor({
            firstName: "1",
            gender: false,
            lastName: "2",
            specialization: "test"
        })

        expect(spy).toBeCalledWith("/doctors", {"firstName": "1", "gender": false, "lastName": "2", "specialization": "test"});
    });
});
