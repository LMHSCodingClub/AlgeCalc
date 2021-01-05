import java.util.ArrayList;

/**
 * Puts an equation in standard form
 */
public class Standardizer {
    private BinaryExpression userExpression;

    public Standardizer(BinaryExpression userExp) {
        userExpression = userExp;
    }

    public double[] getNecessaryToTheDeveloperCoefficientsFromExpression() {
        BinaryExpression tempFullExpression = userExpression;
        ArrayList<Double> term = new ArrayList<Double>();

        // 3x + 4 = 5
        // 3x + 4 - 5 = 0
        // 3x - 1 = 0

        while (!(tempFullExpression.leftHandSide instanceof BinaryExpression)) {
            term.add(getCoefficientFromExpression(tempFullExpression.rightHandSide));
            tempFullExpression = (BinaryExpression) tempFullExpression.leftHandSide;
        }

        // 10x^2 + 12x + 4
    }

    /**
     * @param exp The BinaryExpression or LiteralExpression
     * @return The coefficient of the expression
     * @throws IllegalArgumentException
     */
    private double getCoefficientFromExpression(Expression exp) {
        if (exp instanceof BinaryExpression) {
            BinaryExpression binarExp = (BinaryExpression) exp;
            if (binarExp.operator == Operator.ASTERISK) {
                if (binarExp.leftHandSide instanceof LiteralExpression) {
                    return ((LiteralExpression) binarExp.leftHandSide).value;
                }
            } else if (binarExp.operator == Operator.CARET) {
                return getCoefficientFromExpression(binarExp.leftHandSide);
            }
        } else if (exp instanceof LiteralExpression) {
            return ((LiteralExpression) exp).value;
        } else if (exp instanceof IdentifierExpression) {
            return 1;
        }

        throw new IllegalArgumentException();
    }
}
