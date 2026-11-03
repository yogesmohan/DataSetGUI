import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class DataSetGUI extends JFrame implements ActionListener {

    private JTextField bankField;
    private JTextField coinNameField, coinValueField;
    private JTextField studentNameField, studentHeightField;

    private JButton bankAdd, bankCalculate, bankReset;
    private JButton coinAdd, coinCalculate, coinReset;
    private JButton studentAdd, studentCalculate, studentReset;

    private JLabel bankResult, coinResult, studentResult;

    private DataSet bankData = new DataSet();
    private DataSet coinData = new DataSet();
    private DataSet studentData = new DataSet();

    private int bankCount = 0;
    private int coinCount = 0;
    private int studentCount = 0;

    private DecimalFormat df = new DecimalFormat("0.00");

    public DataSetGUI() {

        setTitle("DataSet GUI Application");
        setSize(650, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1, 10, 10));

        // BANK ACCOUNT PANEL
        JPanel bankPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        bankPanel.setBorder(
            BorderFactory.createTitledBorder("Bank Account"));

        bankField = new JTextField(10);
        bankAdd = new JButton("Add");
        bankCalculate = new JButton("Calculate");
        bankReset = new JButton("Reset");
        bankResult = new JLabel("No records added");

        bankPanel.add(new JLabel("Balance (RM):"));
        bankPanel.add(bankField);
        bankPanel.add(bankAdd);
        bankPanel.add(bankCalculate);
        bankPanel.add(bankReset);
        bankPanel.add(bankResult);

        add(bankPanel);

        // COIN PANEL
        JPanel coinPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        coinPanel.setBorder(
            BorderFactory.createTitledBorder("Coin"));

        coinNameField = new JTextField(10);
        coinValueField = new JTextField(10);
        coinAdd = new JButton("Add");
        coinCalculate = new JButton("Calculate");
        coinReset = new JButton("Reset");
        coinResult = new JLabel("No records added");

        coinPanel.add(new JLabel("Coin Name:"));
        coinPanel.add(coinNameField);
        coinPanel.add(new JLabel("Coin Value:"));
        coinPanel.add(coinValueField);
        coinPanel.add(coinAdd);
        coinPanel.add(coinCalculate);
        coinPanel.add(coinReset);
        coinPanel.add(coinResult);

        add(coinPanel);

        // STUDENT PANEL
        JPanel studentPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        studentPanel.setBorder(
            BorderFactory.createTitledBorder("Student"));

        studentNameField = new JTextField(10);
        studentHeightField = new JTextField(10);
        studentAdd = new JButton("Add");
        studentCalculate = new JButton("Calculate");
        studentReset = new JButton("Reset");
        studentResult = new JLabel("No records added");

        studentPanel.add(new JLabel("Student Name:"));
        studentPanel.add(studentNameField);
        studentPanel.add(new JLabel("Height (cm):"));
        studentPanel.add(studentHeightField);
        studentPanel.add(studentAdd);
        studentPanel.add(studentCalculate);
        studentPanel.add(studentReset);
        studentPanel.add(studentResult);

        add(studentPanel);

        // REGISTER EVENT LISTENERS
        bankAdd.addActionListener(this);
        bankCalculate.addActionListener(this);
        bankReset.addActionListener(this);

        coinAdd.addActionListener(this);
        coinCalculate.addActionListener(this);
        coinReset.addActionListener(this);

        studentAdd.addActionListener(this);
        studentCalculate.addActionListener(this);
        studentReset.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();

        try {

            // BANK ACCOUNT EVENTS
            if (source == bankAdd) {
                double balance =
                    Double.parseDouble(bankField.getText());

                if (!Double.isFinite(balance) || balance < 0)
                    throw new IllegalArgumentException();

                bankData.add(new BankAccount(balance));
                bankCount++;
                bankResult.setText("Accounts added: " + bankCount);
                bankField.setText("");
            }

            else if (source == bankCalculate) {
                if (bankCount == 0) {
                    showEmptyMessage();
                    return;
                }

                JOptionPane.showMessageDialog(this,
                    "Average Balance: RM "
                    + df.format(bankData.getAverage())
                    + "\nHighest Balance: RM "
                    + df.format(bankData.getMax().getMeasure())
                    + "\nAccount: "
                    + bankData.getMax().getName());
            }

            else if (source == bankReset) {
                bankData = new DataSet();
                bankCount = 0;
                bankField.setText("");
                bankResult.setText("No records added");
            }

            // COIN EVENTS
            else if (source == coinAdd) {
                String name = coinNameField.getText().trim();
                double value =
                    Double.parseDouble(coinValueField.getText());

                if (name.isEmpty() ||
                    !Double.isFinite(value) || value < 0)
                    throw new IllegalArgumentException();

                coinData.add(new Coin(value, name));
                coinCount++;
                coinResult.setText("Coins added: " + coinCount);
                coinNameField.setText("");
                coinValueField.setText("");
            }

            else if (source == coinCalculate) {
                if (coinCount == 0) {
                    showEmptyMessage();
                    return;
                }

                JOptionPane.showMessageDialog(this,
                    "Average Coin Value: "
                    + df.format(coinData.getAverage())
                    + "\nHighest Coin Value: "
                    + df.format(coinData.getMax().getMeasure())
                    + "\nCoin Name: "
                    + coinData.getMax().getName());
            }

            else if (source == coinReset) {
                coinData = new DataSet();
                coinCount = 0;
                coinNameField.setText("");
                coinValueField.setText("");
                coinResult.setText("No records added");
            }

            // STUDENT EVENTS
            else if (source == studentAdd) {
                String name = studentNameField.getText().trim();
                double height =
                    Double.parseDouble(studentHeightField.getText());

                if (name.isEmpty() ||
                    !Double.isFinite(height) || height < 0)
                    throw new IllegalArgumentException();

                studentData.add(new Student(name, height));
                studentCount++;
                studentResult.setText("Students added: " + studentCount);
                studentNameField.setText("");
                studentHeightField.setText("");
            }

            else if (source == studentCalculate) {
                if (studentCount == 0) {
                    showEmptyMessage();
                    return;
                }

                JOptionPane.showMessageDialog(this,
                    "Average Height: "
                    + df.format(studentData.getAverage())
                    + " cm\nTallest Height: "
                    + df.format(studentData.getMax().getMeasure())
                    + " cm\nTallest Student: "
                    + studentData.getMax().getName());
            }

            else if (source == studentReset) {
                studentData = new DataSet();
                studentCount = 0;
                studentNameField.setText("");
                studentHeightField.setText("");
                studentResult.setText("No records added");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "Please enter a valid number.",
                "Input Error", JOptionPane.ERROR_MESSAGE);

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this,
                "Please enter valid non-negative values and names.",
                "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showEmptyMessage() {
        JOptionPane.showMessageDialog(this,
            "Please add at least one record first.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DataSetGUI gui = new DataSetGUI();
            gui.setVisible(true);
        });
    }
}
