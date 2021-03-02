
/**
 * Operations on equations that involve one variable using a formula for each
 * degree
 * 
 * @author Alan Joseph, Andrew Wang, Meggan Shvartsberg, Jared Levin, Nihal
 *         Bankulla, Daniel Porotov, David Volchonock, John Tur, Varun Singh
 * @implNote All methods are static
 */
class Monovariable {
  public static ComplexNumber useLinearFormula(double aNum, double bNum) {
    ComplexNumber aSolution = new ComplexNumber(aNum);
    ComplexNumber bSolution = new ComplexNumber(bNum);

    ComplexNumber negativeB = bSolution.times(-1);
    return negativeB.divideBy(aSolution);
  }

  /**
   * Uses the quadratic formula to solve the equation
   */
  public static ComplexNumber[] useQuadraticFormula(double aNum, double bNum, double cNum) {
    double bSquared = Math.pow(bNum, 2);
    double fourTimesATimesC = 4 * aNum * cNum;

    // Full discriminant
    ComplexNumber discriminant = new ComplexNumber(bSquared - fourTimesATimesC);
    ComplexNumber rootOfDiscriminant = discriminant.sqrt();

    ComplexNumber twoTimesA = new ComplexNumber(2).times(aNum);
    ComplexNumber negativeB = new ComplexNumber(-bNum);

    // Solution with plus sign
    ComplexNumber xPlus = negativeB.plus(rootOfDiscriminant).divideBy(twoTimesA);

    // Solution with minus sign
    ComplexNumber xMinus = negativeB.minus(rootOfDiscriminant).divideBy(twoTimesA);

    return new ComplexNumber[] { xPlus, xMinus };
  }

  public static ComplexNumber[] useCubicFormula(double aNum, double bNum, double cNum, double dNum) {
    ComplexNumber delta0 = new ComplexNumber(bNum).pow(2).minus(new ComplexNumber(3).times(aNum).times(cNum));

    ComplexNumber delta1part1 = new ComplexNumber(bNum).pow(3).times(2);
    ComplexNumber delta1part2 = new ComplexNumber(9).times(aNum).times(bNum).times(cNum);
    ComplexNumber delta1part3 = new ComplexNumber(aNum).pow(2).times(27).times(dNum);

    ComplexNumber delta1 = delta1part1.minus(delta1part2).plus(delta1part3);

    ComplexNumber C3 = delta0.pow(3).times(4);
    ComplexNumber insideSqrt = delta1.pow(2).minus(C3).sqrt();
    ComplexNumber cbrtNumerator = delta1.plus(insideSqrt);

    // This operation is plus or minus; if C is 0 when using plus, these following
    // steps
    // must be recomputed by using minus, instead
    ComplexNumber c = cbrtNumerator.divideBy(2).cbrt();

    if (c.getRealPart() == 0 && c.getImaginaryPart() == 0) {
      c = delta1.minus(insideSqrt).divideBy(2).cbrt();
    }

    ComplexNumber xi = new ComplexNumber(-1).plus(new ComplexNumber(-3).sqrt()).divideBy(2);
    ComplexNumber minus1Over3A = new ComplexNumber(-1).divideBy(new ComplexNumber(3).times(aNum));

    ComplexNumber[] solutions = new ComplexNumber[3];

    for (int i = 0; i <= 2; i++) {
      ComplexNumber xiTimesC = xi.pow(i).times(c);
      System.out.println(String.format("%s (%s + %s + (%s / %s)", minus1Over3A.toString(), xiTimesC.toString(), String.valueOf(bNum), delta0.toString(), xiTimesC.toString()));
      
      solutions[i] = minus1Over3A.times(xiTimesC.plus(bNum).plus(delta0.divideBy(xiTimesC)));
    }

    return solutions;
  }

