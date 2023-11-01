package com.baeldung.Record;

import java.sql.Date;
import java.util.ArrayList;

public interface IRecordRepository {
    public Boolean addRecord(Record record) throws Exception;
    public Boolean updateRecord(Record record) throws Exception;
    public Boolean deleteRecord(int id) throws Exception;
    public ArrayList<Record> getRecordsByUserDate(int idUser, Date date) throws Exception;
    public ArrayList<Record> getRecordsByDoctorDate(int idDoctor, Date date) throws Exception;
    public ArrayList<Record> getRecordsByUserDoctorDate(int idUser, int idDoctor, Date date) throws Exception;
    public ArrayList<Record> getRecordsByPatientDateInterval(String patientLogin, Date startDate,
                                                             Date endDate, int limit, int skipped) throws Exception;
    public Record getRecordById(int id) throws Exception;
}
