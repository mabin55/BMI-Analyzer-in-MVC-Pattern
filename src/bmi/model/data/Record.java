package bmi.model.data;

public class Record {

    //attributes
    private String subjectID;
    private double height;
    private double weight;
    private int bmi10;
    private String category;

    public Record(){
    }//end of empty constructor

    public Record(String id, double ht, double wt, int bmi10, String ctgy){

	setSubjectID(id);
        setHeight(ht);
	setWeight(wt);
	setBmi10(bmi10);
	setCategory(ctgy);
    }//end of parameterized constructor

    //getter and setter
    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        
        height= Math.floor(height*100)/100;    
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        weight = Math.floor(weight*100)/100;    
        this.weight = weight;
    }

    public double getBmi() {
        double bmi;
        bmi = (double) ((bmi10/10 +(bmi10%10/10)));
        return bmi;
    }

    public void setBmi10(double bmi){
        bmi = Math.floor(bmi*100)/100;
        this.bmi10= (int) ((bmi+0.05)*10.0);      
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
        
    @Override
    public String toString(){
        return String.format("<" + subjectID +", "+height+", "+weight+", "+bmi10+", "+category+">");
    }//end of override method toString
}//end of class Record
