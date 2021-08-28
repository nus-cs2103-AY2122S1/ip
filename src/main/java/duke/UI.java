package duke;

import java.util.Scanner;

import duke.task.Task;

public class UI {

    private Scanner scanner;

    public UI() {
        scanner = new Scanner(System.in);
    }

    // Print methods

    /** Prints the welcome message */
    public void printWelcome() {
        System.out.println(
                "Lollipop: Hello! I am your personal chatbot, Lollipop! "
                + "What would you like to do today?");
    }

    /** Prints the goodbye message */
    public void printGoodBye() {
        System.out.println("Lollipop: See you again soon!");
    }

    /** Prints a message when no task is available */
    public void printNoTaskAvailable() {
        System.out.println("Lollipop: You have no tasks available.");
    }

    /**
     * Prints a task with a index number.
     *
     * @param index The index of the task.
     * @param task The task to be printed.
     */
    public void printTaskWithIndex(int index, Task task) {
        System.out.printf("%d. %s%n", index, task);
    }

    /**
     * Prints a notification when a task is added.
     *
     * @param task The newly added task.
     */
    public void printAddTask(Task task) {
        System.out.printf("Lollipop: %s has been added.%n", task.toString());
    }

    /**
     * Prints a notification when a task is marked done.
     *
     * @param task The task which has been marked as done.
     */
    public void printTaskMarkedDone(Task task) {
        System.out.printf("Lollipop: %s has been marked as done.%n", task.toString());
    }

    /**
     * Prints a notification when a task is deleted.
     *
     * @param task The task which has been deleted.
     */
    public void printDeleteTask(Task task) {
        System.out.printf("Lollipop: %s has been deleted.%n", task.toString());
    }

    /** Prints the header when displaying the task list */
    public void printTaskListHeader() {
        System.out.println("Lollipop: Here are your tasks");
    }

    /** Prints the header when displaying tasks which occur on the specified date */
    public void printTasksOnDateHeader() {
        System.out.println("Lollipop: Here the tasks that occurs on the specified date:");
    }

    /** Prints the header when displaying tasks with specified keyword */
    public void printTasksWithKeywordHeader() {
        System.out.println("Lollipop: Here the tasks that match your keyword:");
    }

    // Error methods
    /**
     * Prints an error when the data file is not found.
     *
     * @param filePath The filepath of the data file.
     */
    public void printLoadingError(String filePath) {
        System.out.printf("Lollipop: %s is not found.%n", filePath);
    }

    /**
     * Prints a message for Duke Exceptions.
     *
     * @param e The Duke Exception.
     */
    public void printDukeException(Exception e) {
        System.out.printf("Lollipop: %s%n", e.getMessage());
    }

    /** Prints a message for IndexOutOfBoundsException */
    public void printIndexOutOfBoundsException() {
        System.out.println("Lollipop: No such duke.task number found.");
    }

    /** Prints a message for NumberFormatException */
    public void printNumberFormatException() {
        System.out.println("Lollipop: Please input a number.");
    }

    /** Prints a message for DateTimeParseException */
    public void printDateTimeParseException() {
        System.out.println("Lollipop: Please specify a valid date format, such as YYYY-MM-DD");
    }

    // Read methods
    /** Reads the next command input from the user */
    public String readCommand() {
        System.out.println("");
        System.out.print("You: ");
        return scanner.nextLine();
    }
}
