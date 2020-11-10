class ComplexService {
  private double[] num1;
  private Operator operator;
  private double[] num2;

  public ComplexService(double[] num1, Operator operator, double[] num2) {
    this.num1 = num1;
    this.operator = operator;
    this.num2 = num2;
  }

  private double[] addComplex(double[] num1, double[] num2) {
    double num3 = new double[2];
    num3[0] = (num1[0] + num2[0]);
    num3[1] = (num1[1] + num2[1])
    return num3;
  }

  private double[] subtractComplex(double[] num1, double[] num2) {
    double num3 = new double[2];
    num3[0] = (num1[0] - num2[0]);
    num3[1] = (num1[1] - num2[1]);
    return num3;
  }

  private double[] multiplyComplex(double[] num1, double[] num2) {
    double num3 = new double[2];
    num3[0] = (num1[0]*num2[0]) - (num1[1]*num2[1]);
    num3[1] = (num1[1]*num2[0]) + (num1[0]*num2[1]);
    return num3;
  }

  private double[] divideComplex(double[] num1, double[] num2) {
    double num3 = new double[2];
    num3[0] = ((num1[0] * num2[0]) + (num1[1] * num2[1])) + 
                ((num1[1] * num2[0]) - (num1[0] * num2[1]));
    num3[1] = Math.pow(num2[0],2) + Math.pow(num2[1],2);
    return num3;
  }

  private double[] calculateComplex(double num1[], Operator sign, double[] num2) {

    double num3[] = new double[2]; // Complex-ate this
      
    switch(sign) {
      case Operator.PLUS:
        num3[0] = num1[0] + num2[0];
        num3[1] = num1[1] + num2[1];
      break;
    
      case Operator.SUBTRACT:
        num3[0] = num1[0] - num2[0];
        num3[1] = num1[1] - num2[1];
      break;
    
      case Operator.MULTIPLY:
        multiplyComplex(num1, num2);
      break;
    
      case Operator.CARET: // ONLY REAL INT EXPONENTS ALLOWED
      
      num3[0] = 1;
      num3[1] = 1;
      for (int i = 0, i < (int)(num2[0]); i++)
      {
        num3[] = multiplyComplex(num1, num2);
      }
    
    break;

    case Operator.SLASH:
          num3[0] = ((num1[0]*num2[0]) + (num1[1]*num2[1]))/(num2[0]^2 + num2[1]^2);
          num3[1] = ((num1[1]*num2[0]) - (num1[0]*num2[1]))/(num2[0]^2 + num2[1]^2);

    break;
    
    default:
      throw new InvalidArgumentException("Moron! Wrong operator");
    break;
    
      }
      return num3;
  }

}