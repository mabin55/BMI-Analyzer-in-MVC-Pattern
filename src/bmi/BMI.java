package bmi;

import bmi.calculator.Calculator;
import bmi.calculator.NewCalculator;
import bmi.controller.BMIController;
import bmi.controller.ICommands;
import bmi.model.BMIModel;
import bmi.model.data.BMIQueries;
import bmi.view.BMIView;

public class BMI {

    public static void main(String[] args) {
        
        ICommands ic = new BMIQueries();
        Calculator calc = new NewCalculator();
        BMIQueries bq = new BMIQueries();
        BMIModel bm = new BMIModel(bq);
        BMIController bc = new BMIController(ic, bm, calc);
        BMIView bv = new BMIView(bm, bc);
        bc.bind(bv);   
    }//end of main
}//end of class BMI
