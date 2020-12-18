import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class UserInterface extends JFrame {
  public UserInterface() {
    BoxLayout layout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
    setLayout(layout);

    JLabel prompt = new JLabel("Enter an equation for AlgeCalc to solve");
    prompt.setFont(new Font("Arial", Font.PLAIN, 20));

    JPanel solutionLine = new JPanel();
    BoxLayout solutionLineLayout = new BoxLayout(solutionLine, BoxLayout.X_AXIS);
    solutionLine.setLayout(solutionLineLayout);

    JButton b = new JButton("Calculate");
    
    JTextField equation = new JTextField(30);
    equation.setBounds(50, 50, 50, 50);
    equation.setFont(new Font("Arial", Font.PLAIN, 20));
    equation.setMaximumSize(new Dimension(Integer.MAX_VALUE,b.getMinimumSize().height));
    
    // Display the solution line panel
    solutionLine.add(equation);
    solutionLine.add(b);
    
    JLabel solutionText = new JLabel("Solution: ");
    solutionText.setFont(new Font("Arial", Font.PLAIN, 20));

    JLabel solution = new JLabel("");
    solution.setFont(new Font("Arial", Font.PLAIN, 20));

    b.setBounds(80, 100, 40, 40);

    b.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("Calculating...");
        System.out.println(equation.getText());
        solution.setText("123");
      }
    });

    add(prompt);
    add(solutionLine);
    add(solutionText); 
    
    setSize(800, 500);
    setVisible(true);
  }
}
//https://codehs.com/sandbox/dvolchonok/Hello_Java 