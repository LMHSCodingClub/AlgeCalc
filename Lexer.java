// For the input 3x + 4 = 5
//
// Lexer output:
// [3] [x] [PLUS] [4] [EQUALS] [5]
// 
// Parser output:
//        =
//       / \
//      /   \
//     +     5
//    / \
//   *   4
//  / \
// 3   x

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

    boolean isLetter(char c) {
        return c >= 'a' && c <= 'z';
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

        if (isLetter(character)) {
            int startIndex = index;

            while (true) {
                index++;
                if (index >= userInput.length() || !isLetter(userInput.charAt(index))) {
                    break;
                }
            }

            String identifier = userInput.substring(startIndex, index);
            Token token = new Token(identifier);
            index++;
            return token;
        }

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
            return new Token(Operator.PLUS);
        }

        if (character == '-') {
            index++;
            return new Token(Operator.MINUS);
        }

        if (character == '*') {
            index++;
            return new Token(Operator.ASTERISK);
        }

        if (character == '/') {
            index++;
            return new Token(Operator.SLASH);
        }

        if (character == '(') {
            index++;
            return new Token(Operator.OPEN_PARENTHESIS);
        }

        if (character == ')') {
            index++;
            return new Token(Operator.CLOSE_PARENTHESIS);
        }

        if (character == '^') {
            index++;
            return new Token(Operator.CARET);
        }

        if (character == '=') {
            index++;
            return new Token(Operator.EQUALS);
        }

        return new Token();
    }
}
