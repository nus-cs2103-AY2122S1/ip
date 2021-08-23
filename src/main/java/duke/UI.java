package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class UI {

    private Scanner scanner;

    public UI() {
        scanner = new Scanner(System.in);
    }

    // Print methods

    /** Prints the welcome message */
    public void printWelcome() {
        System.out.println(
                "Lollipop: Hello! I am your personal chatbot, Lollipop! " +
                        "What would you like to do today?");
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
     * Prints the task list.
     *
     * @param taskList The current list of tasks.
     */
    public void printTaskList(List<Task> taskList) {
        System.out.println("Lollipop: Here are your tasks");
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            System.out.printf("%d. %s%n", i + 1, task.toString());
        }
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

    /**
     * Prints the tasks which occur on a specified date.
     *
     * @param taskList The list of tasks.
     * @param date The specified date for which the tasks fall on.
     */
    public void printTaskOnDate(List<Task> taskList, LocalDate date) {
        System.out.println("Lollipop: Here the tasks that occurs on the specified date.");
        int count = 1;
        for (Task task : taskList) {
            if (task instanceof Deadline) {
                LocalDate deadline = ((Deadline) task).getDeadline();
                if (deadline.equals(date)) {
                    System.out.printf("%d. %s%n", count, task);
                }
            } else if (task instanceof Event) {
                LocalDate time = ((Event) task).getTime();
                if (time.equals(date)) {
                    System.out.printf("%d. %s%n", count, task);
                }
            }
        }
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
