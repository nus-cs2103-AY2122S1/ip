package duke;

import duke.task.Task;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents the User Interface, which is responsible for all the inputting and printing of
 * statements which the user will see.
 */
public class Ui {
    public static final String DEFAULT_SPACES = "    ";
    public static final String INDENTED_SPACES = "     ";
    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private Scanner input;
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Constructor for Ui. Initialises the input scanner.
     */
    public Ui() {
        this.input = new Scanner(System.in);
    }

    /**
     * Shows the welcome message.
     */
    public static void showWelcome() {
        Ui.showLine();
        System.out.println(DEFAULT_SPACES + "Hello! I'm Duke");
        System.out.println(DEFAULT_SPACES + "What can I do for you?");
        Ui.showLine();
    }

    /**
     * Gives the program the next line which was input by the user.
     *
     * @return string of the input.
     */
    public String readCommand() {
        return input.nextLine();
    }

    /**
     * Displays the tasks currently in the task list.
     *
     * @param taskList the current task list.
     */
    public static void listTasks(ArrayList<Task> taskList) {
        if (taskList.size() == 0) {
            System.out.println(DEFAULT_SPACES + "You have no tasks in your list!");
            System.out.println(DEFAULT_SPACES + "Try adding some tasks with todo, event, or deadline");
        } else {
            System.out.println(DEFAULT_SPACES + "Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(INDENTED_SPACES + (i + 1) + "." + taskList.get(i));
            }
        }
    }

    /**
     * Displays the message when a user finishes a task.
     *
     * @param task the task to be completed.
     */
    public static void finishTask(Task task) {
        System.out.println(DEFAULT_SPACES + "Nice! I've marked this task as done:");
        System.out.println(INDENTED_SPACES + task);
    }

    /**
     * Displays the remaining tasks in the list.
     *
     * @param tasks task list.
     */
    public static void remainingTasks(ArrayList<Task> tasks) {
        System.out.printf(DEFAULT_SPACES + "Now you have %d tasks in the list.%n", tasks.size());
    }

    /**
     * Displays a message when a task is deleted.
     *
     * @param task task to be deleted.
     */
    public static void deleteTask(Task task) {
        System.out.println(DEFAULT_SPACES + "Noted. I've removed this task:");
        System.out.println(INDENTED_SPACES + task);
    }

    /**
     * Displays the exit message for Duke.
     */
    public static void endDuke() {
        System.out.println(DEFAULT_SPACES + "Bye. Hope to see you again soon!");
    }

    /**
     * Displays the error message for a number format exception.
     */
    public static void numberFormatExceptionMessage() {
        System.out.println(DEFAULT_SPACES + "☹ OOPS!!! The value you inputted is not valid!");
    }

    /**
     * Displays the error message for a date time parse exception.
     */
    public static void dateTimeParseExceptionMessage() {
        System.out.println(DEFAULT_SPACES + "☹ OOPS!!!  You used an invalid date! Hint: Use 'YYYY-MM-DD HH:mm'");
    }

    /**
     * Displays the error message for an array index out of bounds exception.
     */
    public static void arrayIndexOutOfBoundsExceptionMessage() {
        System.out.println(DEFAULT_SPACES + "☹ OOPS!!!  Did you miss a term?");
    }

    /**
     * Displays the hint when there is an error message for a the creation of a todo.
     */
    public static void toDoHint() {
        System.out.println(DEFAULT_SPACES + "Hint: add a description!");
    }

    /**
     * Displays the hint when there is an error message for a the creation of a deadline.
     */
    public static void deadlineHint() {
        System.out.println(DEFAULT_SPACES + "Hint: Use /by to add a deadline!");
    }

    /**
     * Displays the hint when there is an error message for a the creation of a event.
     */
    public static void eventHint() {
        System.out.println(DEFAULT_SPACES + "Hint: Use /at to add a timing for the event!");
    }

    /**
     * Displays the error message for when there is a file not found exception.
     */
    public static void fileNotFoundError() {
        System.out.println("File not found! Please create an empty file in /ip/data named duke.txt to get started");
    }

    /**
     * Displays a message stating that there was no saved file, so one is created for them.
     */
    public static void createNewFile() {
        System.out.println(DEFAULT_SPACES + "Saved file not found! Creating a new file for you...");
    }

    /**
     * Displays a message stating that the directory did not exist, so one is created for them.
     */
    public static void createNewDirectory() {
        System.out.println(DEFAULT_SPACES + "Directory does not exist! Creating a new directory for you...");
    }

    /**
     * Displays a message when a task is added.
     *
     * @param task task to be added
     */
    public static void addTask(Task task) {
        System.out.println(DEFAULT_SPACES + "Got it. I've added this task:");
        System.out.println(INDENTED_SPACES + task);
    }

    /**
     * Displays the number of tasks in the task list.
     *
     * @param tasks the task list
     */
    public static void numberOfTasks(ArrayList<Task> tasks) {
        System.out.printf(DEFAULT_SPACES + "Now you have %d tasks in the list.%n", tasks.size());
    }

    /**
     * Displays the default message when an input is not understood.
     */
    public static void defaultMessage() {
        System.out.println(DEFAULT_SPACES + "OOPS I did not quite understand that :(");
    }

    /**
     * Displays a straight line used for dividing commands and inputs.
     */
    public static void showLine() {
        System.out.println("    ____________________________________________________________");
    }
}
