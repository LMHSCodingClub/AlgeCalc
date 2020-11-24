class Expression {

}

// -5
// -(3 + 2)
//     UnaryExpression
//       Operator: Minus
//       Operand: LiteralExpression
//                  Value: 5
class UnaryExpression extends Expression {
    public Operator operator;
    public Expression operand;

    public UnaryExpression(Operator operator, Expression operand) {
        this.operator = operator;
        this.operand = operand;
    }
    
}

// 3 + 2
class BinaryExpression extends Expression {
    public Operator operator;
    public Expression leftHandSide;
    public Expression rightHandSide;

    public BinaryExpression(Operator operator, Expression lhs, Expression rhs) {
        this.operator = operator;
        this.leftHandSide = lhs;
        this.rightHandSide = rhs;
    }
}

// 5
class LiteralExpression extends Expression {
    public double value;

    public LiteralExpression(double value) {
        this.value = value;
    }
}

class IdentifierExpression extends Expression {
  public String name;

  public IdentifierExpression(String name) {
    this.name = name;
  }
}