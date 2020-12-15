
class MyProgram {
  public static void main(String[] args) {

    double[] num1 = { 18, -26 };

    double[] num3 = calculateComplex(num1, '3', null);

    System.out.println(num3[0] + " + " + num3[1] + "i");
  }

  // Muhlenberg2008!!!
  // It's a good place

  public static double[] addComplex(double[] num1, double[] num2) {
    double[] num3 = new double[2];
    num3[0] = (num1[0] + num2[0]);
    num3[1] = (num1[1] + num2[1]);
    return num3;
  }

  public static double[] subtractComplex(double[] num1, double[] num2) {
    double[] num3 = new double[2];
    num3[0] = (num1[0] - num2[0]);
    num3[1] = (num1[1] - num2[1]);
    return num3;
  }

  public static double[] multiplyComplex(double[] num1, double[] num2) {
    double[] num3 = new double[2];
    num3[0] = (num1[0] * num2[0]) - (num1[1] * num2[1]);
    num3[1] = (num1[1] * num2[0]) + (num1[0] * num2[1]);
    return num3;
  }

  public static double[] calculateComplex(double num1[], char sign, double[] num2) {

    double[] num3 = new double[2]; // Complex-ate this

    switch (sign) {
      case '+':
        num3[0] = num1[0] + num2[0];
        num3[1] = num1[1] + num2[1];
        break;

      case '-':
        num3[0] = num1[0] - num2[0];
        num3[1] = num1[1] - num2[1];
        break;

      case '*':
        num3[0] = (num1[0] * num2[0]) - (num1[1] * num2[1]);
        num3[1] = (num1[1] * num2[0]) + (num1[0] * num2[1]);
        break;

      case '^': // ONLY REAL INT EXPONENTS ALLOWED

        num3[0] = 1;
        num3[1] = 1;
        if (num2[0] > 0) {
          for (int i = 0; i < (int) num2[0]; i++) {
            num3 = calculateComplex(num3, '*', num1);
          }
        } else // num2[0] < 0, negative exponent
        {
          for (int i = 0; i > (int) (num2[0]); i--) {
            num3 = calculateComplex(num3, '/', num1);
          }
        }

        break;

      case '3': // cube root
        double[] polar = polarize(num1);
        num3[0] = Math.pow(polar[0], 1 / 3.0) * Math.cos((polar[1] / (3))); // You add a 2pi*k for other roots
        num3[1] = Math.pow(polar[0], 1 / 3.0) * Math.sin((polar[1] / (3))); // You add a 2pi*k for other roots

        break;
      // DM: (cos x + i*sin x)^n = cos (nx) + i*sin(nx)
      // modification: z = r(cos x + i sin x)
      // r^(1/n) ( cos((x+2pi*k)/(n)) + i*sin((x+2pi*k)/n))
      case '2': // square root
        double[] polar2 = polarize(num1);
        num3[0] = Math.pow(polar2[0], 1 / 2.0) * Math.cos((polar2[1] / (2))); // You add a 2pi*k for other roots
        num3[1] = Math.pow(polar2[0], 1 / 2.0) * Math.sin((polar2[1] / (2))); // You add a 2pi*k for other roots
        break;

      case '/':
        num3[0] = ((num1[0] * num2[0]) + num1[1] * num2[1]) / (Math.pow(num2[0], 2) + Math.pow(num2[1], 2));
        num3[1] = ((num1[1] * num2[0]) - (num1[0] * num2[1])) / (Math.pow(num2[0], 2) + Math.pow(num2[1], 2));

        break;

      default:  
        throw new IllegalArgumentException("Moron! Wrong operator");
      // break;

    }
    return num3;
  }

  public static double[] polarize(double num[]) {
      double r = Math.sqrt(Math.pow(num[0], 2) + Math.pow(num[1], 2));
      double theta = Math.atan(num[1] / num[0]);
    double[] polar = { r, theta };
    return polar;

  }
}