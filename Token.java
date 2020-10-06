public class Token {
    public TokenKind tokenKind;
    public double doubleValue;
    public char characterValue;
    public Operator operatorValue;

    public Token() {
        tokenKind = TokenKind.END_OF_FILE;
    }

    public Token(double doubleValue) {
        this.doubleValue = doubleValue;
        tokenKind = TokenKind.NUMBER;
    }

    public Token(char characterValue) {
        this.characterValue = characterValue;
        tokenKind = TokenKind.VARIABLE;
    }

    public Token(Operator operatorValue) {
        this.operatorValue = operatorValue;
        tokenKind = TokenKind.OPERATOR;
    }


}