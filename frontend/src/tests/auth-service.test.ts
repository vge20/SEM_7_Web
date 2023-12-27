import { AuthService } from "../services/auth-service";

const api = {
    get: jest.fn(),
    post: jest.fn(),
    put: jest.fn(),
    delete: jest.fn(),
}


describe('auth service', () => {
    let service: AuthService;

    beforeEach(() => {
        service = new AuthService(api as any);
    });

    it('get all', async () => {
        const spy = jest.spyOn(api, 'post').mockResolvedValue({ data: [] });

        await service.login({
            login: "login",
            password: "pass",
        })

        expect(spy).toBeCalledWith("/authentications", {
            login: "login",
            password: "pass",
        });
    });

});
