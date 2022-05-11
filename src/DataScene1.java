import javax.swing.*;

public class DataScene1 {
    private JPanel Main;
    private JCheckBox IDCheckBox;
    private JCheckBox countryCodeCheckBox;
    private JCheckBox districtCheckBox;
    private JCheckBox nameCheckBox;
    private JCheckBox populationCheckBox;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JPasswordField passwordField1;
    private JTextField textField4;

    public static void main(String[] args) {
        JFrame frame = new JFrame("DataScene1");
        frame.setContentPane(new DataScene1().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
