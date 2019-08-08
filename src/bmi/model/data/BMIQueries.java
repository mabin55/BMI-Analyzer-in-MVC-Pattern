package bmi.model.data;

import bmi.controller.ICommands;
import bmi.model.IQueries;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BMIQueries implements IQueries, ICommands{
    
    //for connection database
    public static final String PASSWORD = "bmi";
    public static final String URL = "jdbc:derby://localhost:1527/bmi2";
    public static final String USERNAME = "bmi";
    //manages conntections
    private Connection connection = null;
    //preparedstatements
    private PreparedStatement selectAllRecords = null;
    private PreparedStatement selectRecordsInRange = null;
    private PreparedStatement updateRecord = null;
    
    List<Record> result;

    //BMIQueries constructor
    public BMIQueries() {
        try{
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
           //query that selects all the records
           selectAllRecords = connection.prepareStatement("SELECT * FROM RECORDS");
            //query that selects the records in range
           selectRecordsInRange = connection.prepareStatement("SELECT * FROM RECORDS WHERE BMI10 BETWEEN ? AND ?");
           //query that updates the bmi and category of records
           updateRecord = connection.prepareStatement("UPDATE RECORDS SET BMI10 = ?, BMICATEGORY = ? WHERE SUBJECTID = ?");
        }//end try
        catch(SQLException sqlException){
            sqlException.printStackTrace();
            System.exit(1);
        }//end catch
    }//end BMIQueries constructor
    
    private Record createRecord (ResultSet rs) throws SQLException {
        
        return new Record(
            rs.getString("subjectID"),
            rs.getDouble("height"),
            rs.getDouble("weight"),
            rs.getInt("bmi10"),
            rs.getString("bmiCategory")
	);
    }//end of method createRecord
    
    //methods    
    @Override
    public int update(Record r) throws SQLException{
        
        //getting the Bmi, Category and SubjectID of a record 
        //to calculate and update Bmi and Category.
        updateRecord.setDouble(1, r.getBmi());
        updateRecord.setString(2, r.getCategory());
        updateRecord.setString(3, r.getSubjectID());
                
	return updateRecord.executeUpdate();

    }//end of method update
    
    @Override
    public void close() throws SQLException{
        
        connection.close();
        System.exit(1);
    }//end of method close
    
    @Override
    public List<Record> getAllRecords() throws SQLException{
        
        ResultSet rs;
        
        rs = selectAllRecords.executeQuery();
        result = new ArrayList<>();
        while(rs.next())
            result.add(createRecord(rs));
        
        return result;
    }//end of method getAllRecords
    
    @Override
    public List<Record> getRecordsInRange(int lower, int upper) throws SQLException{
        
        ResultSet rs;
        
        selectRecordsInRange.setInt(1, lower);
        selectRecordsInRange.setInt(2, upper);
        
        rs = selectRecordsInRange.executeQuery();
        result = new ArrayList<>();
        while(rs.next())
            result.add(createRecord(rs));
            
        return result;
    }//end of method getRecordsInRange
}
