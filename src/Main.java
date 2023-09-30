import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Main extends JPanel {

    private JTextField targetNumberTextField;
    private JTextField arrayToOrderTextField;
    private JButton startButton;
    private JButton resetButton;
    private JTextField sequentialSearchTextField;
    private JTextField binarySearchTextField;
    private JTextField bubbleSortTextField;
    private JTextField insertionSortTextField;
    private JTextField quickSortTextField;

    public Main() {
        targetNumberTextField = new JTextField(10);
        arrayToOrderTextField = new JTextField(20);
        startButton = new JButton("Start");
        resetButton = new JButton("Reset");
        sequentialSearchTextField = new JTextField(30);
        binarySearchTextField = new JTextField(30);
        bubbleSortTextField = new JTextField(30);
        insertionSortTextField = new JTextField(30);
        quickSortTextField = new JTextField(30);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSearchAndSort();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetFields();
            }
        });

        // Agrega componentes al panel
        add(new JLabel("Array to Order:"));
        add(arrayToOrderTextField);
        add(new JLabel("Target Number:"));
        add(targetNumberTextField);
        add(startButton);
        add(resetButton);
        add(new JLabel("Results:"));
        add(sequentialSearchTextField);
        add(binarySearchTextField);
        add(bubbleSortTextField);
        add(insertionSortTextField);
        add(quickSortTextField);
    }

    private void performSearchAndSort() {
        // Tu lógica para buscar y ordenar aquí...

        // Por ejemplo, aquí establecemos un mensaje de demostración:
        sequentialSearchTextField.setText("Sequential Search Result");
        binarySearchTextField.setText("Binary Search Result");
        bubbleSortTextField.setText("Bubble Sort Result");
        insertionSortTextField.setText("Insertion Sort Result");
        quickSortTextField.setText("Quick Sort Result");
    }

    private void resetFields() {
        targetNumberTextField.setText("");
        arrayToOrderTextField.setText("");
        sequentialSearchTextField.setText("");
        binarySearchTextField.setText("");
        bubbleSortTextField.setText("");
        insertionSortTextField.setText("");
        quickSortTextField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Search and Sort App");
                frame.setSize(800, 600);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                frame.getContentPane().add(new Main());

                frame.setVisible(true);
            }
        });
    }
}
