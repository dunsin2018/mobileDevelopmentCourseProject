package com.app.mycalculator.EquationHandler;

import android.text.TextUtils;

import com.app.mycalculator.Model.ResultModel;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.text.DecimalFormat;

public class EquationCalculator
{




    static    String symbols[]=new String[]{"^","√","/","%","(",")","*","-","+"};
   static  DecimalFormat formater = new DecimalFormat("#.##");
    public static boolean shouldAdd(String equation,String newDigit)
    {

        if(TextUtils.isEmpty(equation))
            return true;


        char c = equation.charAt(equation.length() - 1);
        boolean  lastDidgit= isSymbole(String.valueOf(c));
        boolean  newdigit= isSymbole(newDigit);


      if(!newdigit)
          return true;

      return  lastDidgit!=newdigit; //If Last character On Screen And New character to add  Is not Symbol


    }


    public  static  ResultModel ConvertMilesToKm(String miles)
    {
        Double Miles= Double.parseDouble(miles);


        ResultModel model=new ResultModel();
        model.setStatus("Result");
        model.setResult(formater.format(Miles * 1.609));
        return model;
    }
    public  static  ResultModel ConvertKmtoMiles(String km)
    {
        Double Km= Double.parseDouble(km);


        ResultModel model=new ResultModel();
        model.setStatus("Result");



        model.setResult(formater.format(Km / 1.609));
        return model;
    }
    private static boolean isSymbole(String valueOf)
    {


        for(String symbol:symbols)
        {
            if(valueOf.equals(symbol))
                return true;
        }

        return false;
    }

   public static ResultModel calculate(String expression)
    {


        ResultModel resultObj=new ResultModel();
        try{

            ExpressionBuilder obj=new ExpressionBuilder(expression);
            Expression build = obj.build();

            resultObj.setStatus("Result");
            resultObj.setResult(formater.format(build.evaluate()));
            return resultObj;
        }
        catch (Exception e)
        {
            resultObj.setStatus("Error");
            resultObj.setResult(e.getMessage());
            return  resultObj;
        }
    }


}
