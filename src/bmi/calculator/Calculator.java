package bmi.calculator;

public abstract class Calculator {
    
    //attribute
    private String category;
    
    //abstract method 
    public abstract double calculate(double w, double h);
    
    //method to set category of bmi
    public String categorise(double bmi){
        
        if(bmi>=0.0 && bmi<18.5){
                category = "Underweight";
            }

            if(bmi>=18.5 && bmi<25.0){
                category = "Normal Weight";
            }

            if(bmi>=25.0 && bmi<30.0){
                category = "Overweight";
            }

            if(bmi>=30.0 && bmi<35.0){
                category = "Obesity(Class1)";
            }

            if(bmi>=35.0 && bmi<40.0){
                category = "Obesity(Class 2)";
            }
            if(bmi>=40.0 && bmi<100.0){
                category = "Morbid Obesity";
            }
        return category;
    }//end of method categorise
}//end of class Calculator
