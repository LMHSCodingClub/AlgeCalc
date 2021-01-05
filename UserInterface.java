import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

class UserInterface extends JFrame {
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

    JLabel solution = new JLabel("");
    solution.setFont(new Font("Arial", Font.PLAIN, 20));

    b.setBounds(80, 100, 40, 40);

    // Click listener for the submit button
    b.addActionListener((e) -> {
        Lexer lexer = new Lexer(equation.getText());
        Parser parser = new Parser(lexer);

        Expression expr = parser.parseExpression(0);

        // Exit if the expression isn't binary
        if (!(expr instanceof BinaryExpression)) {
          JOptionPane.showMessageDialog(UserInterface.this, "Must be a binary expression!");
          return;
        }

        BinaryExpression binExp = (BinaryExpression) expr;

        Expression left = binExp.leftHandSide;
        Expression right = binExp.rightHandSide;

        if (!(right instanceof LiteralExpression)) {
          JOptionPane.showMessageDialog(UserInterface.this, "The right hand side must be a literal expression!");
          return;
        } 

        

        solutionText.setText();
      }
    });

    add(prompt);
    add(solutionLine);
    add(solutionText);

    setSize(800, 500);

    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
  }
}