package duke;

import java.util.ArrayList;

import duke.tasks.Task;

/**
 * Responds to Interactions with the user.
 * Returns String message to be displayed in GUI.
 */
public class Ui {
    private static final String GREETING = "Hello! I'm Duck! *quack*\n"
            + "What can I do for you?\n"
            + "Enter 'help' to view supported commands.";
    private static final String BYE = "Bye. Hope to see you again soon!\n"
            + "*quack*\n";
    private static final String HELP = "Here's a list of commands that Duck can help you with!\n"
            + "1. todo TASK: Add a Todo task\n"
            + "2. deadline TASK /by YYYY-MM-DD: Add a Deadline task with a specified date\n"
            + "3. event TASK /at YYYY-MM-DD: Add a Event task with a specified date\n"
            + "4. list: Display your task list\n"
            + "5. done TASK_NO: Mark a task as completed\n"
            + "6. delete TASK_NO: Delete a task\n"
            + "7. find SEARCH_TERMS: Search for tasks\n"
            + "8. help: View this list of commands again\n"
            + "*quack*\n";

    /**
     * Display greeting to users upon running.
     * @return Greeting string.
     */
    public static String showGreeting() {
        return GREETING;
    }

    /**
     * Display farewell greeting after user uses 'bye' command.
     * @return Bye greeting string.
     */
    public String showBye() {
        return BYE;
    }

    /**
     * Display list of supported commands when user uses 'help' command.
     * @return String list of commands.
     */
    public String showHelp() {
        return HELP;
    }
    
    /**
     * Displays error message from exceptions or errors.
     * @param errorMessage message from exceptions or errors.
     * @return Error message string.
     */
    public static String showError(String errorMessage) {
        return errorMessage;
    }

    /**
     * Inform user that task has been added.
     * @param task     task that was added.
     * @param numTasks number of tasks in the user's tasklist.
     * @return Message that task was added.
     */
    public String showAdded(Task task, int numTasks) {
        return String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in your list.\n",
                task.toString(), numTasks);
    }

    /**
     * Inform user that task has been deleted.
     * @param deletedTask task that was deleted.
     * @param numTasks    number of remaining tasks in the user's tasklist.
     * @return Message that task was deleted.
     */
    public String showDeleted(Task deletedTask, int numTasks) {
        return String.format("Noted. I've removed this task:\n%s\nNow you have %d tasks in your list.\n",
                deletedTask.toString(), numTasks);
    }

    /**
     * Inform user that task has already been marked as done.
     * @param task task that was already done.
     * @return Message that task was already done.
     */
    public String showTaskDone(Task task) {
        return "This task has already been completed!\n"
                + task.toString();
    }

    /**
     * Inform user that task was marked as done.
     * @param task task that was marked as done.
     * @return Message that task was marked as done.
     */
    public String showMarkedDone(Task task) {
        return "Nice! I've marked this task as done: \n"
                + task.toString();
    }

    /**
     * Displays the list of tasks in the user's TaskList when 'list' command is used.
     * @param tasks user's TaskList.
     * @return String of user's tasklist.
     */
    public String showList(TaskList tasks) {
        if (tasks.taskList.isEmpty()) {
            return "There are no tasks on your list. *quack*";
        } else if (tasks.getLength() == 1) {
            return String.format("There is one task on your list:\n1. %s\n*quack*",
                    tasks.getTask(0).toString());
        } else {
            String taskStrings = "Here are the tasks on your list:\n";
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
            String searchResults = "Here are the tasks I found:\n";
            System.out.println();
            for (Task task : results) {
                searchResults += String.format("%s\n", task.toString());
            }
            searchResults += "*quack*\n";
            return searchResults;
        }
    }
}
