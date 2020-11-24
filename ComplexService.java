import java.lang.Math;


class ComplexService {
  
  private double[] polarize(doube num[])
  {
    double r = sqrt(num[0]);
  }
  
  
  private double[] calculateComplex(double num1[], char sign, double[] num2) {

    double[] num3 = new double[2]; // Complex-ate this
      
    switch(sign) {
      case '+':
        num3[0] = num1[0] + num2[0];
        num3[1] = num1[1] + num2[1];
      break;
    
      case '-':
        num3[0] = num1[0] - num2[0];
        num3[1] = num1[1] - num2[1];
      break;
    
      case '*':
        num3[0] = (num1[0]*num2[0]) - (num1[1]*num2[1]);
        num3[1] = (num1[1]*num2[0]) + (num1[0]*num2[1]);
      break;
    
      case '^': // ONLY REAL INT EXPONENTS ALLOWED
      
      num3[0] = 1;
      num3[1] = 1;
      if ( num2[0] > 0)
      {
        for (int i = 0; i < (int) num2[0]; i++) 
        {
          num3 = calculateComplex(num3,'*',num1);
        }
      }
      else // num2[0] < 0
      {
        for (int i = 0; i > (int)(num2[0]); i--)
        {
          num3 = calculateComplex(num3,'/',num1);
        }
      }
    
    break;

    case '/':
          num3[0] = ((num1[0]*num2[0]) + (num1[1]*num2[1]))/(Math.pow(num2[0], 2) + Math.pow(num2[1],2));
          num3[1] = ((num1[1]*num2[0]) - (num1[0]*num2[1]))/(Math.pow(num2[0], 2) + Math.pow(num2[1], 2));

    break;

    case '3': //cube root


    break;
//DM: (cos x + i*sin x)^n = cos (nx) + i*sin(nx)
// modification: z = r(cos x + i sin x)
// r^(1/n) ( cos((x+2pi*k)/(n)) + i*sin((x+2pi*k)/n))
    case '2': // square root
      num3[0]
    break;
    
    default:
      throw new IllegalArgumentException("Moron! Wrong operator");
      //break;
    
      }
      return num3;
  }

}

