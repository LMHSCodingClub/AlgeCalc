import java.util.ArrayList;

/**
 * Converts parsed {@link Expression}s into formula-pluggable values
 */
public class Standardizer {
    private Expression expression;

    /**
     * Constructor for Standardizer
     * @param binExp The binary expression (everything on the left hand side of the equal sign)
     */
    public Standardizer(BinaryExpression binExp) {
        expression = binExp;
        expression = ((BinaryExpression) binExp).leftHandSide;
    }
    
// 5x^3 + 4x^2 + 3x - 15 = 0


    //                    =
    //                 /     \
    //                -       0
    //              /  \
    //            +    15
    //           / \
    //          +   3x
    //         / \
    //      5x^3  4x^2

    public double[] getCoefficientsFromExpression() {
        ArrayList<Double> term = new ArrayList<Double>();
        
        while (expression instanceof BinaryExpression) {
            if (
                ((BinaryExpression) expression).operator != Operator.PLUS && 
                ((BinaryExpression) expression).operator != Operator.MINUS
            ) {
                break;
            }

            int negative = ((BinaryExpression) expression).operator == Operator.MINUS ? -1 : 1;
            term.add(negative * getCoefficientFromExpression(((BinaryExpression) expression).rightHandSide));
            expression = ((BinaryExpression) expression).leftHandSide;
        }

        term.add(getCoefficientFromExpression(expression));

        return reverseArray(term);
    }

    double[] reverseArray(ArrayList<Double> term) {
        double[] reversedTerm = new double[term.size()];

        // LENGTH 2
        // 0: reversedTerm[2 - 0 - 1 = 1] = term.get(0)
        // 1: reversedTerm[2 - 1 - 1 = 0] = term.get(1)
        for (int i = 0; i < reversedTerm.length; i++) {
            reversedTerm[i] = term.get(reversedTerm.length - i - 1);
        }

        return reversedTerm;
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
        } else if (exp instanceof UnaryExpression) {
            UnaryExpression unExp = (UnaryExpression) exp;
            if (unExp.operator == Operator.MINUS) {
                return -1 * getCoefficientFromExpression(unExp.operand);
            }
        } else if (exp instanceof LiteralExpression) {
            return ((LiteralExpression) exp).value;
        } else if (exp instanceof IdentifierExpression) {
            return 1;
        }

        throw new IllegalArgumentException();
    }
}
