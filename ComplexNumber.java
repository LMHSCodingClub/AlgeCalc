public class ComplexNumber implements OperationalEntity {
    private double realPart;
    private double imaginaryPart;

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
    public OperationalEntity plus(OperationalEntity addend) {
        ComplexNumber compAddend = (ComplexNumber) addend;

        return new ComplexNumber(realPart + compAddend.getRealPart(), imaginaryPart + compAddend.getImaginaryPart());
    }

    @Override
    public OperationalEntity minus(OperationalEntity subtrahend) {
        ComplexNumber compSubtrahend = (ComplexNumber) subtrahend;

        return new ComplexNumber(realPart - compSubtrahend.getRealPart(),
                imaginaryPart - compSubtrahend.getImaginaryPart());
    }

    @Override
    public OperationalEntity times(OperationalEntity factor) {
        ComplexNumber compFactor = (ComplexNumber) factor;
        return new ComplexNumber(
            realPart * compFactor.getRealPart() - imaginaryPart * compFactor.getImaginaryPart(),
            imaginaryPart * compFactor.getRealPart() + (realPart * compFactor.getImaginaryPart()) 
        );
    }

    @Override
    public OperationalEntity dividedBy(OperationalEntity divisor) {
        ComplexNumber compDivisor = (ComplexNumber) divisor;

        double toDivideBy = Math.pow(compDivisor.getRealPart(), 2) + Math.pow(compDivisor.getImaginaryPart(), 2);

        double realResult = realPart * compDivisor.getRealPart() - imaginaryPart * compDivisor.getImaginaryPart();
        realResult /= toDivideBy;
        
        double imaginaryResult = imaginaryPart * compDivisor.getRealPart() + (realPart * compDivisor.getImaginaryPart());
        imaginaryResult /= toDivideBy;

        return new ComplexNumber(realResult, imaginaryResult);
    }

}
