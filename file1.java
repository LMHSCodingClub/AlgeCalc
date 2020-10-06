import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        Lexer lexer = new Lexer(input);

        Token t;
        while ((t = lexer.lex()).tokenKind != TokenKind.END_OF_FILE) {
            switch (t.tokenKind) {
                case OPERATOR:
                    System.out.println("Operator " + t.operatorValue.toString());
                    break;
                case VARIABLE:
                    System.out.println("Variable " + t.characterValue);
                    break;
                case NUMBER:
                    System.out.println("Number " + t.doubleValue);
                     break;
                case END_OF_FILE:
                    System.out.println("End Of File");
                break;
                
                }
        }
    }
}