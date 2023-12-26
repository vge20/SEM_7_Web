/** Generate by swagger-axios-codegen */
// @ts-nocheck
/* eslint-disable */

/** Generate by swagger-axios-codegen */
/* eslint-disable */
// @ts-nocheck
import axiosStatic, { AxiosInstance, AxiosRequestConfig } from 'axios';

export interface IRequestOptions extends AxiosRequestConfig {
  /** only in axios interceptor config*/
  loading?: boolean;
  showError?: boolean;
}

export interface IRequestConfig {
  method?: any;
  headers?: any;
  url?: any;
  data?: any;
  params?: any;
}

// Add options interface
export interface Service1Options {
  axios?: AxiosInstance;
  /** only in axios interceptor config*/
  loading: boolean;
  showError: boolean;
}

// Add default options
export const serviceOptions: Service1Options = {};

// Instance selector
export function axios(configs: IRequestConfig, resolve: (p: any) => void, reject: (p: any) => void): Promise<any> {
  if (serviceOptions.axios) {
    return serviceOptions.axios
      .request(configs)
      .then(res => {
        resolve(res.data);
      })
      .catch(err => {
        reject(err);
      });
  } else {
    throw new Error('please inject yourself instance like axios  ');
  }
}

export function getConfigs(method: string, contentType: string, url: string, options: any): IRequestConfig {
  const configs: IRequestConfig = {
    loading: serviceOptions.loading,
    showError: serviceOptions.showError,
    ...options,
    method,
    url
  };
  configs.headers = {
    ...options.headers,
    'Content-Type': contentType
  };
  return configs;
}

export const basePath = '';

export interface IList<T> extends Array<T> {}
export interface List<T> extends Array<T> {}
export interface IDictionary<TValue> {
  [key: string]: TValue;
}
export interface Dictionary<TValue> extends IDictionary<TValue> {}

export interface IListResult<T> {
  items?: T[];
}

export class ListResultDto<T> implements IListResult<T> {
  items?: T[];
}

export interface IPagedResult<T> extends IListResult<T> {
  totalCount?: number;
  items?: T[];
}

export class PagedResultDto<T = any> implements IPagedResult<T> {
  totalCount?: number;
  items?: T[];
}

// customer definition
// empty

export class DoctorsService1 {
  /**
   * Добавить нового врача
   */
  addDoctor(
    params: {
      /** requestBody */
      body?: PostDoctors;
    } = {} as any,
    options: IRequestOptions = {}
  ): Promise<any> {
    return new Promise((resolve, reject) => {
      let url = basePath + '/doctors';

      const configs: IRequestConfig = getConfigs('post', 'application/json', url, options);

      let data = params.body;

      configs.data = data;

      axios(configs, resolve, reject);
    });
  }
  /**
   * Получить список врачей
   */
  getDoctorsList(
    params: {
      /** Специализация врачей */
      specialization: string;
      /** Лимит количества врачей для возврата */
      limit: number;
      /** Сдвиг возврата в коллекции врачей */
      skipped: number;
    } = {} as any,
    options: IRequestOptions = {}
  ): Promise<Doctors[]> {
    return new Promise((resolve, reject) => {
      let url = basePath + '/doctors';

      const configs: IRequestConfig = getConfigs('get', 'application/json', url, options);
      configs.params = { specialization: params['specialization'], limit: params['limit'], skipped: params['skipped'] };

      /** 适配ios13，get请求不允许带body */

      axios(configs, resolve, reject);
    });
  }
  /**
   * Обновить существующего врача
   */
  updateDoctor(
    params: {
      /** Id врача */
      id: number;
      /** requestBody */
      body?: Doctors;
    } = {} as any,
    options: IRequestOptions = {}
  ): Promise<any> {
    return new Promise((resolve, reject) => {
      let url = basePath + '/doctors/{id}';
      url = url.replace('{id}', params['id'] + '');

      const configs: IRequestConfig = getConfigs('put', 'application/json', url, options);

      let data = params.body;

      configs.data = data;

      axios(configs, resolve, reject);
    });
  }
  /**
   * Удалить существующего врача
   */
  deleteDoctor(
    params: {
      /** id удаляемого врача */
      id: number;
    } = {} as any,
    options: IRequestOptions = {}
  ): Promise<any> {
    return new Promise((resolve, reject) => {
      let url = basePath + '/doctors/{id}';
      url = url.replace('{id}', params['id'] + '');

      const configs: IRequestConfig = getConfigs('delete', 'application/json', url, options);

      let data = null;

      configs.data = data;

      axios(configs, resolve, reject);
    });
  }
  /**
   * Получить врача
   */
  getDoctor(
    params: {
      /** id врача */
      id: number;
    } = {} as any,
    options: IRequestOptions = {}
  ): Promise<Doctors> {
    return new Promise((resolve, reject) => {
      let url = basePath + '/doctors/{id}';
      url = url.replace('{id}', params['id'] + '');

      const configs: IRequestConfig = getConfigs('get', 'application/json', url, options);

      /** 适配ios13，get请求不允许带body */

      axios(configs, resolve, reject);
    });
  }
}

