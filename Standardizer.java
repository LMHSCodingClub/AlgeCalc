import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
    
    public double[] getCoefficientsFromExpression() {
        ArrayList<Double> term = new ArrayList<Double>();
        
        while (expression instanceof BinaryExpression) {
            if (
                ((BinaryExpression) expression).operator != Operator.PLUS && 
                ((BinaryExpression) expression).operator != Operator.MINUS
            ) {
                break;
            }

            term.add(getCoefficientFromExpression(((BinaryExpression) expression).rightHandSide));
            expression = ((BinaryExpression) expression).leftHandSide;
        }

        term.add(getCoefficientFromExpression(expression));


        return StreamSupport
            .stream(Spliterators.spliteratorUnknownSize(
                term
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
