// Operators should be processed in this priority:
// MULTIPLY/DIVIDE
// ADD/SUBTRACT
// EQUALS

//      +
//     / \
//   3x   *
//       / \
//      4   5

// 3x + 4 * 5 = 6 + 7 * 8

// (-3 + 4)
// UnaryExpression
//   Operator: Parenthetical
//   Value: BinaryExpression
//             Operator: Add
//             lhs = 3, rhs = 4

public class Parser {
    Lexer lexer;

    Token current;
    Token peek;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
        current = lexer.lex();
    }

    Token nextToken() {
        Token current = this.current;
        if (peek != null) {
            this.current = peek;
            peek = null;
        } else {
            this.current = lexer.lex();
        }

        return current;
    }

    Token peek() {
        if (peek == null) {
            peek = lexer.lex();
        }

        return peek;
    }


    public Expression parseExpression() {
        ExpressionSyntax left;

        int unaryOperatorPrecedence = SyntaxFacts.unaryOperatorPrecedence(current.operatorValue);
        if (unaryOperatorPrecedence != 0) {
            Token operator = nextToken();

            // -(3 + 2)
            Expression operand = parseExpression();
            left = new UnaryExpression(operator.operatorValue, operand);
        } else {
            left = parsePrimaryExpression();
        }
    }

    public Expression parsePrimaryExpression() {
        if (current.tokenKind == TokenKind.NUMBER) {
            return new LiteralExpression(current.doubleValue);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