export class RecordsService1 {
  /**
   * Удалить запись на приём
   */
  deleteRecord(
    params: {
      /** id врача удаляемой записи */
      doctorId: number;
      /** Логин пользователя удаляемой записи */
      patientLogin: string;
      /** Дата удаляемой записи */
      date: string;
      /** Время начала удаляемой записи */
      startTime: string;
      /** Время конца удаляемой записи */
      endTime: string;
    } = {} as any,
    options: IRequestOptions = {}
  ): Promise<any> {
    return new Promise((resolve, reject) => {
      let url = basePath + '/records';

      const configs: IRequestConfig = getConfigs('delete', 'application/json', url, options);
      configs.params = {
        doctorId: params['doctorId'],
        patientLogin: params['patientLogin'],
        date: params['date'],
        startTime: params['startTime'],
        endTime: params['endTime']
      };

      let data = null;

      configs.data = data;

      axios(configs, resolve, reject);
    });
  }
  /**
   * Добавить запись на приём
   */
  addRecord(
    params: {
      /** requestBody */
      body?: PostRecords;
    } = {} as any,
    options: IRequestOptions = {}
  ): Promise<any> {
    return new Promise((resolve, reject) => {
      let url = basePath + '/records';

      const configs: IRequestConfig = getConfigs('post', 'application/json', url, options);

      let data = params.body;

      configs.data = data;

      axios(configs, resolve, reject);
    });
  }
  /**
   * Получить список записей
   */
  getRecordsList(
    params: {
      /** Логин пациента */
      patientLogin: string;
      /** Дата начала */
      startDate: string;
      /** Дата конца */
      endDate: string;
      /** Лимит количества записей для возврата */
      limit: number;
      /** Сдвиг возврата в коллекции записей */
      skipped: number;
    } = {} as any,
    options: IRequestOptions = {}
  ): Promise<Records[]> {
    return new Promise((resolve, reject) => {
      let url = basePath + '/records';

      const configs: IRequestConfig = getConfigs('get', 'application/json', url, options);
      configs.params = {
        patientLogin: params['patientLogin'],
        startDate: params['startDate'],
        endDate: params['endDate'],
        limit: params['limit'],
        skipped: params['skipped']
      };

      /** 适配ios13，get请求不允许带body */

      axios(configs, resolve, reject);
    });
  }
  /**
   * Обновить запись на приём
   */
  updateRecord(
    params: {
      /** Id записи */
      id: number;
      /** requestBody */
      body?: Records;
    } = {} as any,
    options: IRequestOptions = {}
  ): Promise<any> {
    return new Promise((resolve, reject) => {
      let url = basePath + '/records/{id}';
      url = url.replace('{id}', params['id'] + '');

      const configs: IRequestConfig = getConfigs('patch', 'application/json', url, options);

      let data = params.body;

      configs.data = data;

      axios(configs, resolve, reject);
    });
  }
}

