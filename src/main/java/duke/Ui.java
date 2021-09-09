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
    public static final String LIST_TASK_MSG = "\tHere are the tasks in your list:\n";
    public static final String FIND_TASKS_MSG = "\tThe tasks that match your keywords are:\n";
    private Scanner sc = new Scanner(System.in);

    public String readCommand() {
        return sc.nextLine();
    }

    public String showWelcome() {
        return "\tHello! I'm Adam, your personal chat bot.\n\tHow may I assist you today?";
    }

    public String showGoodbye() {
        return "\tGoodbye! Please visit me again soon :(";
    }

    /**
     * Shows to user that a task was added to the task list successfully.

     */
    public String showAddTask(TaskList tasks, Task task) {
        return "\tGot it. I've added this task:\n\t  " + task
                + "\n\tNow you have " + tasks.getListSize() + " in the list.";
    }

    /**
     * Shows to user that a task was deleted from the task list successfully.
     * @param tasks The task list containing the user's tasks.
     * @param task The task to add into the task list.
     */
    public String showDeleteTask(TaskList tasks, Task task) {
        return "\tNoted. I've removed this task:\n\t  " + task
                + "\n\tNow you have " + tasks.getListSize() + " in the list.";
    }

    public String showLoadingError() {
        return "\tWe couldn't load your data file ><";
    }

    /**
     * Shows to user the current list of tasks.
     * @param tasks The task list containing the user's tasks.
     */
    public String showTaskList(TaskList tasks) {
        return LIST_TASK_MSG + printTaskLists(tasks);
    }

    public String showTasksFound(TaskList tasks) {
        return FIND_TASKS_MSG + printTaskLists(tasks);
    }

    public String showTaskDone(Task task) {
        return "\tNice! I've marked this task as done:\n\t  " + task;
    }

    /**
     * Shows to user that the task list has been cleared.
     */
    public String showClearTasks() {
        return "\t All your tasks have been cleared!";
    }


    public String showError(String errorMessage) {
        return "\t" + errorMessage;
    }

    public String printTaskLists(TaskList tasks) {
        String s = "";
        int id = 1;
        for (Task task : tasks.getTaskList()) {
            s += "\t" + id++ + "." + task + "\n";
        }
        return s;
    }
}
