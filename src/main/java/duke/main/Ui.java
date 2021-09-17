package duke.main;

import java.util.Scanner;

import duke.tasks.Task;

/**
 * Represents a class that handles all Ui responsibility
 */
public class Ui {
    private final Scanner sc;
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Shows users that there are no saved txt file
     */
    public void showLoadingError() {
        System.out.println("Saved task not found, starting as new!");
    }

    /**
     * Shows errors
     * @param error Errors to be shown
     */
    public void showError(String error) {
        System.out.println(error);
    }

    /**
     * Shows welcome screen
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }

    public String showBye() {
        return "Bye. Hope to see you again soon!";
    }

    public void showLine() {
        System.out.println("───────────────────────────");
    }

    /**
     * Shows found tasks
     * @param foundTask Task found
     */
    public String showFoundTask(String foundTask) {
        String output = "";
        output += "Here are the matching tasks in your list\n";
        output += foundTask;
        return output;
    }

    public String readCommand() {
        return this.sc.nextLine();
    }

    /**
     * Shows confirmation after task is added
     * @param task Task added
     */
    public String showAddTask(Task task) {
        String output = "";
        output += "Got it. I've added this task:\n";
        output += task;
        return output;
    }

    /**
     * Shows task list
     * @param taskList Task list to be shown
     */
    public String showTaskList(TaskList taskList) {
        String output = "";
        output += "Here are the tasks in your list:\n";
        output += taskList;
        return output;
    }

    /**
     * Shows confirmation that a task has been deleted
     * @param task Deleted task
     */
    public String showDeleteTask(Task task) {
        String output = "";
        output += "Noted. I've removed this task:\n";
        output += task;
        return output;
    }

    /**
     * Shows done task to Ui
     * @param task task that has been marked done
     * @return output to user
     */
    public String showDoneTask(Task task) {
        String output = "";
        output += "Nice! I've marked this task as done:\n";
        output += task;
        return output;
    }

    /**
     * Shows the number of task the user currently have
     * @param numTasks number of tasks
     * @return output to user
     */
    public String showNumTask(int numTasks) {
        if (numTasks == 1 || numTasks == 0) {
            return "Now you have " + numTasks + " task in the list.";
        } else {
            return "Now you have " + numTasks + " tasks in the list.";
        }
    }
}
