class SyntaxFacts {
    public static int unaryOperatorPrecedence(Operator operator) {
        if (operator == null) {
            return 0;
        }

        switch (operator) {
            case MINUS:
                return 10;
            default:
                return 0;
        }
    }

    public static int binaryOperatorPrecedence(Operator operator) {
        if (operator == null) {
            return 0;
        }
        
        switch (operator) {
            case CARET:
            return 10;
            case ASTERISK:
            case SLASH:
                return 9;
            case PLUS:
            case MINUS:
                return 8;
            case EQUALS:
                return 7;
            default:
                return 0;
        }
    }
}