package duke;
import java.util.Scanner;

import duke.task.Find;
import duke.task.Task;
import duke.task.TaskList;


/**
 * Class that encapsulates the Ui in Duke.
 */
public class Ui {
    private Scanner sc;
    private String input = "";

    /**
     * Constructor for Ui class.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Returns welcome statement.
     *
     * @return Welcome statement.
     */
    public String showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return ("Hello from\n" + logo + "\n"
                + "Hello! I'm Duke\n" + "What can I do for you?");
    }

    /**
     * Returns closing statement.
     *
     * @return Closing statement.
     */
    public String goodbye() {
        return ("Bye. Hope to see you again soon!");
    }

    /**
     * Returns Ui for adding task to list.
     *
     * @param task Task to be added.
     * @param size Updated number of items on the TaskList.
     * @return Ui for adding task to list.
     */
    public String addTaskToList(Task task, int size) {
        String taskToString = task.toString();
        return ("Got it. I've added this task: \n" + taskToString
                + "\nNow you have " + size + " tasks in the list.");
    }

    /**
     * Prints Ui for setting task as done.
     *
     * @param task Done task.
     */
    public String setTaskAsDone(Task task) {
        return "Nice! I've marked this task as done: \n" + task.toString();
    }

    /**
     * Prints Ui for removing task from the list.
     *
     * @param task Task to be removed.
     * @param size Updated number of items on the TaskList.
     */
    public String removeTaskFromList(Task task, int size) {
        String taskToString = task.toString();
        return ("Noted. I've removed this task: \n" + taskToString
                + "\nNow you have " + size + " tasks in the list.");
    }

    /**
     * Prints the current TaskList.
     *
     * @param taskList The current TaskList.
     */
    public String printTaskList(TaskList taskList) {
        if (taskList.getSize() == 0) {
            return "There are currently no tasks in your list.";
        } else {
            String ls = "Here are the tasks in your list:\n";
            for (int i = 0; i < taskList.getSize(); i++) {
                ls += (i + 1) + "." + taskList.getTask(i).toString() + "\n";
            }
            return ls;
        }
    }

    /**
     * Searches for the keyword among current Tasks.
     *
     * @param ls Current TaskList.
     * @param word Keyword.
     * @param find Current Find object.
     * @return String representation of the Tasks that include the keyword.
     */
    public String printListWithKeyword(TaskList ls, String word, Find find) {
        String result = "";
        int count = 1;
        for (int i = 0; i < ls.getSize(); i++) {
            Task task = ls.getTask(i);
            if (task.getDesc().toLowerCase().contains(word.toLowerCase())) {
                find.setFound();
                result += count + "." + ls.getTask(i).toString() + "\n";
                count++;
            }
        }
        return result;
    }

    /**
     * Prints a message when all current Tasks do not contain the keyword provided.
     *
     * @param word Keyword.
     */
    public String noResultsFound(String word) {
        return ("There were no tasks that included your keyword: " + word + ".");
    }

    /**
     * Reads the user input.
     *
     * @return The next line of user input.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints DukeException errors.
     *
     * @param e Error message.
     */
    public String showError(String e) {
        return (e);
    }

    /**
     * Prints line for formatting messages.
     */
    public String showLine() {
        return ("___________________");
    }

    /**
     * Returns String representation of Task status.
     *
     * @param status Boolean representation of Task status, true for done and false for undone.
     * @return String representation of Task status, "X" for done and " " for undone.
     */
    public String taskStatusIcon(boolean status) {
        return (status ? "X" : " ");
    }

}
