package com.baeldung.Record;

import java.sql.Date;
import java.util.ArrayList;

public interface IRecordRepository {
    public Boolean addRecord(Record record);
    public Boolean updateRecord(Record record);
    public Boolean deleteRecord(int id);
    public ArrayList<Record> getRecordsByUserDate(int idUser, Date date);
    public ArrayList<Record> getRecordsByDoctorDate(int idDoctor, Date date);
    public ArrayList<Record> getRecordsByUserDoctorDate(int idUser, int idDoctor, Date date);
    public Record getRecordById(int id);
}
