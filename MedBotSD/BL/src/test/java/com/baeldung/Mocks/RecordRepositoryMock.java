package com.baeldung.Mocks;

import com.baeldung.Record.Record;
import com.baeldung.Record.IRecordRepository;

import java.sql.Date;
import java.util.ArrayList;

public class RecordRepositoryMock implements IRecordRepository {
    @Override
    public Boolean addRecord(Record record) {
        return null;
    }

    @Override
    public Boolean updateRecord(Record record) {
        return null;
    }

    @Override
    public Boolean deleteRecord(int id) {
        return null;
    }

    @Override
    public ArrayList<Record> getRecordsByUserDate(int idUser, Date date) {
        return null;
    }

    @Override
    public ArrayList<Record> getRecordsByDoctorDate(int idDoctor, Date date) {
        return null;
    }

    @Override
    public ArrayList<Record> getRecordsByUserDoctorDate(int idUser, int idDoctor, Date date) {
        return null;
    }

    @Override
    public Record getRecordById(int id) {
        return null;
    }
}
