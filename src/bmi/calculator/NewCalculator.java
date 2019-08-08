package bmi.calculator;

public class NewCalculator extends Calculator {
    
    private double bmi;
    
    @Override
    public double calculate(double w, double h){
        
        double bmi = (1.3*w)/(Math.pow(h,2.5));
        return bmi;
    }//end of method calculate
}//end of class NewCalculator
