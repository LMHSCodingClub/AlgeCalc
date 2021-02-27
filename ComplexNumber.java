public class ComplexNumber implements OperationalEntity {
    private double realPart;
    private double imaginaryPart = 0;

    ComplexNumber(ComplexNumber n) {
        realPart = n.getRealPart();
        imaginaryPart = n.getImaginaryPart();
    }

    ComplexNumber(double r) {
        realPart = r;
    }

    ComplexNumber(double r, double im) {
        realPart = r;
        imaginaryPart = im;
    }

    public double getRealPart() {
        return realPart;
    }

    public double getImaginaryPart() {
        return imaginaryPart;
    }

    @Override
    public String toString() {
        if (imaginaryPart == 0) {
            return Double.toString(realPart);
        } else if (imaginaryPart < 0) {
            return String.format("%d - %di", realPart, -imaginaryPart);
        } else {
            return String.format("%d + %di", realPart, imaginaryPart);
        }
    }

    public double[] asDouble() {
        return new double[] { realPart, imaginaryPart };
    }

    @Override
    public ComplexNumber plus(OperationalEntity addend) {
        ComplexNumber compAddend = (ComplexNumber) addend;

        return new ComplexNumber(realPart + compAddend.getRealPart(), imaginaryPart + compAddend.getImaginaryPart());
    }

    public ComplexNumber plus(double addend) {
        return plus(new ComplexNumber(addend));
    }

    @Override
    public ComplexNumber minus(OperationalEntity subtrahend) {
        ComplexNumber compSubtrahend = (ComplexNumber) subtrahend;

        return new ComplexNumber(realPart - compSubtrahend.getRealPart(),
                imaginaryPart - compSubtrahend.getImaginaryPart());
    }

    public ComplexNumber minus(double subtrahend) {
        return new ComplexNumber(realPart - subtrahend, imaginaryPart);
    }

    public ComplexNumber times(OperationalEntity factor) {
        ComplexNumber compFactor = (ComplexNumber) factor;
        return new ComplexNumber(realPart * compFactor.getRealPart() - imaginaryPart * compFactor.getImaginaryPart(),
                imaginaryPart * compFactor.getRealPart() + (realPart * compFactor.getImaginaryPart()));
    }

    public ComplexNumber times(double factor) {
        return times(new ComplexNumber(factor));
    }

    @Override
    public ComplexNumber divideBy(OperationalEntity divisor) {
        ComplexNumber compDivisor = (ComplexNumber) divisor;

        double toDivideBy = Math.pow(compDivisor.getRealPart(), 2) + Math.pow(compDivisor.getImaginaryPart(), 2);

        double realResult = realPart * compDivisor.getRealPart() - imaginaryPart * compDivisor.getImaginaryPart();
        realResult /= toDivideBy;

        double imaginaryResult = imaginaryPart * compDivisor.getRealPart()
                + (realPart * compDivisor.getImaginaryPart());
        imaginaryResult /= toDivideBy;

        return new ComplexNumber(realResult, imaginaryResult);
    }

    public ComplexNumber divideBy(double divisor) {
        return divideBy(new ComplexNumber(divisor));
    }

    @Override
    public ComplexNumber pow(OperationalEntity exponent) {
        ComplexNumber compExp = (ComplexNumber) exponent;
        ComplexNumber result = new ComplexNumber(1, 0);

        if (compExp.getRealPart() > 0) {
            for (int i = 0; i < (int) compExp.getRealPart(); i++) {
                result = result.times(this);
            }
        } else // num2[0] < 0
        {
            for (int i = 0; i > compExp.getRealPart(); i--) {
                result = result.divideBy(this);
            }
        }

        return result;
    }

    public ComplexNumber pow(double exponent) {
        return pow(new ComplexNumber(exponent));
    }

    /**
     * @apiNote Formerly {@link ComplexService.findSquareRoot(double[])}
     */
    @Override
    public ComplexNumber sqrt() {
        double[] polar = polarize(asDouble());

        // If it is positive and real, just take Math.sqrt
        if (realPart > 0 && !isImaginary()) {
            return new ComplexNumber(Math.sqrt(realPart));
        }

        // If it is negative and real, take Math.sqrt of abs and make imaginaryPart 1
        else if (realPart < 0 && !isImaginary()) {
            return new ComplexNumber(0, 1).times(Math.sqrt(-realPart));
        }

        // DONE: Anything else, do this:
        return new ComplexNumber(
            // You add a 2pi*k for other roots
            Math.pow(realPart, 1.0 / 2.0) * Math.cos((polar[1] / (2))),
            Math.pow(polar[0], 1.0 / 2.0) * Math.sin((polar[1] / (2))) 
        );
    }

    /**
     * @apiNote Formerly {@link ComplexService.calculateComplex(double[], '3', null)}
     */
    @Override
    public ComplexNumber cbrt() {
        if (isReal()) {
            return new ComplexNumber(Math.pow(Math.abs(realPart), 1.0 / 3.0));
        } else {
            double[] polar = polarize(asDouble());
            // You add a 2pi*k for other roots
            double real = Math.pow(polar[0], 1.0 / 3.0) * Math.cos((polar[1] / (3)));
            double imaginary = Math.pow(polar[0], 1.0 / 3.0) * Math.sin((polar[1] / (3)));
            return new ComplexNumber(real, imaginary);
        }
    }

    boolean isReal() {
        return realPart != 0;
    }

    boolean isImaginary() {
        return imaginaryPart != 0;
    }

    boolean isComplex() {
        return isReal() && isImaginary();
    }

    private double[] polarize(double[] num) {
		double r = Math.sqrt(Math.pow(num[0], 2) + Math.pow(num[1], 2));
		double theta = Math.atan(num[1] / num[0]);
		double[] polar2 = { r, theta };
		return polar2;
	}
}
