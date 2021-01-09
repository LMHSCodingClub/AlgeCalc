
// REMEMBER TO SEND A SHARE LINK NOT THE LINK ON TOP
import java.lang.Math;
import java.util.Scanner;

class Monovariable {
  public static double[] useLinearFormula(double aNum, double bNum) {

    double[] aSolution = new double[] { aNum, 0 };
    double[] bSolution = new double[] { bNum, 0 };

    return calcComp(calcComp(bSolution, '*', new double[] { -1, 0 }), '/', aSolution);
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
    double[] xPlus = calcComp(calcComp(negativeB, '+', rootOfDiscriminant), '/', twoTimesA);

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

    // calcComp(bSolution, '^', new double[] {2, 0}), '-', calcComp(new double[]
    // {3,0}, '*', calcComp(aSolution, '*', cSolution)));
    // NOTE THIS LINE EXISTS< AND IS MAYBE IMPORTANT???

    // SUSPICIOUS!!!!!!!!!!!!!!!
    double[] delta1 = calcComp(

        calcComp(
            // 2b^3 - 9abc + 27*a^2*d
            calcComp(calcComp(new double[] { 2, 0 }, '*', bSolution), '^', new double[] { 3.0, 0 }), '-',

            calcComp(calcComp(calcComp(new double[] { 9, 0 }, '*', aSolution), '*', bSolution), '*', cSolution)),
        '+',

        calcComp(

            calcComp(new double[] { 27, 0 }, '*', calcComp(aSolution, '^', new double[] { 2.0, 0 })), '*', dSolution));

    /*
     * double[] delta1 = calcComp(calcComp(calcComp([2*bNum, 0], '^', new double[]
     * {3.0, 0}), '-', new double[] {9*aNum*bNum*cNum,0}), '+',
     * calcComp(calcComp(new double[] {aNum*27, 0}, '^', new double[] {2.0, 0}),
     * '*', dSolution)
     */

    double[] C1 = calcComp(delta1, '^', new double[] { 2, 0 }); // d0 ^ 2
    double[] C2 = calcComp(delta0, '^', new double[] { 3, 0 }); // d0 ^ 3
    double[] C3 = calcComp(C2, '*', new double[] { 4, 0 });
    double[] C4 = calcComp(delta1, '^', new double[] { 2, 0 });
    double[] C5 = calcComp(C4, '-', C3);
    double[] C6 = ComplexService.findSquareRoot(C5);
    double[] C7 = calcComp(delta1, '+', C6);
    double[] C8 = calcComp(C7, '/', new double[] { 2, 0 });
    double[] C = calcComp(C8, '3', new double[] {});

    /*
     * double[] x = calcComp(new double[]{-1,0}, '/', calcComp(new double[]{3,0},
     * '*', aSolution), '*', (calcComp(calcComp(bSolution, '+', C), '+',
     * calcComp(delta0, '/', C))));
     */

    double[] neg1Over3a = calcComp(new double[] { -1, 0 }, '/', calcComp(new double[] { 3, 0 }, '*', aSolution));
    double[] deltaZeroOverC = calcComp(delta0, '/', C);
    double[] cPlusdeltaZeroOverC = calcComp(C, '+', deltaZeroOverC);
    double[] bPlusCPlusdeltaZeroOverC = calcComp(bSolution, '+', cPlusdeltaZeroOverC);
    double[] x = calcComp(neg1Over3a, '*', bPlusCPlusdeltaZeroOverC);

    //
    double[] xi = { -1 / 2, Math.sqrt(3) / 2 };
    double[] xi_sqr = calcComp(xi, '^', new double[] { 2, 0 });

    // COME BACK HERE DANIEL AND FINISH THE ALTERNATE X VALUES FOR CUBIC FORMULA
    double[] x0 = calcComp(calcComp(new double[] { -1, 0 }, '/', (calcComp(new double[] { 3, 0 }, '*', aSolution))),
        '*', calcComp(calcComp(bSolution, '+', C), '+', calcComp(delta0, '/', C)));
    double[] x1 = calcComp(calcComp(new double[] { -1, 0 }, '/', (calcComp(new double[] { 3, 0 }, '*', aSolution))),
        '*',
        calcComp(calcComp(bSolution, '+', calcComp(xi, '*', C)), '+', calcComp(delta0, '/', calcComp(xi, '*', C))));
    double[] x2 = calcComp(calcComp(new double[] { -1, 0 }, '/', (calcComp(new double[] { 3, 0 }, '*', aSolution))),
        '*', calcComp(calcComp(bSolution, '+', calcComp(xi_sqr, '*', C)), '+',
            calcComp(delta0, '/', calcComp(xi_sqr, '*', C))));

    return new double[][] { x0, x1, x2 };

    // int[][] twoD_arr = new int[10][20];
  }

