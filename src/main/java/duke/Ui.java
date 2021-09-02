package duke;

import java.util.ArrayList;

import duke.tasks.Task;

/**
 * Deals with Interactions with the user.
 * Handles both input and displaying output.
 */
public class Ui {
    private boolean isExit;
    static final String GREETING = "Hello! I'm Duck! *quack*\n"
            + "What can I do for you?\n";
    private static final String BYE = "Bye. Hope to see you again soon!\n"
            + "*quack*\n";
    
    /** Constructor for Ui object.*/
    public Ui() {
        this.isExit = false;
    }

    /**
     * Prints greeting to users upon running.
     */
    public String showGreeting() {
        return GREETING;
    }

    /**
     * Prints farewell greeting after user uses 'bye' command.
     */
    public String showBye() {
        return BYE;
    }

    /**
     * Prints error message from DukeExceptions for users to correct their input.
     *
     * @param errorMessage message from errors caught.
     */
    public String showError(String errorMessage) {
        return errorMessage;
    }

    /**
     * Prints IOException error message when opening the duke.txt file upon start.
     *
     * @param errorMessage message from IOException.
     */
    public void showLoadingError(String errorMessage) {
        System.out.println("OOPS!! An error has occurred: " + errorMessage);
    }

    public boolean getIsExit() {
        return this.isExit;
    }

    /**
     * Inform user that task has been added.
     *
     * @param task     task that was added.
     * @param numTasks number of tasks in the user's tasklist.
     */
    public String showAdded(Task task, int numTasks) {
        return String.format("Got it. I've added this task: %s\nNow you have %d tasks in your list.\n",
                 task.toString(), numTasks);
    }

    /**
     * Inform user that task has been deleted.
     *
     * @param deletedTask task that was deleted.
     * @param numTasks    number of remaining tasks in the user's tasklist.
     */
    public String showDeleted(Task deletedTask, int numTasks) {
        return String.format("Noted. I've removed this task: %s\nNow you have %d tasks in your list.\n",
                deletedTask.toString(), numTasks);
    }

    /**
     * Inform user that task has already been marked as done.
     *
     * @param task task that was already done.
     */
    public String showTaskDone(Task task) {
        return "This task has already been completed!\n"
                + task.toString();
    }

    /**
     * Inform user that task was marked as done.
     *
     * @param task task that was marked as done.
     */
    public String showMarkedDone(Task task) {
        return "Nice! I've marked this task as done: \n"
                + task.toString();
    }

    /**
     * Displays the list of tasks in the user's TaskList when 'list' command is used.
     *
     * @param tasks user's TaskList.
     */
    public String showList(TaskList tasks) {
        if (tasks.taskList.isEmpty()) {
            return "There are no duke.tasks on your list. *quack*";
        } else if (tasks.getLength() == 1) {
            return String.format("There is one task on your list:\n1. %s\n*quack*", 
                    tasks.getTask(0).toString());
        } else {
            String taskStrings = "Here are the duke.tasks on your list:\n";
            int i = 1;
            for (Task task : tasks.taskList) {
                taskStrings += String.format("%d. %s\n", i, task.toString());
                i++;
            }
            taskStrings += "*quack*\n";
            return taskStrings;
        }
    }

    /**
     * Returns string of search results from user's 'find' command.
     * @param results ArrayList of tasks that match the search term.
     * @return String of search results.
     */
    public String showFindResults(ArrayList<Task> results) {
        if (results.isEmpty()) {
            return "Sorry, I did not find any task that matches your search *sad quack*\n";
        } else {
            String searchResults = "Here are the tasks I found:";
            System.out.println();
            for (Task task : results) {
                searchResults += String.format("%s\n", task.toString());
            }
            searchResults += "*quack*\n";
            return searchResults;
        }
    }
}
