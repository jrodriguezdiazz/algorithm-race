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
        setUI();
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
    }

    private void performSearchAndSort() {
        sequentialSearchTextField.setText("Sequential Search Result");
        binarySearchTextField.setText("Binary Search Result");
        bubbleSortTextField.setText("Bubble Sort Result");
        insertionSortTextField.setText("Insertion Sort Result");
        quickSortTextField.setText("Quick Sort Result");
    }

    private void setUI() {
        targetNumberTextField = new JTextField(10);
        arrayToOrderTextField = new JTextField(20);
        startButton = new JButton("Start");
        resetButton = new JButton("Reset");
        sequentialSearchTextField = new JTextField(30);
        binarySearchTextField = new JTextField(30);
        bubbleSortTextField = new JTextField(30);
        insertionSortTextField = new JTextField(30);
        quickSortTextField = new JTextField(30);

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

    private void resetFields() {
        targetNumberTextField.setText("");
        arrayToOrderTextField.setText("");
        sequentialSearchTextField.setText("");
        binarySearchTextField.setText("");
        bubbleSortTextField.setText("");
        insertionSortTextField.setText("");
        quickSortTextField.setText("");
    }

    private boolean isValidInput(String input) {
        return !input.isEmpty() && input.matches("^[0-9,]+$");
    }

    private boolean isValidNumber(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private int sequentialSearch(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1; // No encontrado
    }

    private int[] bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // Intercambiar array[j] y array[j+1]
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }

    private int[] insertionSort(int[] array) {
        int n = array.length;
        for (int i = 1; i < n; i++) {
            int key = array[i];
            int j = i - 1;

            // Mover elementos del array[0..i-1] que son mayores que key
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
        return array;
    }

    private int[] quickSort(int[] array) {
        quickSortHelper(array, 0, array.length - 1);
        return array;
    }

    private void quickSortHelper(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);

            quickSortHelper(array, low, pi - 1);
            quickSortHelper(array, pi + 1, high);
        }
    }

    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;

                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
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
