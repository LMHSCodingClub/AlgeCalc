import java.lang.Math;

import org.graalvm.compiler.nodes.memory.MemoryCheckpoint.Single;


class ComplexService {
  
  private double[] polarize(double num[])
  {
    double r = sqrt( Math.pow(num[0], 2) + Math.pow(num[1],2) );
    double theta = Math.arctan(num[1] / num[0]);
    double[] polar = {r, theta};
    return polar;

  }

  private double[] depolarize(double polar[])
  {
    double[] num = new double[2];

    num[0] = Math.cos(polar[1])*polar[0];
    num[1] = Math.sin(polar[1])*polar[0];

    return num;
  }

// modification: z = r(cos x + i sin x)
// r^(1/n) ( cos((x+2pi*k)/(n)) + i*sin((x+2pi*k)/n))
  
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
        
// ((ac+bd) / (c^2 + d^2)+  i(bc-ad)/(c^2 + d^2) 
        num3[0] = ((num1[0]*num2[0]) +  num1[1]*num2[1])/(Math.pow(num2[0],2) + Math.pow(num2[1],2));
        num3[1] = ((num1[1]*num2[0]) - (num1[0]*num2[1]))/(Math.pow(num2[0], 2) + Math.pow(num2[1], 2));
    break;

    case '3': //cube root
          double[] polar = polarize(num1);
          num3[0] = Math.pow(polar[0],1/3) * Math.cos((polar[1]/(3))); // You add a 2pi*k for other roots
          num3[1] = Math.pow(polar[0],1/3) * Math.sin((polar[1]/(3))); // You add a 2pi*k for other roots
          
    break;
//DM: (cos x + i*sin x)^n = cos (nx) + i*sin(nx)
// modification: z = r(cos x + i sin x)
// r^(1/n) ( cos((x+2pi*k)/(n)) + i*sin((x+2pi*k)/n))
    case '2': // square root
        double[] polar = polarize(num1);
        num3[0] = Math.pow(polar[0],1/32) * Math.cos((polar[1]/(2))); // You add a 2pi*k for other roots
        num3[1] = Math.pow(polar[0],1/2) * Math.sin((polar[1]/(2))); // You add a 2pi*k for other roots
    break; 
    case 's': //cube root
          double[] polar = polarize(num1);
          num3[0] = Math.pow(polar[0],1/3) * Math.cos((polar[1]/(3))); // You add a 2pi*k for other roots
          num3[1] = Math.pow(polar[0],1/3) * Math.sin((polar[1]/(3))); // You add a 2pi*k for other roots
          
    break;
    default:
      throw new IllegalArgumentException("Moron! Wrong operator");
      //break;
    
      }
      return num3;
  } 

}

