public class Token {
    public TokenKind tokenKind;
    public double doubleValue;
    public String identifierValue;
    public Operator operatorValue;

    public Token() {
        tokenKind = TokenKind.END_OF_FILE;
    }

    public Token(double doubleValue) {
        this.doubleValue = doubleValue;
        tokenKind = TokenKind.NUMBER;
    }

    public Token(String identifierValue) {
        this.identifierValue = identifierValue;
        tokenKind = TokenKind.IDENTIFIER;
    }

    public Token(Operator operatorValue) {
        this.operatorValue = operatorValue;
        tokenKind = TokenKind.OPERATOR;
    }


}