  public static ComplexNumber[] useQuarticFormula(double aNum, double bNum, double cNum, double dNum, double eNum) { // QUARTIC
    // Store placeholder variables p, q, and r

    ComplexNumber aSolution = new ComplexNumber(bNum / aNum);
    ComplexNumber bSolution = new ComplexNumber(cNum / aNum);
    ComplexNumber cSolution = new ComplexNumber(dNum / aNum);
    ComplexNumber dSolution = new ComplexNumber(eNum / aNum);

    //
    ComplexNumber p = bSolution.minus(new ComplexNumber(3.0 / 8.0).times(aSolution.pow(2)));

    ComplexNumber abOver2 = aSolution.times(bSolution).divideBy(2);
    ComplexNumber aCubedOver8 = aSolution.times(3).divideBy(8);

    ComplexNumber q = cSolution.minus(abOver2).plus(aCubedOver8);

    // https://drive.google.com/file/d/146JquWZG-x3oddYL6bQMfFjDoyn7L5zk/view
    ComplexNumber acOver4 = aSolution.times(cSolution).divideBy(4);
    ComplexNumber aSquaredBOver16 = aSolution.pow(2).times(bSolution).divideBy(16);

    ComplexNumber threeAFourthOver256 = aSolution.pow(4).times(3).divideBy(256);
    ComplexNumber firstHalfOfR = dSolution.minus(acOver4);
    ComplexNumber secondHalfOfR = aSquaredBOver16.minus(threeAFourthOver256);
    ComplexNumber r = firstHalfOfR.plus(secondHalfOfR);

    double first = 1.0;
    double second = p.getRealPart() * 2.0;
    double third = p.getRealPart() * p.getRealPart() - r.getRealPart() * 4;
    double fourth = q.getRealPart() * q.getRealPart() * -1.0;

    System.out.println(String.format("%fx^3 + %fx^2 + %fx + %f", first, second, third, fourth));

    ComplexNumber[] allLambda = useCubicFormula(first, second, third, fourth);

    ComplexNumber lambda = allLambda[0];
    ComplexNumber lambda1 = allLambda[1];
    ComplexNumber lambda2 = allLambda[2];
    System.out.println("lambda = " + lambda.toString());
    System.out.println("lambda1 = " + lambda1.toString());
    System.out.println("lambda2 = " + lambda2.toString());

    // (+,+)
    // The last three lines from this and sqrtLambda are just given different
    // iterations
    ComplexNumber sqrtLambda = lambda.sqrt();
    ComplexNumber qOverSqrtLambda = q.divideBy(sqrtLambda); 
    ComplexNumber innerParenPlus = p.plus(lambda).plus(qOverSqrtLambda);
    ComplexNumber twoTimesInnerParenPlus = innerParenPlus.times(2);
    ComplexNumber lambdaMinusTwoTimesInnerParenPlus = lambda.minus(twoTimesInnerParenPlus);

    // x^4+5x^3-7x^2+2x+9 = 0
    ComplexNumber innerSqrt = lambdaMinusTwoTimesInnerParenPlus.sqrt();
    ComplexNumber sqrtLambdaPlusInnerSqrt = sqrtLambda.plus(innerSqrt);
    ComplexNumber outerParenOver2 = sqrtLambdaPlusInnerSqrt.divideBy(2);
    ComplexNumber aOver4 = aSolution.divideBy(4);

    // (+,-)
    ComplexNumber secondSqrtLambdaPlusInnerSqrt = sqrtLambda.minus(innerSqrt); 
    ComplexNumber secondOuterParenOver2 = secondSqrtLambdaPlusInnerSqrt.divideBy(2);
    ComplexNumber secondAOver4 = aSolution.divideBy(4);

    ComplexNumber innerParenMinus = p.plus(lambda).minus(qOverSqrtLambda);
    ComplexNumber twoTimesInnerParenMinus = innerParenMinus.times(2);
    ComplexNumber lambdaMinusTwoTimesInnerParenMinus = lambda.minus(twoTimesInnerParenMinus);

    innerSqrt = lambdaMinusTwoTimesInnerParenMinus.sqrt();

    // (-,-)
    ComplexNumber negativeSqrtLambda = new ComplexNumber(-1).times(lambda.sqrt());
    ComplexNumber thirdSqrtLambdaMinusInnerSqrt = negativeSqrtLambda.minus(innerSqrt);//
    ComplexNumber thirdOuterParenOver2 = thirdSqrtLambdaMinusInnerSqrt.divideBy(2); 
    ComplexNumber thirdAOver4 = aSolution.divideBy(4);
    // (-,+)
    ComplexNumber fourthSqrtLambdaMinusInnerSqrt = negativeSqrtLambda.plus(innerSqrt);//
    ComplexNumber fourthOuterParenOver2 = fourthSqrtLambdaMinusInnerSqrt.divideBy(2);
    ComplexNumber fourthAOver4 = aSolution.divideBy(4);

    // NEED TO ACCOUNT FOR ALL 4 CASES
    ComplexNumber result1 = outerParenOver2.minus(aOver4); // (+,+)
    ComplexNumber result2 = secondOuterParenOver2.minus(secondAOver4);  // (+,-)
    ComplexNumber result3 = thirdOuterParenOver2.minus(thirdAOver4); // (-,-)
    ComplexNumber result4 = fourthOuterParenOver2.minus(fourthAOver4); // (-,+)

    ComplexNumber[] result = { result1, result2, result3, result4 };

    return result;
  }

  /**
   * @deprecated Convenience method for ComplexService.calculateComplex()
   */
  public static double[] calcComp(double num1[], char sign, double[] num2) {
    return ComplexService.calculateComplex(num1, sign, num2);
  }
}