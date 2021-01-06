import javax.swing.*;
import java.awt.*;

class UserInterface extends JFrame {
  private static final long serialVersionUID = 1L;

  public static void main(String[] args) {
    new UserInterface();
  }

  public UserInterface() {
    BoxLayout layout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
    setLayout(layout);

    JLabel prompt = new JLabel("Enter an equation for AlgeCalc to solve");
    prompt.setFont(new Font("Arial", Font.PLAIN, 20));

    JPanel solutionLine = new JPanel();
    BoxLayout solutionLineLayout = new BoxLayout(solutionLine, BoxLayout.X_AXIS);
    solutionLine.setLayout(solutionLineLayout);

    JButton b = new JButton("Calculate");

    ListModel listModel = new DefaultListModel();
    JList solutionList = new JList(listModel);


    // The text field
    JTextField equation = new JTextField(30);
    equation.setBounds(50, 50, 50, 50);
    equation.setFont(new Font("Arial", Font.PLAIN, 20));
    equation.setMaximumSize(new Dimension(Integer.MAX_VALUE, b.getMinimumSize().height));

    // Display the solution line panel
    solutionLine.add(equation);
    solutionLine.add(b);

    JLabel solutionText = new JLabel("Solution: ");
    solutionText.setFont(new Font("Arial", Font.PLAIN, 20));

    b.setBounds(80, 100, 40, 40);

    // Click listener for the submit button
    b.addActionListener((e) -> {
      Lexer lexer = new Lexer(equation.getText());
      Parser parser = new Parser(lexer);

      Expression expr = parser.parseExpression();

      // Exit if the expression isn't binary
      if (!(expr instanceof BinaryExpression)) {
        JOptionPane.showMessageDialog(UserInterface.this, "Must be a binary expression!");
        return;
      }

      BinaryExpression binExp = (BinaryExpression) expr;
      Expression right = binExp.rightHandSide;

      if (!(right instanceof LiteralExpression)) {
        JOptionPane.showMessageDialog(UserInterface.this, "The right hand side must be a literal expression!");
        return;
      }

      Standardizer standardizer = new Standardizer(binExp);
      double[] coefficients = standardizer.getCoefficientsFromExpression();
      double[][] solutions = null;

      switch (coefficients.length) {
        case 2:
          solutions = new double[][] { Monovariable.useLinearFormula(coefficients[0], coefficients[1]) };
          break;
        case 3:
          solutions = Monovariable.useQuadraticFormula(coefficients[0], coefficients[1], coefficients[2]);
          break;
        case 4:
          solutions = Monovariable.useCubicFormula(coefficients[0], coefficients[1], coefficients[2], coefficients[3]);
          break;
        case 5:
          solutions = Monovariable.useQuarticFormula(coefficients[0], coefficients[1], coefficients[2], coefficients[3],
              coefficients[4]);
          break;
      }
      
      for (double[] solution : solutions) {
        solution[0] = Math.round(solution[0] * 1000) / 1000;
        solution[1] = Math.round(solution[1] * 1000) / 1000;
        String finalSolutionText = solution[0] + " + " + solution[1] + "i, \n";
        solutionList.add(new JLabel(finalSolutionText));
        System.out.println("Solution: " + finalSolutionText);
      }
    });

    add(prompt);
    add(solutionLine);
    add(solutionList);

    setSize(800, 500);

    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
  }
}