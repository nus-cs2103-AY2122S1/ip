package duke;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Represents the User Interface which interacts with the user.
 */
public class Ui {
    Scanner sc;

    /**
     * Class constructor specifying the Scanner object to read user input.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints welcome message.
     */
    public void welcome() {
        String message =
                "____________________________________________________________\n" +
                        " Hello! I'm Duke\n" +
                        " What can I do for you?\n" +
                        "____________________________________________________________\n";
        System.out.println(message);
    }

    public String readInput() {
        return sc.nextLine();
    }

    /**
     * Prints a list of all the tasks
     * @param tasks TaskList containing the tasks.
     */
    public void listTasks(TaskList tasks) {
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the tasks in your list:");
        System.out.println(tasks.toString());
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Prints terminating message.
     */
    public void terminateMessage() {
        String message =
                "____________________________________________________________\n" +
                        " Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________\n";
        System.out.println(message);
    }

    /**
     * Prints message to confirm all tasks has been cleared.
     */
    public void clearMessage() {
        String message =
                "____________________________________________________________\n" +
                        " List is cleared!\n" +
                        "____________________________________________________________\n";
        System.out.println(message);
    }

    /**
     * Prints message indicating task has been added successfully.
     * @param task Task that was added
     * @param tasks TaskList
     */
    public void addMessage(Task task, TaskList tasks) {
        String message =
                "____________________________________________________________\n" +
                        " Got it. I've added this task:\n   " +
                        task.toString() +
                        tasks.getListSizeString() +
                        "____________________________________________________________\n";
        System.out.println(message);
    }

    /**
     * Prints message indicating that task has been marked as done.
     * @param task Task to be marked as done.
     */
    public void doneMessage(Task task) {
        String message =
                "____________________________________________________________\n" +
                        " Nice! I've marked this task as done:\n" +
                        String.format("   [%s] %s\n", task.getCompletedMarker(), task.getTask()) +
                        "____________________________________________________________\n";
        System.out.println(message);
    }

    /**
     * Prints message indicating that task has been completed.
     * @param task Task to be deleted.
     * @param tasks TaskList.
     */
    public void deleteMessage(Task task, TaskList tasks) {
        String message =
                "____________________________________________________________\n" +
                        " Noted. I've removed this task:\n   " +
                        task.toString() +
                        String.format("\n Now you have %d tasks in the list.\n", tasks.getListSize()) +
                        "____________________________________________________________\n";
        System.out.println(message);
    }

    /**
     * Prints tasks containing specified keyword.
     * @param tasks TaskList containing filtered tasks.
     */
    public void findMessage(TaskList tasks) {
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the matching tasks in your list:");
        System.out.println(tasks.toString());
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Prints the appropriate error handling message.
     * @param e Exception caught.
     */
    public void handleException(Exception e) {
        String message;
        if (e instanceof InvalidCommandException) {
            message =
                    "____________________________________________________________\n" +
                            " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                            "____________________________________________________________\n";
        } else if (e instanceof ToDoDescriptionNotFoundException) {
            message =
                    "____________________________________________________________\n" +
                            " ☹ OOPS!!! The description of a todo cannot be empty.\n" +
                            "____________________________________________________________\n";
        } else if (e instanceof EventDescriptionNotFoundException) {
            message =
                    "____________________________________________________________\n" +
                            " ☹ OOPS!!! The description of an event cannot be empty.\n" +
                            "____________________________________________________________\n";
        } else if (e instanceof DeadlineDescriptionNotFoundException) {
            message =
                    "____________________________________________________________\n" +
                            " ☹ OOPS!!! The description of a deadline cannot be empty.\n" +
                            "____________________________________________________________\n";
        } else if (e instanceof DeadlineNotFoundException) {
            message =
                    "____________________________________________________________\n" +
                            " ☹ OOPS!!! The date of the deadline cannot be empty.\n" +
                            "____________________________________________________________\n";
        } else if (e instanceof EventTimeNotFoundException) {
            message =
                    "____________________________________________________________\n" +
                            " ☹ OOPS!!! The time of an event cannot be empty.\n" +
                            "____________________________________________________________\n";
        } else if (e instanceof TaskIndexOutOfBoundException) {
            message =
                    "____________________________________________________________\n" +
                            " ☹ OOPS!!! The task number is invalid.\n" +
                            "____________________________________________________________\n";
        } else if (e instanceof DateTimeParseException) {
            message =
                    "____________________________________________________________\n" +
                            " ☹ OOPS!!! The date is in the wrong format. Please use the following format\n" +
                            " For event: dd-MM-yyyy HHmm\n" +
                            " For deadline: dd-MM-yyyy\n" +
                            "____________________________________________________________\n";
        } else {
            message =
                    "____________________________________________________________\n" +
                            " ☹ OOPS!!! An unknown error has occurred!\n" +
                            "____________________________________________________________\n";
        }
        System.out.println(message);
    }
}
