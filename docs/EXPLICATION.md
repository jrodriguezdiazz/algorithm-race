# Code Explanation

Algorithm Race is structured with a main class `Main` that extends `JPanel` and contains inner classes for each algorithm. Below is a detailed explanation of the code structure and functionality.

## Main Class

### Initialization

- The `Main` class initializes the graphical user interface components, including text fields for user inputs, buttons for starting and resetting, and text fields for displaying the results of each algorithm.

```java
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
```

### User Interaction
The performSearchAndSort method is triggered when the user clicks the "Start" button. It validates the user inputs and converts them to the appropriate data types before executing the algorithms.

The resetFields method is triggered when the user clicks the "Reset" button. It clears all the input and result fields.

## Algorithm Execution

The perform `AlgorithmsInBackground` method executes each algorithm in its own thread using SwingWorker to avoid blocking the user interface. After all algorithms have completed execution, it updates the result fields with the outcomes and the execution times.
Algorithm Classes
Each algorithm is implemented in its own inner class extending Thread. Below are the common patterns in each algorithm class:

### Initialization
Each class is initialized with the necessary parameters, such as the array to be sorted or searched and the target number to be found.

### Execution
The run method contains the implementation of the algorithm and measures the time taken for execution.
Results Retrieval
        
Each class provides methods to retrieve the result of the algorithm and the time taken for execution, which are used to update the result fields in the main class.


### Example: SequentialSearch Class

```java
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
        time = System.nanoTime() - startTime;
    }

    public int getResult() {
        return result;
    }

    public long getTime() {
        return time;
    }
}
```

This class searches for the target number in the array using sequential search and measures the time taken for the search.

## Conclusion
The code is structured in a modular way, with separate classes for each algorithm, allowing for easy understanding, modification, and extension. The use of Swing and SwingWorker ensures a responsive and user-friendly interface, making Algorithm Race a practical tool for learning and comparing algorithms.
