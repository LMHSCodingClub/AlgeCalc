import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Puts an equation in standard form
 */
public class Standardizer {
    private BinaryExpression userEquation;

    public Standardizer(BinaryExpression userEq) {
        userEquation = userEq;
    }

    public double[] getCoefficientsFromExpression() {
        Expression expression = userEquation.leftHandSide;
        ArrayList<Double> term = new ArrayList<Double>();

        // 3x + 4 = 5
        // 3x + 4 - 5 = 0
        // 3x - 1 = 0

        // 3
        while (expression instanceof BinaryExpression) {
            BinaryExpression binaryExpr = ((BinaryExpression) expression); 

            if (!(binaryExpr.leftHandSide instanceof BinaryExpression)) {
                break;
            }

            if (binaryExpr.operator != Operator.PLUS && binaryExpr.operator != Operator.MINUS) {
                break;
            }

            term.add(getCoefficientFromExpression(binaryExpr.rightHandSide));
            expression = binaryExpr.leftHandSide;
        }

        term.add(getCoefficientFromExpression(expression));


        return StreamSupport
            .stream(Spliterators.spliteratorUnknownSize(term
                .stream()
                .collect(Collectors.toCollection(LinkedList::new))
                .descendingIterator(), Spliterator.ORDERED), false
            )
            .mapToDouble(Double::doubleValue).toArray();
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
