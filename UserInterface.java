import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.Arrays;

class UserInterface extends JFrame {
  private static final long serialVersionUID = 1L;

  public static void main(String[] args) {
    new UserInterface();
  }

  public UserInterface() {
    Font mainFont = new Font("Arial", Font.PLAIN, 20);

    BoxLayout layout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
    setLayout(layout);

    Insets margins = new Insets(10, 20, 10, 20);

    JLabel prompt = new JLabel("Enter an equation for AlgeCalc to solve");

    prompt.setFont(mainFont);

    JPanel solutionLine = new JPanel();

    BoxLayout solutionLineLayout = new BoxLayout(solutionLine, BoxLayout.X_AXIS);
    solutionLine.setLayout(solutionLineLayout);

    JButton b = new JButton("Calculate");

    DefaultListModel<String> listModel = new DefaultListModel<>();
    JList<String> solutionList = new JList<>(listModel) {
      private static final long serialVersionUID = -270397778461875894L;

      public Insets getInsets() {
        return super.getInsets(margins);
      }
    };
    solutionList.setFont(mainFont);
    

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

      if (!(binExp.operator == Operator.EQUALS)) {
        JOptionPane.showMessageDialog(UserInterface.this, "You must enter an equation!");
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
        System.out.println("Coefficients: " + Arrays.toString(coefficients));
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

      listModel.clear();

      for (double[] solution : solutions) {
        // Round each part of each solution to the nearest thousandth
        DecimalFormat f = new DecimalFormat("##.###");

        String finalSolutionText = "";

        if (solution[1] == 0) {
          finalSolutionText = f.format(solution[0]);
        } else {
          finalSolutionText = f.format(solution[0]) + " + " + f.format(solution[1]) + "i, \n";
        }

        listModel.addElement("x = " + finalSolutionText);

        // TODO: Figure out what we want to do with this
        /*
        int i = 0;
        do {
          // If the solution is a duplicate
          if (!solutions[i].equals(solution)) {
            System.out.println("Solution: " + finalSolutionText);
          }
          i++;
        } while (i < solutions.length);*/
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