package bmi.controller;

import bmi.model.data.Record;
import java.util.Iterator;

public interface IModel {
    
    public void setAllRecords();
    public void setRecordsInRange(int n1, int n2);
    public void previous();
    public void next();
    public Iterator<Record> iterator();
}//end of interface IModel
