package com.baeldung.Record;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Date;
import java.util.ArrayList;

public class RecordRepository implements IRecordRepository {

    @Override
    public Boolean addRecord(Record record) {
        RecordDAModel recordDAModel = new RecordDAModel(record.getIdDoctor(), record.getIdUser(),
                record.getDate(), record.getStartTime(), record.getEndTime());
        try {
            Session session = RecordSessionFactory.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(recordDAModel);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            throw e;
        }

        return true;
    }

    @Override
    public Boolean updateRecord(Record record) {
        RecordDAModel recordDAModel = new RecordDAModel(record.getId(), record.getIdDoctor(), record.getIdUser(),
                record.getDate(), record.getStartTime(), record.getEndTime());
        try {
            Session session = RecordSessionFactory.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.update(recordDAModel);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            throw e;
        }

        return true;
    }

    @Override
    public Boolean deleteRecord(int id) {
        RecordDAModel recordDAModel = new RecordDAModel();
        recordDAModel.setId(id);

        try {
            Session session = RecordSessionFactory.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(recordDAModel);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            throw e;
        }

        return true;
    }

    @Override
    public ArrayList<Record> getRecordsByUserDate(int idUser, Date date) {
        ArrayList<RecordDAModel> arrRecordDAModels;
        try {
            arrRecordDAModels = (ArrayList<RecordDAModel>) RecordSessionFactory.getSessionFactory()
                    .openSession().createQuery("from RecordDAModel where idUser = " + Integer.toString(idUser) +
                            " and date = '" + date.toString() + "'").list();
        } catch (Exception e) {
            throw e;
        }

        ArrayList<Record> arrRecords;
        if (arrRecordDAModels.size() != 0) {
            arrRecords = new ArrayList<>(0);
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
    public ArrayList<Record> getRecordsByDoctorDate(int idDoctor, Date date) {
        ArrayList<RecordDAModel> arrRecordDAModels;
        try {
            arrRecordDAModels = (ArrayList<RecordDAModel>) RecordSessionFactory.getSessionFactory()
                    .openSession().createQuery("from RecordDAModel where idDoctor = " + Integer.toString(idDoctor) +
                            " and date = '" + date.toString() + "'").list();
        } catch (Exception e) {
            throw e;
        }

        ArrayList<Record> arrRecords;
        if (arrRecordDAModels.size() != 0) {
            arrRecords = new ArrayList<>(0);
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
    public ArrayList<Record> getRecordsByUserDoctorDate(int idUser, int idDoctor, Date date) {
        ArrayList<RecordDAModel> arrRecordDAModels;
        try {
            arrRecordDAModels = (ArrayList<RecordDAModel>) RecordSessionFactory.getSessionFactory()
                    .openSession().createQuery("from RecordDAModel where idDoctor = " + Integer.toString(idDoctor) +
                            " and idUser = " + Integer.toString(idUser) + " and date = '" + date.toString() + "'")
                    .list();
        } catch (Exception e) {
            throw e;
        }

        ArrayList<Record> arrRecords;
        if (arrRecordDAModels.size() != 0) {
            arrRecords = new ArrayList<>(0);
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
    public Record getRecordById(int id) {
        ArrayList<RecordDAModel> arrRecordDAModels;
        try {
            arrRecordDAModels = (ArrayList<RecordDAModel>) RecordSessionFactory.getSessionFactory()
                    .openSession().createQuery("from RecordDAModel where id = " + Integer.toString(id)).list();
        } catch (Exception e) {
            throw e;
        }

        Record record;
        if (arrRecordDAModels.size() != 0) {
            record = new Record(arrRecordDAModels.get(0).getId(), arrRecordDAModels.get(0).getIdDoctor(),
                    arrRecordDAModels.get(0).getIdUser(), arrRecordDAModels.get(0).getDate(),
                    arrRecordDAModels.get(0).getStartTime(), arrRecordDAModels.get(0).getEndTime());
        }
        else
            record = null;

        return record;
    }
}

