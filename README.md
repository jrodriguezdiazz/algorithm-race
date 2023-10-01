# Algorithm Race: A Comparative Analysis Project

Algorithm Race is a project designed to compare and analyze the efficiency and speed of various sorting and searching algorithms. It provides a user-friendly interface allowing users to input a set of data and a target number, and then observe how quickly different algorithms can sort the data or find the target number.

## Overview

Algorithm Race is built in Java, utilizing the Swing library for the graphical user interface. It allows users to visually compare the performance of several sorting and searching algorithms, including Sequential Search, Binary Search, Bubble Sort, Insertion Sort, and Quick Sort. Each algorithm is implemented in its own class and runs in a separate thread, allowing for parallel execution and accurate time measurement.

## Features

- **User-Friendly Interface:** Easy-to-use graphical interface built with Swing.
- **Multiple Algorithms:** Includes implementations of several sorting and searching algorithms.
- **Parallel Execution:** Runs each algorithm in its own thread, allowing for simultaneous execution and comparison.
- **Real-Time Results:** Displays the results and the time taken for each algorithm in real-time.
- **Input Validation:** Ensures that the user inputs are valid before executing the algorithms.

## How It Works

1. **User Input:** The user inputs an array of integers and a target number through the graphical interface.
2. **Start Execution:** The user starts the execution by clicking the "Start" button.
3. **Validation and Conversion:** The input is validated, and the array is converted to integer format.
4. **Algorithm Execution:** Each algorithm is executed in parallel in its own thread, measuring the time taken to complete.
5. **Display Results:** Once all algorithms have completed execution, the results and execution times are displayed in the interface.
6. **Reset:** The user has the option to reset the fields and run the algorithms again with different inputs.

## Usage

To use Algorithm Race, simply run the main class, input a valid array and a target number, and click the "Start" button. The results of each algorithm, along with the time taken, will be displayed in the respective fields in the interface. If needed, the user can reset the fields using the "Reset" button and input new data.

## Conclusion

Algorithm Race is a versatile tool for anyone looking to understand and compare the efficiency of various sorting and searching algorithms. It provides a hands-on, interactive way to visualize algorithm performance, making it a valuable resource for students, educators, and anyone with an interest in algorithms and data structures.

## License

This project is open-source and available to everyone. Feel free to modify, distribute, and contribute to the project.
