package com.baeldung.Record;

import com.baeldung.DataSource.DataSource;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class RecordRepository implements IRecordRepository {

    @Override
    public Boolean addRecord(Record record) throws Exception {
        RecordDAModel recordDAModel = new RecordDAModel(record.getIdDoctor(), record.getIdUser(),
                record.getDate(), record.getStartTime(), record.getEndTime());

        Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("insert into " +
                "Records(idDoctor, idUser, date, startTime, endTime) values " +
                "(?, ?, ?, ?, ?)");
        statement.setInt(1, recordDAModel.getIdDoctor());
        statement.setInt(2, recordDAModel.getIdUser());
        statement.setDate(3, recordDAModel.getDate());
        statement.setTime(4, recordDAModel.getStartTime());
        statement.setTime(5, recordDAModel.getEndTime());

        if (statement.executeUpdate() == 0)
            return false;
        else
            return true;
    }

    @Override
    public Boolean updateRecord(Record record) throws Exception {
        RecordDAModel recordDAModel = new RecordDAModel(record.getId(), record.getIdDoctor(), record.getIdUser(),
                record.getDate(), record.getStartTime(), record.getEndTime());

        Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("update records set " +
                "idDoctor = ?, idUser = ?, date = ?, startTime = ?, endTime = ? where id = ?");
        statement.setInt(1, recordDAModel.getIdDoctor());
        statement.setInt(2, recordDAModel.getIdUser());
        statement.setDate(3, recordDAModel.getDate());
        statement.setTime(4, recordDAModel.getStartTime());
        statement.setTime(5, recordDAModel.getEndTime());
        statement.setInt(6, recordDAModel.getId());

        if (statement.executeUpdate() == 0)
            return false;
        else
            return true;
    }

    @Override
    public Boolean deleteRecord(int id) throws Exception {
        Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("delete from records " +
                "where id = ?");
        statement.setInt(1, id);

        if (statement.executeUpdate() == 0)
            return false;
        else
            return true;
    }

    @Override
    public ArrayList<Record> getRecordsByUserDate(int idUser, Date date) throws Exception {
        ArrayList<RecordDAModel> arrRecordDAModels = new ArrayList<>();

        Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from records " +
                "where idUser = ? and date = ?");
        statement.setInt(1, idUser);
        statement.setDate(2, date);
        ResultSet queryRes = statement.executeQuery();

        while (queryRes.next()) {
            RecordDAModel recordDAModel = new RecordDAModel();
            recordDAModel.setId(queryRes.getInt("id"));
            recordDAModel.setIdUser(queryRes.getInt("idUser"));
            recordDAModel.setIdDoctor(queryRes.getInt("idDoctor"));
            recordDAModel.setDate(queryRes.getDate("date"));
            recordDAModel.setStartTime(queryRes.getTime("startTime"));
            recordDAModel.setEndTime(queryRes.getTime("endTime"));
            arrRecordDAModels.add(recordDAModel);
        }

        ArrayList<Record> arrRecords;
        if (arrRecordDAModels.size() != 0) {
            arrRecords = new ArrayList<>();
            for (int i = 0; i < arrRecordDAModels.size(); i++) {
                arrRecords.add(new Record(arrRecordDAModels.get(i).getId(), arrRecordDAModels.get(i).getIdDoctor(),
                        arrRecordDAModels.get(i).getIdUser(), arrRecordDAModels.get(i).getDate(),
                        arrRecordDAModels.get(i).getStartTime(), arrRecordDAModels.get(i).getEndTime()));
            }
        }
        else
            arrRecords = null;

        return arrRecords;
    }

    @Override
    public ArrayList<Record> getRecordsByDoctorDate(int idDoctor, Date date) throws Exception {
        ArrayList<RecordDAModel> arrRecordDAModels = new ArrayList<>();

        Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from records " +
                "where idDoctor = ? and date = ?");
        statement.setInt(1, idDoctor);
        statement.setDate(2, date);
        ResultSet queryRes = statement.executeQuery();

        while (queryRes.next()) {
            RecordDAModel recordDAModel = new RecordDAModel();
            recordDAModel.setId(queryRes.getInt("id"));
            recordDAModel.setIdUser(queryRes.getInt("idUser"));
            recordDAModel.setIdDoctor(queryRes.getInt("idDoctor"));
            recordDAModel.setDate(queryRes.getDate("date"));
            recordDAModel.setStartTime(queryRes.getTime("startTime"));
            recordDAModel.setEndTime(queryRes.getTime("endTime"));
            arrRecordDAModels.add(recordDAModel);
        }

        ArrayList<Record> arrRecords;
        if (arrRecordDAModels.size() != 0) {
            arrRecords = new ArrayList<>();
            for (int i = 0; i < arrRecordDAModels.size(); i++) {
                arrRecords.add(new Record(arrRecordDAModels.get(i).getId(), arrRecordDAModels.get(i).getIdDoctor(),
                        arrRecordDAModels.get(i).getIdUser(), arrRecordDAModels.get(i).getDate(),
                        arrRecordDAModels.get(i).getStartTime(), arrRecordDAModels.get(i).getEndTime()));
            }
        }
        else
            arrRecords = null;

        return arrRecords;
    }

    @Override
    public ArrayList<Record> getRecordsByUserDoctorDate(int idUser, int idDoctor, Date date) throws Exception {
        ArrayList<RecordDAModel> arrRecordDAModels = new ArrayList<>();

        Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from records " +
                "where idDoctor = ? and idUser = ? and date = ?");
        statement.setInt(1, idDoctor);
        statement.setInt(2, idUser);
        statement.setDate(3, date);
        ResultSet queryRes = statement.executeQuery();

        while (queryRes.next()) {
            RecordDAModel recordDAModel = new RecordDAModel();
            recordDAModel.setId(queryRes.getInt("id"));
            recordDAModel.setIdUser(queryRes.getInt("idUser"));
            recordDAModel.setIdDoctor(queryRes.getInt("idDoctor"));
            recordDAModel.setDate(queryRes.getDate("date"));
            recordDAModel.setStartTime(queryRes.getTime("startTime"));
            recordDAModel.setEndTime(queryRes.getTime("endTime"));
            arrRecordDAModels.add(recordDAModel);
        }

        ArrayList<Record> arrRecords;
        if (arrRecordDAModels.size() != 0) {
            arrRecords = new ArrayList<>();
            for (int i = 0; i < arrRecordDAModels.size(); i++) {
                arrRecords.add(new Record(arrRecordDAModels.get(i).getId(), arrRecordDAModels.get(i).getIdDoctor(),
                        arrRecordDAModels.get(i).getIdUser(), arrRecordDAModels.get(i).getDate(),
                        arrRecordDAModels.get(i).getStartTime(), arrRecordDAModels.get(i).getEndTime()));
            }
        }
        else
            arrRecords = null;

        return arrRecords;
    }

    @Override
    public ArrayList<Record> getRecordsByPatientDateInterval(String patientLogin, Date startDate, Date endDate,
                                                             int limit, int skipped) throws Exception {
        Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from users where login = ?");
        statement.setString(1, patientLogin);
        ResultSet queryRes = statement.executeQuery();

        int userId;
        if (queryRes.next()) {
            userId = queryRes.getInt("id");
        }
        else {
            return null;
        }

        statement = connection.prepareStatement("select * from records where idUser = ? " +
                                                    "and date >= ? and date <= ? limit ? offset ?");
        statement.setInt(1, userId);
        statement.setDate(2, startDate);
        statement.setDate(3, endDate);
        statement.setInt(4, limit);
        statement.setInt(5, skipped);
        queryRes = statement.executeQuery();

        ArrayList<RecordDAModel> arrRecordDAModels = new ArrayList<>();
        while (queryRes.next()) {
            RecordDAModel recordDAModel = new RecordDAModel();
            recordDAModel.setId(queryRes.getInt("id"));
            recordDAModel.setIdUser(queryRes.getInt("idUser"));
            recordDAModel.setIdDoctor(queryRes.getInt("idDoctor"));
            recordDAModel.setDate(queryRes.getDate("date"));
            recordDAModel.setStartTime(queryRes.getTime("startTime"));
            recordDAModel.setEndTime(queryRes.getTime("endTime"));
            arrRecordDAModels.add(recordDAModel);
        }

        ArrayList<Record> arrRecords;
        if (arrRecordDAModels.size() != 0) {
            arrRecords = new ArrayList<>();
            for (int i = 0; i < arrRecordDAModels.size(); i++) {
                arrRecords.add(new Record(arrRecordDAModels.get(i).getId(), arrRecordDAModels.get(i).getIdDoctor(),
                        arrRecordDAModels.get(i).getIdUser(), arrRecordDAModels.get(i).getDate(),
                        arrRecordDAModels.get(i).getStartTime(), arrRecordDAModels.get(i).getEndTime()));
            }
        }
        else
            arrRecords = null;

        return arrRecords;
    }

    @Override
    public Record getRecordById(int id) throws Exception {
        Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from records " +
                "where id = ?");
        statement.setInt(1, id);
        ResultSet queryRes = statement.executeQuery();

        RecordDAModel recordDAModel = null;
        if (queryRes.next()) {
            recordDAModel = new RecordDAModel();
            recordDAModel.setId(queryRes.getInt("id"));
            recordDAModel.setIdUser(queryRes.getInt("idUser"));
            recordDAModel.setIdDoctor(queryRes.getInt("idDoctor"));
            recordDAModel.setDate(queryRes.getDate("date"));
            recordDAModel.setStartTime(queryRes.getTime("startTime"));
            recordDAModel.setEndTime(queryRes.getTime("endTime"));
        }

        Record record;
        if (recordDAModel != null) {
            record = new Record(recordDAModel.getId(), recordDAModel.getIdDoctor(),
                    recordDAModel.getIdUser(), recordDAModel.getDate(),
                    recordDAModel.getStartTime(), recordDAModel.getEndTime());
        }
        else
            record = null;

        return record;
    }
}

