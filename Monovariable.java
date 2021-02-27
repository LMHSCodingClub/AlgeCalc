import java.util.Arrays;

/** 
 * Operations on equations that involve one variable using a formula for each degree
 * @author Alan Joseph, Andrew Wang, Meggan Shvartsberg, Jared Levin, 
 * Nihal Bankulla, Daniel Porotov, David Volchonock, John Tur, Varun Singh
 * @implNote All methods are static
 */
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

    public static double[][] useCubicFormula(double aNum, double bNum, double cNum, double dNum) {
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

        double[][] solutions = new double[3][2];

        for (int i = 0; i <= 2; i++) {
            ComplexNumber xiTimesC = xi.pow(i).times(c);
            solutions[i] = minus1Over3A.times(
                    xiTimesC.plus(bNum).plus(delta0.divideBy(xiTimesC))).asDouble();
        }

        return solutions;
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
                                                                                                      // Jared stole
                                                                                                      // this.
            double[] aCubedOver8 = calcComp(calcComp(aSolution, '^', new double[] { 3, 0 }), '/', new double[] { 8, 0 });

        double[] q = calcComp(calcComp(cSolution, '-', abOver2), '+', aCubedOver8);

        double[] acOver4 = calcComp(calcComp(aSolution, '*', cSolution), '/', new double[] { 4, 0 });
        double[] aSquaredBOver16 = calcComp(calcComp(bSolution, '*', calcComp(aSolution, '^', new double[] { 2, 0 })),
                '/', new double[] { 16, 0 });
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

        System.out.println(String.format("%fx^3 + %fx^2 + %fx + %f", first, second, third, fourth));

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
        double[] lambdaMinusTwoTimesInnerParenPlus = calcComp(lambda, '-', twoTimesInnerParenPlus); // Verified!!!!

        // x^4+5x^3-7x^2+2x+9 = 0
        double[] innerSqrt = ComplexService.findSquareRoot(lambdaMinusTwoTimesInnerParenPlus);
        double[] sqrtLambdaPlusInnerSqrt = ComplexService.findSum(sqrtLambda, innerSqrt);//
        double[] outerParenOver2 = calcComp(sqrtLambdaPlusInnerSqrt, '/', new double[] { 2, 0 });
        double[] aOver4 = calcComp(aSolution, '/', new double[] { 4, 0 });

        // (+,-)
        double[] secondSqrtLambdaPlusInnerSqrt = calcComp(sqrtLambda, '-', innerSqrt);//
        double[] secondOuterParenOver2 = calcComp(secondSqrtLambdaPlusInnerSqrt, '/', new double[] { 2, 0 });
        double[] secondAOver4 = calcComp(aSolution, '/', new double[] { 4, 0 });

        double[] innerParenMinus = calcComp(calcComp(p, '+', lambda), '-', qOverSqrtLambda);
        double[] twoTimesInnerParenMinus = calcComp(new double[] { 2, 0 }, '*', innerParenMinus);
        double[] lambdaMinusTwoTimesInnerParenMinus = calcComp(lambda, '-', twoTimesInnerParenMinus);

        innerSqrt = ComplexService.findSquareRoot(lambdaMinusTwoTimesInnerParenMinus);

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
     * @deprecated Convenience method for ComplexService.calculateComplex()
     */
    public static double[] calcComp(double num1[], char sign, double[] num2) {
        return ComplexService.calculateComplex(num1, sign, num2);
    }
}