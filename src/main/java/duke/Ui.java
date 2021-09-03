package duke;

import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Deals with interactions with the user.
 *
 * @author Adam Ho
 */
public class Ui {
    private static final String DIVIDER = "\t____________________________________________________________";
    private Scanner sc = new Scanner(System.in);

    public String readCommand() {
        return sc.nextLine();
    }

    public void showLine() {
        System.out.println(DIVIDER);
    }

    public void showWelcome() {
        System.out.println("\tHello! I'm Adam, your personal chat bot.\n\tHow may I assist you today?");
    }

    public void showGoodbye() {
        System.out.println("\tGoodbye! Please visit me again soon :(");
    }

    /**
     * Shows to user that a task was added to the task list successfully.

     */
    public void showAddTask(TaskList tasks, Task task) {
        System.out.println("\tGot it. I've added this task:\n\t  " + task);
        System.out.println("\tNow you have " + tasks.getListSize() + " in the list.");
    }

    /**
     * Shows to user that a task was deleted from the task list successfully.
     * @param tasks The task list containing the user's tasks.
     * @param task The task to add into the task list.
     */
    public void showDeleteTask(TaskList tasks, Task task) {
        System.out.println("\tNoted. I've removed this task:\n\t  " + task);
        System.out.println("\tNow you have " + tasks.getListSize() + " in the list.");
    }

    public void showLoadingError() {
        System.out.println("\tWe couldn't load your data file ><");
    }

    /**
     * Shows to user the current list of tasks.
     * @param tasks The task list containing the user's tasks.
     */
    public void showTaskList(TaskList tasks) {
        System.out.println("\tHere are the tasks in your list:");
        int id = 1;
        for (Task task : tasks.getTaskList()) {
            System.out.println("\t" + id++ + "." + task);
        }
    }

    public void showTaskDone(Task task) {
        System.out.println("\tNice! I've marked this task as done:\n\t  " + task);
    }

    public void showError(String errorMessage) {
        System.out.println("\t" + errorMessage);
    }
}
