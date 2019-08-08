package bmi.controller;

import bmi.model.data.Record;
import java.sql.SQLException;

public interface ICommands {
    
    public int update(Record r) throws SQLException;
    public void close() throws SQLException;
}//end of interface ICommands
