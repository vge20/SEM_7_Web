export interface Doctor {
  id?: number;
  firstName?: string;
  lastName?: string;
  gender?: boolean;
  specialization?: string;
}

export interface PostDoctor {
  firstName?: string;
  lastName?: string;
  gender?: boolean;
  specialization?: string;
}

export interface Record {
  id?: number;
  doctorId?: number;
  patientLogin?: string;
  date?: string;
  startTime?: string;
  endTime?: string;
}

export interface PostRecord {
  doctorId?: number;
  patientLogin?: string;
  date?: string;
  startTime?: string;
  endTime?: string;
}

export interface Patient {
  id?: number;
  login?: string;
  password?: string;
  firstName?: string;
  lastName?: string;
  gender?: boolean;
  birthDate?: string;
}

export interface PostPatient {
  login?: string;
  password?: string;
  firstName?: string;
  lastName?: string;
  gender?: boolean;
  birthDate?: string;
}

export interface Admins {
  id?: number;
  login?: string;
  password?: string;
  firstName?: string;
  lastName?: string;
  gender?: boolean;
  birthDate?: string;
}

export interface AuthenticationParameters {
  login?: string;
  password?: string;
}
