import javax.swing.*;
import java.awt.*;

public class Calculator {
    private JFrame frame;
    private JTextField field1, field2;
    private JComboBox<String> operationBox;
    private JLabel resultLabel;
    private JTextArea historyArea;

    private Calculate calc = new Calculate();
    private History history = new History();

    public Calculator() {
        setupFrame();
        addInputFields();
        addOperationSelector();
        addResultPanel();
        addHistoryButton();
        addHistoryArea();
        addExitButton();
        frame.setVisible(true);
    }

    private void setupFrame() {
        frame = new JFrame("Swing Calculator");
        frame.setSize(400, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(7, 1));
    }

    private void addInputFields() {
        JPanel inputPanel = new JPanel(new GridLayout(2, 2));
        inputPanel.add(new JLabel("Number 1:"));
        field1 = new JTextField();
        inputPanel.add(field1);
        inputPanel.add(new JLabel("Number 2:"));
        field2 = new JTextField();
        inputPanel.add(field2);
        frame.add(inputPanel);
    }

    private void addOperationSelector() {
        JPanel opPanel = new JPanel();
        String[] ops = {"+", "-", "*", "/"};
        operationBox = new JComboBox<>(ops);
        opPanel.add(new JLabel("Operation:"));
        opPanel.add(operationBox);
        frame.add(opPanel);
    }

    private void addResultPanel() {
        JPanel resultPanel = new JPanel();
        JButton calcButton = new JButton("Calculate");
        resultLabel = new JLabel("Result: ");
        resultPanel.add(calcButton);
        resultPanel.add(resultLabel);
        frame.add(resultPanel);

        calcButton.addActionListener(e -> performCalculation());
    }

    private void addHistoryButton() {
        JButton historyButton = new JButton("Show History");
        frame.add(historyButton);
        historyButton.addActionListener(e -> historyArea.setText(history.getAll()));
    }

    private void addHistoryArea() {
        historyArea = new JTextArea();
        historyArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(historyArea);
        frame.add(scrollPane);
    }

    private void addExitButton() {
        JButton exitButton = new JButton("Exit");
        frame.add(exitButton);
        exitButton.addActionListener(e -> System.exit(0));
    }

    private void performCalculation() {
        try {
            double num1 = Double.parseDouble(field1.getText());
            double num2 = Double.parseDouble(field2.getText());
            String op = (String) operationBox.getSelectedItem();

            double result = switch (op) {
                case "+" -> calc.add(num1, num2);
                case "-" -> calc.subtract(num1, num2);
                case "*" -> calc.multiply(num1, num2);
                case "/" -> calc.divide(num1, num2);
                default -> 0;
            };

            String record = num1 + " " + op + " " + num2 + " = " + result;
            resultLabel.setText("Result: " + result);
            history.add(record);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Please enter valid numbers.");
        } catch (ArithmeticException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
