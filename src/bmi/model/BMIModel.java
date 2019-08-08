package bmi.model;

import bmi.controller.IModel;
import bmi.model.data.Record;
import java.sql.SQLException;
import java.util.List;
import java.util.Iterator;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BMIModel extends Observable implements IModel {
    
    //variables
    private IQueries queries;
    List<Record> result;
    int currentRecordIndex;
    int numberOfRecords;
    int currentRecordNumber;
    Record currentRecord;
    Iterator<Record> iteratorRecord;
    Update update;
    
    public BMIModel(IQueries q){
        
        this.queries = q;
        currentRecordIndex = -1;
        numberOfRecords = 0;
        result = null;
        currentRecord = null;
    }// end of parameterized constructor
    
    private void setModelState(){
        
        numberOfRecords = result.size();
        if(numberOfRecords > 0){
            currentRecordIndex = 0;
            currentRecordNumber = currentRecordIndex + 1;
            currentRecord = result.get(currentRecordIndex);
        }
        else{
            currentRecordIndex = -1;
            currentRecord = null;
        }
        setChanged();
        
        //creating Update Object
        String s = currentRecordNumber + " of " + numberOfRecords;
        update = new Update(s, currentRecord);
        
        notifyObservers(update);
    }// end of method setModelState

    @Override
    public void setAllRecords(){
    
        try {
            result = queries.getAllRecords();
        } catch (SQLException ex) {
            Logger.getLogger(BMIModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        setModelState();
    }//end of method setAllRecords

    @Override
    public void setRecordsInRange(int n1, int n2) {
        
        try {
            result = queries.getRecordsInRange(n1, n2);
        } catch (SQLException ex) {
            Logger.getLogger(BMIModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        setModelState();
    }//end of method setRecordsInRange

    @Override
    public void previous() {
        currentRecordIndex--;
        //wrap around
        if(currentRecordIndex < 0){
            currentRecordIndex = numberOfRecords - 1;
            currentRecordNumber = currentRecordIndex + 1;
        }
        currentRecord = result.get(currentRecordIndex);
        setChanged();
        
        //creating update object
        String s = currentRecordNumber + " of " + numberOfRecords;
        update = new Update(s, currentRecord);
        
        notifyObservers(update);
    }//end of method previous

    @Override
    public void next() {
        
        currentRecordIndex++;
        currentRecordNumber = currentRecordIndex + 1;
        //wrap around
        if(currentRecordIndex >= numberOfRecords){
            currentRecordIndex = 0;
            currentRecordNumber = currentRecordIndex + 1;
        }
        currentRecord = result.get(currentRecordIndex);
        setChanged();
        
        //creating update object
        String s = currentRecordNumber + " of " + numberOfRecords;
        update = new Update(s, currentRecord);
        
        notifyObservers(update);
        
    }//end of method next

    @Override
    public Iterator<Record> iterator() {
        
        try {
            result = queries.getAllRecords(); //to get all the records from database
        } catch (SQLException ex) {
            Logger.getLogger(BMIModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        iteratorRecord = result.iterator();
        
        return iteratorRecord;
    }//end of method iterator
}//end of class BMIModel