export class PatientsService1 {
  /**
   * Получить пациента по логину
   */
  getPatientByLogin(
    params: {
      /** Логин пациента */
      login: string;
    } = {} as any,
    options: IRequestOptions = {}
  ): Promise<Patients> {
    return new Promise((resolve, reject) => {
      let url = basePath + '/patients/{login}';
      url = url.replace('{login}', params['login'] + '');

      const configs: IRequestConfig = getConfigs('get', 'application/json', url, options);

      /** 适配ios13，get请求不允许带body */

      axios(configs, resolve, reject);
    });
  }
  /**
   * Удалить пациента по логину
   */
  deletePatientByLogin(
    params: {
      /** Логин пациента */
      login: string;
    } = {} as any,
    options: IRequestOptions = {}
  ): Promise<any> {
    return new Promise((resolve, reject) => {
      let url = basePath + '/patients/{login}';
      url = url.replace('{login}', params['login'] + '');

      const configs: IRequestConfig = getConfigs('delete', 'application/json', url, options);

      let data = null;

      configs.data = data;

      axios(configs, resolve, reject);
    });
  }
  /**
   * Обновить пациента
   */
  updatePatient(
    params: {
      /** Логин пациента */
      login: string;
      /** requestBody */
      body?: Patients;
    } = {} as any,
    options: IRequestOptions = {}
  ): Promise<any> {
    return new Promise((resolve, reject) => {
      let url = basePath + '/patients/{login}';
      url = url.replace('{login}', params['login'] + '');

      const configs: IRequestConfig = getConfigs('put', 'application/json', url, options);

      let data = params.body;

      configs.data = data;

      axios(configs, resolve, reject);
    });
  }
  /**
   * Добавить пациента
   */
  addPatient(
    params: {
      /** requestBody */
      body?: PostPatients;
    } = {} as any,
    options: IRequestOptions = {}
  ): Promise<any> {
    return new Promise((resolve, reject) => {
      let url = basePath + '/patients';

      const configs: IRequestConfig = getConfigs('post', 'application/json', url, options);

      let data = params.body;

      configs.data = data;

      axios(configs, resolve, reject);
    });
  }
  /**
   * Получить список пациентов
   */
  getPatientsList(
    params: {
      /** Пол пациента */
      gender: boolean;
      /** Подстрока в логине пациента */
      substr: string;
      /** Лимит количества пациентов для возврата */
      limit: number;
      /** Сдвиг возврата в коллекции пациентов */
      skipped: number;
    } = {} as any,
    options: IRequestOptions = {}
  ): Promise<Patients[]> {
    return new Promise((resolve, reject) => {
      let url = basePath + '/patients';

      const configs: IRequestConfig = getConfigs('get', 'application/json', url, options);
      configs.params = {
        gender: params['gender'],
        substr: params['substr'],
        limit: params['limit'],
        skipped: params['skipped']
      };

      /** 适配ios13，get请求不允许带body */

      axios(configs, resolve, reject);
    });
  }
}

export class AdminsService1 {
  /**
   * Обновить администратора
   */
  updateAdmin(
    params: {
      /** Id администратора */
      id: number;
      /** requestBody */
      body?: Admins;
    } = {} as any,
    options: IRequestOptions = {}
  ): Promise<any> {
    return new Promise((resolve, reject) => {
      let url = basePath + '/admins/{id}';
      url = url.replace('{id}', params['id'] + '');

      const configs: IRequestConfig = getConfigs('put', 'application/json', url, options);

      let data = params.body;

      configs.data = data;

      axios(configs, resolve, reject);
    });
  }
}

export class AuthenticationsService1 {
  /**
   * Аутентификация
   */
  authentification(
    params: {
      /** requestBody */
      body?: AuthenticationParameters;
    } = {} as any,
    options: IRequestOptions = {}
  ): Promise<any> {
    return new Promise((resolve, reject) => {
      let url = basePath + '/authentications';

      const configs: IRequestConfig = getConfigs('post', 'application/json', url, options);

      let data = params.body;

      configs.data = data;

      axios(configs, resolve, reject);
    });
  }
}

