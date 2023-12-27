import { RecordsService } from "../services/records-service";

const api = {
    get: jest.fn(),
    post: jest.fn(),
    patch: jest.fn(),
    delete: jest.fn(),
}


describe('records service', () => {
    let service: RecordsService;

    beforeEach(() => {
        service = new RecordsService(api as any);
    });

    it('get all', async () => {
        const spy = jest.spyOn(api, 'get').mockResolvedValue({ data: [] });

        await service.getAllRecords({
            limit: 0,
            skipped: 0,
            endDate: "1",
            patientLogin: "c",
            startDate: "2"
        })

        expect(spy).toBeCalledWith("/records?patientLogin=c&startDate=2&endDate=1&limit=0&skipped=0");
    });

    it('get by id', async () => {
        const spy = jest.spyOn(api, 'get').mockResolvedValue({ data: [] });

        await service.getRecordById(12)

        expect(spy).toBeCalledWith("/records/12");
    });

    it('update', async () => {
        const spy = jest.spyOn(api, 'patch').mockResolvedValue({ data: [] });

        await service.updateRecord({
            date: "1",
            doctorId: 2,
            endTime: '3',
            id: 0,
            patientLogin: "123",
            startTime: "31"
        })

        expect(spy).toBeCalledWith("/records/0", {
            date: "1",
            doctorId: 2,
            endTime: '3',
            id: 0,
            patientLogin: "123",
            startTime: "31"
        });
    });

    it('delete', async () => {
        const spy = jest.spyOn(api, 'delete').mockResolvedValue({ data: [] });

        await service.deleteRecord({
            date: "1",
            doctorId: 2,
            endTime: '3',
            id: 0,
            patientLogin: "123",
            startTime: "31"
        })

        expect(spy).toBeCalledWith("/records?doctorId=2&patientLogin=123&date=1&startTime=31&endTime=3");
    });

    it('create', async () => {
        const spy = jest.spyOn(api, 'post').mockResolvedValue({ data: [] });

        await service.createRecord({
            date: "1",
            doctorId: 2,
            endTime: '3',
            patientLogin: "123",
            startTime: "31"
        })

        expect(spy).toBeCalledWith("/records", {
            date: "1",
            doctorId: 2,
            endTime: '3',
            patientLogin: "123",
            startTime: "31"
        });
    });
});
