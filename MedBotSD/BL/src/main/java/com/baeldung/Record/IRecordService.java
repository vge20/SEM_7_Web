package com.baeldung.Record;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public interface IRecordService {
    public Boolean addRecord(Record record);
    public Boolean deleteRecord(Record record);
    public Boolean updateRecord(Record record);
    public ArrayList<Record> getRecordsByDate(int idDoctor, Date date);
    public ArrayList<Record> getRecordsByPatientDateInterval(String patientLogin, Date startDate, Date endDate,
                                                             int limit, int skipped);
    public Boolean deleteRecordByParams(int doctorId, int userId, Date date, Time startTime, Time endTime);
}
