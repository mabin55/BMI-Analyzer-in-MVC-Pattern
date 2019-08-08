package bmi.controller;

import bmi.calculator.Calculator;
import bmi.model.IQueries;
import bmi.model.data.Record;
import java.sql.SQLException;
import java.util.Iterator;

public class BMIController {
    
    //variables 
    IQueries queries;
    IView view;
    Calculator calculator;
    ICommands command;
    IModel model;
    
    Iterator<Record> recordIterator;
    
    public BMIController(ICommands ic, IModel im, Calculator c){
        
        this.command = ic;
        this.model = im;
        this.calculator = c;
    }//end of parameterized constructor
    
    //to bind BMIView object
    public void bind(IView v){
        
        this.view = v;
    }//end of method bind
    
    
    //to perform exit button action
    public void closeConnection() throws SQLException{
        
        command.close();
    }//end of method closeConnection
    
    //to perform all record button action
    public void browseAllRecords(){
        
        boolean flag = true;
        view.browsing(flag);
        model.setAllRecords();
    }//end of browseAllRecords method
    
    //to perform records in range button action
    public void browseRecordsInRange(int lower, int upper){
        
        view.browsing(true);
        model.setRecordsInRange(lower, upper);
    }//end of browseRecordsInRange method
    
    //to perform next button action
    public void nextRecord(){
        
        model.next();
    }//end of nextRecord method
    
    //to perform previous button action
    public void previousRecord(){
        
        model.previous();
    }//end of previousRecord method
    
    //to perform calculate button action
    public void updateAllRecords() throws SQLException{

        recordIterator = model.iterator();
        while(recordIterator.hasNext()){
            Record r = (Record) recordIterator.next();
            r.setBmi10(calculator.calculate(r.getWeight(), r.getHeight()));
            r.setCategory(calculator.categorise(r.getBmi()));
            command.update(r);
            view.display(" All the BMI values \n and category is updated!");
        }//end of while loop
    }//end of updateRecords method
}//end of class BMIController
