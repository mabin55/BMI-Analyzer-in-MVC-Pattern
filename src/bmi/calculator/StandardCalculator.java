package bmi.calculator;

public class StandardCalculator extends Calculator {
    
    private double bmi;
    
    @Override
    public double calculate(double w, double h){
       
        double bmi = w/(Math.pow(h,2));
        return bmi;
    }//end of method calculate
}//end of class StandardCalcualtor
