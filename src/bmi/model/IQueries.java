package bmi.model;

import bmi.model.data.Record;
import java.util.List;
import java.sql.SQLException;

public interface IQueries {
   
    public List<Record> getAllRecords() throws SQLException;
    public List<Record> getRecordsInRange(int lower, int upper) throws SQLException;
}//end of interface IQueries
