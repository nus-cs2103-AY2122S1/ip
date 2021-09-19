package duke;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Represents the User Interface which interacts with the user.
 */
public class Ui {
    private static final String HEADER = "____________________________________________________________\n";
    private static final String FOOTER = "\n____________________________________________________________\n";
    private Scanner sc;

    /**
     * Class constructor specifying the Scanner object to read user input.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints message.
     * @param message to be printed.
     */
    public void printMessage(String message) {
        System.out.println(HEADER
            + message
            + FOOTER);
    }

    /**
     * Prints welcome message.
     */
    public String welcome() {
        String message = " Hello! I'm Duke\n"
                + " What can I do for you?";
        return message;
    }

    public String readInput() {
        return sc.nextLine();
    }

    /**
     * Prints a list of all the tasks
     * @param tasks TaskList containing the tasks.
     */
    public String listTasks(TaskList tasks) {
        String message = String.format(" Here are the tasks in your list:\n%s", tasks.toString());
        return message;
    }

    /**
     * Prints terminating message.
     */
    public String terminateMessage() {
        String message = " Bye. Hope to see you again soon!";
        return message;
    }

    /**
     * Prints message to confirm all tasks has been cleared.
     */
    public String clearMessage() {
        String message = " List is cleared!";
        return message;
    }

    /**
     * Prints message indicating task has been added successfully.
     * @param task Task that was added
     * @param tasks TaskList
     */
    public String addMessage(Task task, TaskList tasks) {
        String message = " Got it. I've added this task:\n   "
                + task.toString()
                + tasks.getListSizeString();
        return message;
    }

    /**
     * Prints message indicating that task has been marked as done.
     * @param task Task to be marked as done.
     */
    public String doneMessage(Task task) {
        String message = " Nice! I've marked this task as done:\n"
                        + String.format("   [%s] %s", task.getCompletedMarker(), task.getTask());
        return message;
    }

    /**
     * Prints message indicating that task has been completed.
     * @param task Task to be deleted.
     * @param tasks TaskList.
     */
    public String deleteMessage(Task task, TaskList tasks) {
        String message = " Noted. I've removed this task:\n   "
                        + task.toString()
                        + String.format("\n Now you have %d tasks in the list.", tasks.getListSize());
        return message;
    }

    /**
     * Prints tasks containing specified keyword.
     * @param tasks TaskList containing filtered tasks.
     */
    public String findMessage(TaskList tasks) {
        String message = " Here are the matching tasks in your list:"
                + tasks.toString();
        return message;
    }

    /**
     * Prints tasks containing specified keyword.
     * @param task TaskList containing filtered tasks.
     */
    public String setPriorityMessage(Task task) {
        String message = " I've changed the priority level of this task:\n"
                + task.toString();
        return message;
    }

    /**
     * Prints the appropriate error handling message.
     * @param e Exception caught.
     */
    public String handleException(Exception e) {
        String message;
        if (e instanceof InvalidCommandException) {
            message = " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        } else if (e instanceof ToDoDescriptionNotFoundException) {
            message = " ☹ OOPS!!! The description of a todo cannot be empty.";
        } else if (e instanceof EventDescriptionNotFoundException) {
            message = " ☹ OOPS!!! The description of an event cannot be empty.";
        } else if (e instanceof DeadlineDescriptionNotFoundException) {
            message = " ☹ OOPS!!! The description of a deadline cannot be empty.";
        } else if (e instanceof DeadlineNotFoundException) {
            message = " ☹ OOPS!!! The date of the deadline cannot be empty.";
        } else if (e instanceof EventTimeNotFoundException) {
            message = " ☹ OOPS!!! The time of an event cannot be empty.";
        } else if (e instanceof TaskIndexOutOfBoundException) {
            message = " ☹ OOPS!!! The task number is invalid.";
        } else if (e instanceof DateTimeParseException) {
            message = " ☹ OOPS!!! The date is in the wrong format. Please use the following format\n"
                            + " For event: dd-MM-yyyy HHmm\n"
                            + " For deadline: dd-MM-yyyy";
        } else {
            message = " ☹ OOPS!!! An unknown error has occurred!";
        }
        return message;
    }
}
