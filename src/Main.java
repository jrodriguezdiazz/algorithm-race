import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
            @Override
            protected Void doInBackground() throws Exception {
                // Sequential Search
                SequentialSearch sequentialSearch = new SequentialSearch(arrayToOrder, targetNumber);
                sequentialSearch.start();

                // Binary Search
                BinarySearch binarySearch = new BinarySearch(arrayToOrder, targetNumber);
                binarySearch.start();

                // Bubble Sort
                BubbleSort bubbleSort = new BubbleSort(arrayToOrder);
                bubbleSort.start();

                // Insertion Sort
                InsertionSort insertionSort = new InsertionSort(arrayToOrder);
                insertionSort.start();

                // Quick Sort
                QuickSort quickSort = new QuickSort(arrayToOrder);
                quickSort.start();

                // Wait for all of the algorithm threads to finish executing
                sequentialSearch.join();
                binarySearch.join();
                bubbleSort.join();
                insertionSort.join();
                quickSort.join();

                // Update the UI
                sequentialSearchTextField.setText(String.format("Sequential Search: %s - %s", (sequentialSearch.getResult() >= 0 ? "Found at index " + sequentialSearch.getResult() : "Not found"), formatTime(sequentialSearch.getTime())));
                binarySearchTextField.setText(String.format("Binary Search: %s - %s", (binarySearch.getResult() >= 0 ? "Found at index " + binarySearch.getResult() : "Not found"), formatTime(binarySearch.getTime())));
                bubbleSortTextField.setText(String.format("Bubble Sort: %s", formatTime(bubbleSort.getTime())));
                insertionSortTextField.setText(String.format("Insertion Sort: %s", formatTime(insertionSort.getTime())));
                quickSortTextField.setText(String.format("Quick Sort: %s", formatTime(quickSort.getTime())));

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

    public class SequentialSearch extends Thread {

        private final int[] array;
        private final int target;
        private volatile int result;
        private volatile long time;

        public SequentialSearch(int[] array, int target) {
            this.array = array;
            this.target = target;
        }

        @Override
        public void run() {
            long startTime = System.nanoTime();
            for (int i = 0; i < array.length; i++) {
                if (array[i] == target) {
                    result = i;
                    break;
                }
            }

            if (result == -1) {
                result = -1;
            }
            time = System.nanoTime() - startTime;
        }

        public int getResult() {
            return result;
        }

        public long getTime() {
            return time;
        }
    }

    public class BinarySearch extends Thread {

        private final int[] array;
        private final int target;
        private volatile int result;
        private volatile long time;

        public BinarySearch(int[] array, int target) {
            this.array = array;
            this.target = target;
        }

        @Override
        public void run() {
            long startTime = System.nanoTime();
            int low = 0;
            int high = array.length - 1;

            while (low <= high) {
                int mid = (low + high) / 2;

                if (array[mid] == target) {
                    result = mid;
                    break;
                } else if (array[mid] < target) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            if (result == -1) {
                result = -1;
            }

            time = System.nanoTime() - startTime;
        }

        public int getResult() {
            return result;
        }

        public long getTime() {
            return time;
        }
    }


    public class BubbleSort extends Thread {

        private final int[] array;
        private volatile long time;

        public BubbleSort(int[] array) {
            this.array = array;
        }

        @Override
        public void run() {
            long startTime = System.nanoTime();

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

            time = System.nanoTime() - startTime;
        }

        public long getTime() {
            return time;
        }
    }


    public class InsertionSort extends Thread {

        private final int[] array;
        private volatile long time;

        public InsertionSort(int[] array) {
            this.array = array;
        }

        @Override
        public void run() {
            long startTime = System.nanoTime();

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

            time = System.nanoTime() - startTime;
        }

        public long getTime() {
            return time;
        }
    }


    public class QuickSort extends Thread {

        private final int[] array;
        private volatile long time;

        public QuickSort(int[] array) {
            this.array = array;
        }

        @Override
        public void run() {
            long startTime = System.nanoTime();

            quickSortHelper(array, 0, array.length - 1);

            time = System.nanoTime() - startTime;
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

        public long getTime() {
            return time;
        }
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
