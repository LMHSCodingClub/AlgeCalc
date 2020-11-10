//REMEMBER TO SEND A SHARE LINK NOT THE LINK ON TOP
import java.lang.Math;
import java.util.Scanner;

class Monovariable {
  public static double[] useLinearFormula(double a, double b) {
    return new double[] {-b / a};
  }

  public static double[] useQuadraticFormula(double a, double b, double c) {
    double discriminant = Math.pow(b, 2) - 4*a*c;
    
   if (discriminant >= 0) { // If the result is a real number (rational or irrational)
    double result1 = (-b + Math.sqrt(discriminant) / (2 * a));
    double result2 = (-b - Math.sqrt(discriminant) / (2 * a));
    return new double[] {result1, result2};
   } 

   return new double[] {};    
  }

  public static double[] useCubicFormula(double a, double b, double c, double d) // CUBIC FORMULA
  {
    // USE ARRAYS TO REPRESENT EVERYTHING AS A COMPLEX NUMBER!!!!
    double p = -b/(3*a);
    double q = Math.pow(p, 3) + (b*c-3*a*d)/(6*Math.pow(a,2));
    double r = c/(3*a);

    //double xPart1 = (Math.pow(Math.pow(q + (Math.pow(q,2) + (r - Math.pow(Math.pow(p,2),(1/2)),(1/3)))))));
    double xPart1 = Math.pow(q + Math.pow((Math.pow(q,2) + Math.pow(Math.pow(r - Math.pow(p,2)),3)),(1/2)),1/3);

    //double xPart2 = Math.pow(q - (Math.pow(q, 2) + Math.pow(Math.pow(r-Math.pow(p, 2), 3), (1/2), (1/3));
    
    double x = xPart1 + xPart2 + p;
    
    double result1 = x;
    double result2 = -9898.98;
    double result3 = -8989.89;

    return new double[] {result1, result2, result3};
  
  }
  

  public static double[] useCubicFormulaLambda(double a, double b, double c, double d) // CUBIC FORMULA, BUT ONLY RETURNS ONE ANSWER
  {
    
    double delta0 =  Math.pow(a,3)*c;
    double delta1 = 2*Math.pow(b,3)-9*a*b*c+27*Math.pow(a,2)*d;
    
    double bigC = Math.cbrt((delta1 + Math.sqrt(Math.pow(delta1, 2) - Math.pow(delta0, 3)))/2);

    if ( bigC == 0)
    {
      bigC = Math.cbrt((delta1 - Math.sqrt(Math.pow(delta1, 2) - Math.pow(delta0, 3)))/2); 
    }
    double result1 = -1/(3*a)*(b+bigC+delta0/bigC);

    double result2 = bigC * ((-1 + Math.sqrt(-3))/2);
    double result3 = bigC * ((-1 - Math.sqrt(-3))/2);

    return new double[] {result1};//commmunism
  
  }

  public static double[] useQuarticFormula(double a, double b, double c, double d, double e) { // QUARTIC FORMULA
      // Store placeholder variables p, q, and r
      double p = b - (3 * Math.pow(a, 2)) / 8;
      double q = c - (a * b / 2) + (Math.pow(a, 3) / 8);
      double r = d - (a * c / 4) + (Math.pow(a, 2) * b) / 16 - 3 * Math.pow(a, 4) / 256;

      double l = useCubicFormulaLambda(1, 2*p, (Math.pow(p,2) - 4*r), Math.pow(q,2));
      double result1 = -Math.sqrt(l) + Math.sqrt(l - 2*(p + l + (q/Math.sqrt(l))))/2 - (a/4);
      double result2 = -Math.sqrt(l) - Math.sqrt(l - 2*(p + l + (q/Math.sqrt(l))))/2 - (a/4);
      double result3 = Math.sqrt(l) + Math.sqrt(l - 2*(p + l + (q/Math.sqrt(l))))/2 - (a/4);
      double result4 = Math.sqrt(l) - Math.sqrt(l - 2*(p + l + (q/Math.sqrt(l))))/2 - (a/4);
      
    return new double[] {result1, result2, result3, result4};
  }
} 
