
/** 
 * Operations on equations that involve one variable using a formula for each degree
 * @author Alan Joseph, Andrew Wang, Meggan Shvartsberg, Jared Levin, 
 * Nihal Bankulla, Daniel Porotov, David Volchonock, John Tur, Varun Singh
 * @implNote All methods are static
 */

import java.util.Arrays;

class Monovariable {
  public static double[] useLinearFormula(double aNum, double bNum) {

    double[] aSolution = new double[] { aNum, 0 };
    double[] bSolution = new double[] { bNum, 0 };

    double[] negativeB = ComplexService.findProduct(new double[] { -1, 0 }, bSolution);
    double[] twoA = ComplexService.findProduct(new double[] { 2, 0 }, aSolution);
    return ComplexService.findQuotient(negativeB, twoA);
  }

  /**
   * Uses the quadratic formula to solve the equation
   */
  public static double[][] useQuadraticFormula(double aNum, double bNum, double cNum) {
    double bSquared = Math.pow(bNum, 2);
    double fourTimesATimesC = 4 * aNum * cNum;

    // Full discriminant
    double[] discriminant = new double[] { bSquared - fourTimesATimesC, 0 };
    double[] rootOfDiscriminant = ComplexService.findSquareRoot(discriminant);

    double[] twoTimesA = new double[] { 2 * aNum, 0 };
    double[] negativeB = new double[] { -bNum, 0 };

    // Solution with plus sign
    double[] xPlus = ComplexService.findQuotient(ComplexService.findSum(negativeB, rootOfDiscriminant), twoTimesA);

    // Solution with minus sign
    double[] xMinus = calcComp(calcComp(negativeB, '-', rootOfDiscriminant), '/', twoTimesA);

    return new double[][] { xPlus, xMinus };

  }

  public static double[][] useCubicFormula(double aNum, double bNum, double cNum, double dNum) // CUBIC FORMULA
  {
    double[] aSolution = new double[] { aNum, 0 };
    double[] bSolution = new double[] { bNum, 0 };
    double[] cSolution = new double[] { cNum, 0 };
    double[] dSolution = new double[] { dNum, 0 };

    double[] delta0 = calcComp(calcComp(bSolution, '^', new double[] { 2.0, 0 }), '-',
        calcComp(calcComp(aSolution, '*', cSolution), '*', new double[] { 3.0, 0 }));

    double[] delta1 = calcComp(
        calcComp(calcComp(new double[] { 2, 0 }, '*', calcComp(bSolution, '^', new double[] { 3.0, 0 })), '-',
            calcComp(calcComp(calcComp(new double[] { 9, 0 }, '*', aSolution), '*', bSolution), '*', cSolution)),
        '+', calcComp(calcComp(new double[] { 27, 0 }, '*', calcComp(aSolution, '^', new double[] { 2.0, 0 })), '*',
            dSolution));

    double[] C2 = calcComp(delta0, '^', new double[] { 3, 0 }); // d
    double[] C3 = calcComp(C2, '*', new double[] { 4, 0 });
    double[] C4 = calcComp(delta1, '^', new double[] { 2, 0 });
    double[] C5 = calcComp(C4, '-', C3);
    double[] C6 = ComplexService.findSquareRoot(C5);

    // This operation is plus or minus; if C is 0 when using plus, these following
    // steps
    // must be recomputed by using minus, instead
    double[] C7 = calcComp(delta1, '+', C6);
    double[] C8 = calcComp(C7, '/', new double[] { 2, 0 });
    double[] C = calcComp(C8, '3', new double[] {});

    if (C[0] == 0 && C[1] == 0) {
      C7 = calcComp(delta1, '-', C6);
      C8 = calcComp(C7, '/', new double[] { 2, 0 });
      C = calcComp(C8, '3', null);
    }

    // sqrt(3)*i
    double[] squareRootOfThreeI = ComplexService.findSquareRoot(new double[] { -3, 0 });
    double[] xiPlus = calcComp(ComplexService.findSum(
      new double[] { -1.0, 0 },
      squareRootOfThreeI
    ), '/', new double[] { 2.0, 0 } );

    double[] xi1 = new double[] { xiPlus[0], xiPlus[1] };
    double[] xi2 = calcComp(xiPlus, '^', new double[] { 2, 0 });

    double[] negativeOneOver3A = new double[] { -1.0 / (3.0 * aNum), 0 };
    
    double[] firstSolution = calcComp(negativeOneOver3A,
        '*', sumMultiple(bSolution, C, calcComp(delta0, '/', C)));

    double[] xiTimesC = calcComp(xi1, '*', C);

    double[] secondSolution = calcComp(
      negativeOneOver3A, 
      '*', 
      sumMultiple(bSolution, xiTimesC, calcComp(delta0, '/', xiTimesC))
    );

    xiTimesC = calcComp(xi2, '*', C);
    
    double[] thirdSolution = calcComp(
      negativeOneOver3A,
      '*',
      sumMultiple(bSolution, xiTimesC, calcComp(delta0, '/', xiTimesC))
    );

    return new double[][] { firstSolution, secondSolution, thirdSolution };
  }

  private static double[] sumMultiple(double[] first, double[] second, double[] third) {
    return ComplexService.findSum(ComplexService.findSum(first, second), third);
  }

