import java.util.Scanner;

class AlgebraicCalculator {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    String input = scan.nextLine();
    Lexer lexer = new Lexer(input);

    Token t;
    try {
      while ((t = lexer.lex()).tokenKind != TokenKind.END_OF_FILE) {
        switch (t.tokenKind) {
        case OPERATOR:
          System.out.println("Operator " + t.operatorValue.toString());
          break;
        case IDENTIFIER:
          System.out.println("Identifer " + t.identifierValue);
          break;
        case NUMBER:
          System.out.println("Number " + t.doubleValue);
          break;
        case END_OF_FILE:
          System.out.println("End Of File");
          break;

        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("An error occurred and the result could not be calculated");
    }
  }
}