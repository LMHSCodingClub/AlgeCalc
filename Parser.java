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


    // 3 + 4
    public Expression parseExpression(int parentPrecedence) {
        // This function always tries to parse out a binary expression.
        // If we realize that there isn't actually a binary expression
        // to parse, however, we will realize that & return the proper
        // thing accordingly.

        Expression left;

        // Check if we are parsing a unary operator. If we are, we want to consume it
        // and apply it to the next expression.

        int unaryOperatorPrecedence = SyntaxFacts.unaryOperatorPrecedence(current.operatorValue);
        if (unaryOperatorPrecedence != 0) {
            Token operator = nextToken();

            Expression operand = parseExpression(0);
            left = new UnaryExpression(operator.operatorValue, operand);
        } else {
            left = parsePrimaryExpression();
        }

        // 3x + 1

        while (true) {
            int precedence = SyntaxFacts.binaryOperatorPrecedence(current.operatorValue);

            // We may have gotten here, where `current` isn't actually a binary operator (e.g. 'sin(-5)')
            // In that case, we should bail from this function, and simply return the expression we have read.
        
            // In the case that we are parsing another operator (e.g. '2 * 3 + 4'), we understandbly must check for precedence.
            // We are parsing left to right, so if we are already in a nested expression, then we need to decide where this newly parsed term belongs -
            // to the parent expression (2 * 3) or this one (3 + 4).
            // So, if the parent precedence is equal or higher, we will also bail and return the term we have got.
            
            if (precedence == 0 || precedence <= parentPrecedence) {
                break;
            }

            Token operatorToken = nextToken();
            Expression right = parseExpression(precedence);
            left = new BinaryExpression(operatorToken.operatorValue, left, right);
        }

        return left;
    }

    public Expression parsePrimaryExpression() {
        if (current.tokenKind == TokenKind.NUMBER) {
            LiteralExpression expr = new LiteralExpression(nextToken().doubleValue);

            return expr;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
