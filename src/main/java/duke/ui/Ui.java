package duke.ui;

import java.util.Scanner;

import duke.task.Task;

public class Ui { //IU Class used to handle the interactions with the user.
    private final Scanner sc;

    /**
     * Constructor for the Ui class
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Calls the welcome Message for Duke
     */
    public String showWelcome() {
        String logo = "Hello from\n"
                + " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"
                + "Hello! I'm Duke\n" + "What can I do for you?\n";
        return logo;
    }

    /**
     * Calls the goodbye message for Duke
     */
    public String showGoodBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Calls the divider line
     */
    public String showLine() {
        return "_______________";
    }

    /**
     * Calls the message to inform the user that the task has been added successfully
     * @param taskList List of tasks
     */
    public String showAddedMessage(TaskList taskList) {
        String line = "Got it. I've added this task:\n"
                + "  " + (taskList.get(taskList.getCount() - 1)) + "\n"
                +"Now you have " + taskList.getCount() + " in the list.";
        return line;
    }

    /**
     * Reads the input by the user and returns it for the parser
     * @return the input by the user
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints the list of task for the users
     * @param taskList Takes in the current list of tasks
     */
    public static String printList(TaskList taskList) {
        return taskList.printList();
    }

    /**
     * Prints the list of task for the users with the pattern in the task name
     * @param taskList List of tasks
     * @param pattern pattern to be searched
     */
    public static String searchList(TaskList taskList, String pattern) {
       return taskList.searchList(pattern);
    }

    /**
     * Prints the done Message to inform the user that the task has been marked as done
     * @param msg  task's message when marked as done
     */
    public static String doneMessage(String msg) {
        String line = "Nice! I've marked this task as done:\n"
                + "  " + msg;
        return line;
    }

    /**
     * Prints the delete message to inform the user that the task has been deleted properly
     * @param task Task to be deleted
     * @param index The number of tasks that remains in the list
     */
    public static String deleteMessage(Task task, int index) {
        String line = "Noted. I've removed this task:\n"
                + "  " + task + "\n"
                +"Now you have " + index + " in the list.";
        return line;
    }

    /**
     * Prints the error to inform the user that their file format is incorrect
     */
    public static String showLoadingError() {
        return "☹ OOPS!!! It seems like your file format is incorrect!";
    }
}
