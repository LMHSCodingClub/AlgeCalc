import java.lang.Math; 

class Main
{
     public static void main(String[] args)
     {
      
      
     }
    
    public static double monovar()
    {
      double a1 = 1.1; // random inputs
      double a2 = 2.1;
      double a3 = 1.1;
      double a4 = 2.1;
      double a5 = 1.1;
      
      double[]arr = alphebatize(a1, a2, a3, a4, a5);
       
      // double b = [0]arr; figure out array usage
      depress(b, c, d, e); // return somehow
        
      return 0.0;
    }
    
    public static double alphabetize(a1, a2, a3, a4, a5)
    {
      double b = a2/a1;
      double c = a3/a1;
      double d = a4/a1;
      double e = a5/a1;
       
      double[]arr = {b, c, ,d , e};
        
      return arr;
    }
    
    public static double depress(b, c, d, e)
    {
      
        
        
        
      return ; // figure out
    }
     
      public static double[] useLinearFormula(double a, double b) {      // LINEAR FORMULA
    return new double[] {-b / a};
  }

  public static double[] useQuadraticFormula(double a, double b, double c) {  // QUADRATIC FORMULA
    double discriminant = Math.pow(b, 2) - 4*a*c;
    double result1 = (-b + Math.sqrt(discriminant) / (2 * a));
    double result2 = (-b - Math.sqrt(discriminant) / (2 * a));
    
  //  if (discriminant > 0){
    return new double[] {result1, result2};
  //  } else {
      //when discriminant is negative, return value with i
  //  }
  }

  public static double[] useCubicFormula(double a, double b, double c, double d) // CUBIC FORMULA
  {
    
    double delta0 =  Math.pow(a,3)*c;
    double delta1 = 2*Math.pow(b,3)-9*a*b*c+27*Math.pow(a,2)*d;
    
    double bigC = Math.cbrt((delta1 + Math.sqrt(Math.pow(delta1, 2) - Math.pow(delta0, 3)))/2);

    if ( bigC == 0)
    {
      bigC = Math.cbrt((delta1 - Math.sqrt(Math.pow(delta1, 2) - Math.pow(delta0, 3)))/2); 
    }
    double result1 = -1/(3*a)*(b+bigC+delta0/bigC);
    double result2 = -888.8;
    double result3 = -999.9;


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

    return new double[] {result1, result2, result3};
  
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
