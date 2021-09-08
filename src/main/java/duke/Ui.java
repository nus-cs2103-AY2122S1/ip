package duke;

import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

/**
 * Deals with interactions with the user.
 *
 * @author Adam Ho
 */
public class Ui {
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
        String response = "\tHere are the tasks in your list:\n";
        int id = 1;
        for (Task task : tasks.getTaskList()) {
            response += "\t" + id++ + "." + task + "\n";
        }
        return response;
    }

    public String showTaskDone(Task task) {
        return "\tNice! I've marked this task as done:\n\t  " + task;
    }

    /**
     * Shows a confirmation message to the user before clearing all tasks.
     */
    public String showConfirmClearTasks() {
        return "\tAre you sure you want to clear your tasks?\ny/n:";
    }

    /**
     * Shows to user that the task list has been cleared.
     */
    public String showClearTasks() {
        return "\t All your tasks have been cleared!";
    }

    /**
     * Shows to user that the task list will not be cleared.
     */
    public String showTasksNotCleared() {
        return "\tAlright I won't clear your tasks :)";
    }

    public String showError(String errorMessage) {
        return "\t" + errorMessage;
    }
}