  public static double[] useCubicFormulaLambda(double[] aSolution, double[] bSolution, double[] cSolution,
      double[] dSolution) // CUBIC FORMULA, BUT ONLY RETURNS ONE ANSWER
  {

    // double delta0 = Math.pow(a,3)*c;
    double[] delta0 = calcComp(calcComp(aSolution, '^', new double[] { 3.0, 0 }), '*', cSolution);

    // SUSPICIOUS!!!!!!!!!!!!!!!
    double[] delta1 = calcComp(

        calcComp(

            calcComp(calcComp(new double[] { 2, 0 }, '*', bSolution), '^', new double[] { 3.0, 0 }), '-',

            calcComp(calcComp(calcComp(new double[] { 9, 0 }, '*', aSolution), '*', bSolution), '*', cSolution)),
        '+',

        calcComp(

            calcComp(new double[] { 27, 0 }, '*', calcComp(aSolution, '^', new double[] { 2.0, 0 })), '*', dSolution));

    // double bigC = Math.cbrt((delta1 + Math.sqrt(Math.pow(delta1, 2) -
    // Math.pow(delta0, 3)))/2);
    double[] C1 = calcComp(delta1, '^', new double[] { 2, 0 }); // d0 ^ 2
    double[] C2 = calcComp(delta0, '^', new double[] { 3, 0 }); // d0 ^ 3
    double[] C3 = calcComp(C2, '*', new double[] { 4, 0 });
    double[] C4 = calcComp(delta1, '^', new double[] { 2, 0 });
    double[] C5 = calcComp(C4, '-', C3);
    double[] C6 = ComplexService.findSquareRoot(C5);
    double[] C7 = calcComp(delta1, '+', C6);
    double[] C8 = calcComp(C7, '/', new double[] { 2, 0 });
    double[] C = calcComp(C8, '3', new double[] {});

    if (C == new double[] { 0, 0 }) {
      // bigC = Math.cbrt((delta1 - Math.sqrt(Math.pow(delta1, 2) - Math.pow(delta0,
      // 3)))/2);
      C7 = calcComp(delta1, '-', C6);
      C8 = calcComp(C7, '/', new double[] { 2, 0 });
      C = calcComp(C8, '3', new double[] {});
    }

    // double result1 = -1/(3*a)*(b+bigC+delta0/bigC);
    double[] negOneOver3a = calcComp(new double[] { -1, 0 }, '/', calcComp(new double[] { 3, 0 }, '*', aSolution));
    double[] delta0OverC = calcComp(delta0, '/', C);
    double[] bPlusC = calcComp(bSolution, '+', C);
    double[] putEndTogether = calcComp(delta0OverC, '+', bPlusC);
    double[] result1 = calcComp(negOneOver3a, '*', putEndTogether);

    /*
     * //double result2 = bigC * ((-1 + Math.sqrt(-3))/2); double[] result2 =
     * calcComp(C, '*', calcComp(-1, '+', calcComp(calcComp(-3, '2', new double[]
     * {}), '/', 2)));
     * 
     * //double result3 = bigC * ((-1 - Math.sqrt(-3))/2); double[] result3 =
     * calcComp(C, '*', calcComp (-1, '-', calcComp(calcComp(-3, '2', new double[]
     * {}), '/', 2)));
     */

    return result1;

  }

  public static double[][] useQuarticFormula(double aNum, double bNum, double cNum, double dNum, double eNum) { // QUARTIC
                                                                                                                // FORMULA
    // Store placeholder variables p, q, and r

    double[] aSolution = new double[] { aNum, 0 };
    double[] bSolution = new double[] { bNum, 0 };
    double[] cSolution = new double[] { cNum, 0 };
    double[] dSolution = new double[] { dNum, 0 };
    double[] eSolution = new double[] { eNum, 0 };

    double[] p = calcComp(bSolution, '-', calcComp(new double[] { 3, 0 }, '*',
        calcComp(calcComp(aSolution, '*', aSolution), '/', new double[] { 8, 0 })));

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
    double[] firstHalfOfR = calcComp(dSolution, '-', acOver4);
    double[] secondHalfOfR = calcComp(aSquaredBOver16, '-', threeAFourthOver256);
    double[] r = calcComp(firstHalfOfR, '+', secondHalfOfR);

    double[] lambda = useCubicFormulaLambda(new double[] { 1, 0 }, calcComp(new double[] { 2, 0 }, '*', p),
        calcComp(calcComp(p, '*', p), '-', calcComp(new double[] { 4, 0 }, '*', r)),
        calcComp(new double[] { -1, 0 }, '*', calcComp(q, '*', q)));

    // (+,+)
    // The last three lines from this and sqrtLambda are just given different
    // itterations
    double[] sqrtLambda = ComplexService.findSquareRoot(lambda);
    double[] qOverSqrtLambda = calcComp(q, '/', sqrtLambda);
    double[] innerParen = calcComp(calcComp(p, '+', lambda), '-', qOverSqrtLambda);
    double[] twoTimesInnerParen = calcComp(new double[] { 2, 0 }, '*', innerParen);
    double[] lambdaMinusTwoTimesInnerParen = calcComp(lambda, '-', twoTimesInnerParen);
    double[] innerSqrt = ComplexService.findSquareRoot(lambdaMinusTwoTimesInnerParen);
    double[] sqrtLambdaPlusInnerSqrt = calcComp(sqrtLambda, '+', innerSqrt);//
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
   * Convenience method for ComplexService.calculateComplex()
   */
  public static double[] calcComp(double num1[], char sign, double[] num2) {
    return ComplexService.calculateComplex(num1, sign, num2);
  }

}