  public static double[][] useQuarticFormula(double aNum, double bNum, double cNum, double dNum, double eNum) { // QUARTIC
    // Store placeholder variables p, q, and r

    double[] aSolution = new double[] { bNum / aNum, 0 };
    double[] bSolution = new double[] { cNum / aNum, 0 };
    double[] cSolution = new double[] { dNum / aNum, 0 };
    double[] dSolution = new double[] { eNum / aNum, 0 };

    //
    double[] p = calcComp(bSolution, '-',
        calcComp(new double[] { 3.0 / 8.0, 0 }, '*', calcComp(aSolution, '^', new double[] { 2, 0 })));

    double[] abOver2 = calcComp(calcComp(aSolution, '*', bSolution), '/', new double[] { 2, 0 }); // (Singh, 2020),
                                                                                                  // Jared stole this.
    double[] aCubedOver8 = calcComp(calcComp(aSolution, '^', new double[] { 3, 0 }), '/', new double[] { 8, 0 });

    double[] q = calcComp(calcComp(cSolution, '-', abOver2), '+', aCubedOver8);

    double[] acOver4 = calcComp(calcComp(aSolution, '*', cSolution), '/', new double[] { 4, 0 });
    double[] aSquaredBOver16 = calcComp(calcComp(bSolution, '*', calcComp(aSolution, '^', new double[] { 2, 0 })), '/',
        new double[] { 16, 0 });
    double[] threeAFourthOver256 = calcComp(
        calcComp(calcComp(aSolution, '^', new double[] { 4, 0 }), '*', new double[] { 3, 0 }), '/',
        new double[] { 256, 0 });
    double[] firstHalfOfR = ComplexService.findDifference(dSolution, acOver4);
    double[] secondHalfOfR = ComplexService.findDifference(aSquaredBOver16, threeAFourthOver256);
    double[] r = ComplexService.findSum(firstHalfOfR, secondHalfOfR);

    // TODO: Fix useCubicFormula()
    double first = 1.0;
    double second = p[0] * 2.0;
    double third = p[0] * p[0] - r[0] * 4;
    double fourth = q[0] * q[0] * -1.0;
    double[][] allLambda = useCubicFormula(first, second, third, fourth);

    double[] lambda = allLambda[0];
    double[] lambda1 = allLambda[1];
    double[] lambda2 = allLambda[2];
    System.out.println("lambda = " + Arrays.toString(lambda));
    System.out.println("lambda1 = " + Arrays.toString(lambda1));
    System.out.println("lambda2 = " + Arrays.toString(lambda2));

    // (+,+)
    // The last three lines from this and sqrtLambda are just given different
    // iterations
    double[] sqrtLambda = ComplexService.findSquareRoot(lambda);
    double[] qOverSqrtLambda = calcComp(q, '/', sqrtLambda);
    double[] innerParenPlus = calcComp(calcComp(p, '+', lambda), '+', qOverSqrtLambda);
    double[] twoTimesInnerParenPlus = calcComp(new double[] { 2, 0 }, '*', innerParenPlus);
    double[] lambdaMinusTwoTimesInnerParenPlus = calcComp(lambda, '-', twoTimesInnerParenPlus);

    // x^4+5x^3-7x^2+2x+9 = 0
    double[] innerSqrt = ComplexService.findSquareRoot(lambdaMinusTwoTimesInnerParenPlus);
    double[] sqrtLambdaPlusInnerSqrt = ComplexService.findSum(sqrtLambda, innerSqrt);//
    double[] outerParenOver2 = calcComp(sqrtLambdaPlusInnerSqrt, '/', new double[] { 2, 0 });
    double[] aOver4 = calcComp(aSolution, '/', new double[] { 4, 0 });

    // (+,-)
    double[] secondSqrtLambdaPlusInnerSqrt = calcComp(sqrtLambda, '-', innerSqrt);//
    double[] secondOuterParenOver2 = calcComp(secondSqrtLambdaPlusInnerSqrt, '/', new double[] { 2, 0 });
    double[] secondAOver4 = calcComp(aSolution, '/', new double[] { 4, 0 });

    // (-,-)
    double[] negativeSrtLambda = calcComp(new double[] { -1, 0 }, '*', ComplexService.findSquareRoot(lambda));
    double[] thirdSqrtLambdaMinusInnerSqrt = calcComp(negativeSrtLambda, '-', innerSqrt);//
    double[] thirdOuterParenOver2 = calcComp(thirdSqrtLambdaMinusInnerSqrt, '/', new double[] { 2, 0 });
    double[] thirdAOver4 = calcComp(aSolution, '/', new double[] { 4, 0 });

    // (-,+)
    double[] fourthSqrtLambdaMinusInnerSqrt = calcComp(negativeSrtLambda, '+', innerSqrt);//
    double[] fourthOuterParenOver2 = calcComp(fourthSqrtLambdaMinusInnerSqrt, '/', new double[] { 2, 0 });
    double[] fourthAOver4 = calcComp(aSolution, '/', new double[] { 4, 0 });

    // NEED TO ACCOUNT FOR ALL 4 CASES
    double[] result1 = calcComp(outerParenOver2, '-', aOver4); // (+,+)
    double[] result2 = calcComp(secondOuterParenOver2, '-', secondAOver4); // (+,-)
    double[] result3 = calcComp(thirdOuterParenOver2, '-', thirdAOver4); // (-,-)
    double[] result4 = calcComp(fourthOuterParenOver2, '-', fourthAOver4); // (-,+)

    double[][] result = { result1, result2, result3, result4 };

    return result;
  }

  /**
   * @deprecated
   * Convenience method for ComplexService.calculateComplex()
   */
  public static double[] calcComp(double num1[], char sign, double[] num2) {
    return ComplexService.calculateComplex(num1, sign, num2);
  }

}
