package bmi.model;

import bmi.model.data.Record;

public class Update {
    
    //variables
    private String msg;
    private Record r;
    
    public Update(String message, Record r){
        this.msg = message;
        this.r = r;
    }// end of method update
    
    public String getMessage(){
        return msg;
    }//end of method getMessage
    
    public Record getRecord(){
        return r;
    }// end of method getRecord
    
}//end of class Update
