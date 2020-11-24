import java.util.Scanner;

class AlgebraicCalculator {
  static UserInterface gInterface;
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    String input = scan.nextLine();
    Lexer lexer = new Lexer(input);
    Parser parser = new Parser(lexer);

    Expression expr = parser.parseExpression(0);
    gInterface = new UserInterface();
  }

  static void printObject(Object o) {
    if (o instanceof Token) {
      Token t = (Token) o;
      switch (t.tokenKind) {
        case NUMBER:
          System.out.print(t.numberValue);
          break;
        case OPERATOR:
          System.out.print(t.operatorValue);
          break;
        case IDENTIFIER:
          System.out.print(t.identifierValue);
          break;
        case END_OF_FILE:
          System.out.print("EOF");
          break;
      }
    }
    if (o instanceof Expression) {
      /* */
    }
  }
}