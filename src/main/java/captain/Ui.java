package captain;

import java.util.Scanner;

import captain.task.Task;
import captain.task.TaskList;


/**
 * Deals with interactions with the user.
 *
 * @author Adam Ho
 */
public class Ui {
    public static final String LIST_TASK_MSG = "\tHere are a list of tasks you have:\n";
    public static final String EMPTY_LIST = "\tGreat job! You're done for the day!\n";
    public static final String FIND_TASKS_MSG = "\tHere's all that I can find with the keywords:\n";
    public static final String DONE_MSG = "\tGood job! One less task for you!\n\t  ";
    public static final String CLEAR_MSG = "\tAll your tasks have been cleared!";
    public static final String LOAD_ERROR_MSG = "\tOops! Looks like something went wrong with your data file!";
    public static final String EXIT_MSG = "\tBye, see you again soon!";

    private Scanner sc = new Scanner(System.in);

    public String readCommand() {
        return sc.nextLine();
    }

    public String showGoodbye() {
        return EXIT_MSG;
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
        return LOAD_ERROR_MSG;
    }

    /**
     * Shows to user the current list of tasks.
     * @param tasks The task list containing the user's tasks.
     */
    public String showTaskList(TaskList tasks) {
        return LIST_TASK_MSG + printTaskLists(tasks);
    }

    /**
     * Shows to user that the task list is empty.
     * @return A String that informs the user that the task list is empty.
     */
    public String showEmptyTaskList() {
        return EMPTY_LIST;
    }

    public String showTasksFound(TaskList tasks) {
        return FIND_TASKS_MSG + printTaskLists(tasks);
    }

    public String showTaskDone(Task task) {
        return DONE_MSG + task;
    }

    /**
     * Shows to user that the task list has been cleared.
     */
    public String showClearTasks() {
        return CLEAR_MSG;
    }


    public String showError(String errorMessage) {
        return "\t" + errorMessage;
    }

    /**
     * Returns a String representation of the task list as an ordered list.
     * @param tasks The tasks in the task list.
     * @return An ordered list of the tasks.
     */
    public String printTaskLists(TaskList tasks) {
        String s = "";
        int id = 1;
        for (Task task : tasks.getTaskList()) {
            s += "\t" + id++ + "." + task + "\n";
        }
        return s;
    }
}
