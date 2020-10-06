public class Lexer {
    String userInput;
    int index;

    public Lexer(String userInput) {
        this.userInput = userInput;
        index = 0;
    }

    boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }


    public Token lex() {
        if (index >= userInput.length()) {
            // we have read all of the characters.
            // return "end of file," since there is nothing else to read
            return new Token();
        }

        char character = userInput.charAt(index);

        /// 345 + 6

        if (character == ' ') {
            index++;
            return lex();
        }

        if (character >= 'a' && character <= 'z') {
            Token token = new Token(character);
            index++;
            return token;
        }

        // 4500x + ...

        if (isNumber(character)) {
            int startIndex = index;

            while (true) {
                index++;
                if (index >= userInput.length() || !isNumber(userInput.charAt(index))) {
                    break;
                }
            }

            String numberString = userInput.substring(startIndex, index);
            double number = Double.parseDouble(numberString);
            Token t = new Token(number);
            return t;
        }

        if (character == '+') {
            index++;
            return new Token(Operator.ADD);
        }

        if (character == '-') {
            index++;
            return new Token(Operator.SUBTRACT);
        }

        if (character == '*') {
            index++;
            return new Token(Operator.MULTIPLY);
        }

        if (character == '/') {
            index++;
            return new Token(Operator.DIVIDE);
        }

        return new Token();
    }
}
