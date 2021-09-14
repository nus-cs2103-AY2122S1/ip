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
     * @return welcome message.
     */
    public static String showWelcome() {
        return "Hello! I'm Duke\nWhat can I do for you?";
    }

    public static String addTag(String taggedString) {
        String output = "Got it, I've tagged the following item:\n";
        output += taggedString;
        return output;
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
     * @return the tasks in the list.
     */
    public static String listTasks(ArrayList<Task> taskList) {
        if (taskList.size() == 0) {
            return "You have no tasks in your list!\nTry adding some tasks with todo, event, or deadline.";
        } else {
            String output = "Here are the tasks in your list:\n";
            for (int i = 0; i < taskList.size(); i++) {
                output += (i + 1) + "." + taskList.get(i) + "\n";
            }
            return output;
        }
    }


    /**
     * Returns the String message instead of printing
     *
     * @param task task to be completed
     * @return String of the message
     */
    public static String finishTask(Task task) {
        String output = "Nice! I've marked this task as done:\n";
        output = output + task;
        return output;
    }

    /**
     * Displays the remaining tasks in the list.
     *
     * @param tasks task list.
     * @return the remaining tasks in the list.
     */
    public static String remainingTasks(ArrayList<Task> tasks) {
        return String.format("Now you have %d tasks in the list.", tasks.size());
    }

    /**
     * Displays a message when a task is deleted.
     *
     * @param task task to be deleted.
     * @return message when task is deleted.
     */
    public static String deleteTask(Task task) {
        String output = "Noted. I've removed this task:\n";
        output += task;
        return output;
    }

    /**
     * Displays the exit message for Duke.
     *
     * @return exit message.
     */
    public static String endDuke() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays the error message for a number format exception.
     *
     * @return number format exception message.
     */
    public static String numberFormatExceptionMessage() {
        return "☹ OOPS!!! The value you inputted is not valid!";
    }

    /**
     * Displays the error message for a date time parse exception.
     *
     * @return date time parse exception message.
     */
    public static String dateTimeParseExceptionMessage() {
        return "☹ OOPS!!!  You used an invalid date! Hint: Use 'YYYY-MM-DD HH:mm'";
    }

    /**
     * Displays the error message for an array index out of bounds exception.
     *
     * @return array out of bounds message.
     */
    public static String arrayIndexOutOfBoundsExceptionMessage() {
        return "☹ OOPS!!!  Did you miss a term?";
    }

    /**
     * Displays the hint when there is an error message for a the creation of a todo.
     *
     * @return hint for toDo.
     */
    public static String toDoHint() {
        return "Hint: add a description!";
    }

    /**
     * Displays the hint when there is an error message for a the creation of a deadline.
     *
     * @return hint for deadline.
     */
    public static String deadlineHint() {
        return "Hint: Use /by to add a deadline!";
    }

    /**
     * Displays the hint when there is an error message for a the creation of a event.
     *
     * @return hint for event.
     */
    public static String eventHint() {
        return "Hint: Use /at to add a timing for the event!";
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
     * @return message that task is added.
     */
    public static String addTask(Task task) {
        String s = "Got it. I've added this task:\n";
        s += task + "\n";
        return s;
    }

    /**
     * Displays the number of tasks in the task list.
     *
     * @param tasks the task list
     * @return number of tasks in the list.
     */
    public static String numberOfTasks(ArrayList<Task> tasks) {
        return String.format("Now you have %d tasks in the list.%n", tasks.size());
    }

    /**
     * Displays the default message when an input is not understood.
     *
     * @return default message when input not understood.
     */
    public static String defaultMessage() {
        return "OOPS I did not quite understand that :(";
    }

    /**
     * Prints out the results given a task list which is the result of a search operation.
     *
     * @param taskList task list comprising matching search terms.
     * @return lists search results.
     */
    public static String listTasksSearchResults(ArrayList<Task> taskList) {
        if (taskList.size() == 0) {
            return "There were no search results. Try another keyword!";
        } else {
            String output = "Here are the matching tasks in your list:\n";
            for (int i = 0; i < taskList.size(); i++) {
                output += (i + 1) + "." + taskList.get(i) + "\n";
            }
            return output;
        }
    }

    /**
     * Provides the hint for users when they use the find command.
     *
     * @return hint for find.
     */
    public static String findHint() {
        return "Add the keyword at the back of find!";
    }
}
