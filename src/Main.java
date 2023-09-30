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
        String arrayInput = arrayToOrderTextField.getText();
        String targetNumberInput = targetNumberTextField.getText();

        if (isValidInput(arrayInput) && isValidNumber(targetNumberInput)) {
            int targetNumber = Integer.parseInt(targetNumberInput);
            String[] arrayStrings = arrayInput.split(",");
            int[] arrayToOrder = new int[arrayStrings.length];

            for (int i = 0; i < arrayStrings.length; i++) {
                arrayToOrder[i] = Integer.parseInt(arrayStrings[i]);
            }

            performAlgorithmsInBackground(arrayToOrder, targetNumber);
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, ingresa un arreglo válido y un número válido.");
        }
    }

    private void performAlgorithmsInBackground(final int[] arrayToOrder, final int targetNumber) {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            long startTime;

            @Override
            protected Void doInBackground() throws Exception {
                // Sequential Search
                startTime = System.nanoTime();
                int sequentialSearchResult = sequentialSearch(arrayToOrder, targetNumber);
                String sequentialSearchTime = formatTime(System.nanoTime() - startTime);

                // Binary Search
                startTime = System.nanoTime();
                int binarySearchResult = Arrays.binarySearch(arrayToOrder, targetNumber);
                String binarySearchTime = formatTime(System.nanoTime() - startTime);

                // Bubble Sort
                startTime = System.nanoTime();
                bubbleSort(Arrays.copyOf(arrayToOrder, arrayToOrder.length));
                String bubbleSortTime = formatTime(System.nanoTime() - startTime);

                // Insertion Sort
                startTime = System.nanoTime();
                insertionSort(Arrays.copyOf(arrayToOrder, arrayToOrder.length));
                String insertionSortTime = formatTime(System.nanoTime() - startTime);

                // Quick Sort
                startTime = System.nanoTime();
                quickSort(Arrays.copyOf(arrayToOrder, arrayToOrder.length));
                String quickSortTime = formatTime(System.nanoTime() - startTime);
                System.out.println(quickSortTime);
                sequentialSearchTextField.setText("Sequential Search: " + (sequentialSearchResult >= 0 ? "Found at index " + sequentialSearchResult : "Not found") + " - Time: " + sequentialSearchTime);
                binarySearchTextField.setText("Binary Search: " + (binarySearchResult >= 0 ? "Found at index " + binarySearchResult : "Not found") + " - Time: " + binarySearchTime);
                bubbleSortTextField.setText("Time: " + bubbleSortTime);
                insertionSortTextField.setText("Time: " + insertionSortTime);
                quickSortTextField.setText("Time: " + quickSortTime);

                return null;
            }
        };

        worker.execute();
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

    public static String formatTime(long nanoseconds) {
        long milliseconds = nanoseconds / 1000000;
        long remainingNanoseconds = nanoseconds % 1000000;
        if (milliseconds >= 60000) {
            long minutes = milliseconds / 60000;
            milliseconds %= 60000;
            return minutes + " minutes " + milliseconds + " milliseconds " + remainingNanoseconds + " nanoseconds";
        } else if (milliseconds >= 1000) {
            long seconds = milliseconds / 1000;
            milliseconds %= 1000;
            return seconds + " seconds " + milliseconds + " milliseconds " + remainingNanoseconds + " nanoseconds";
        } else {
            return milliseconds + " milliseconds " + remainingNanoseconds + " nanoseconds";
        }
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
