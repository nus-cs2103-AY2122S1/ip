package tipsy;

/**
 * Ui is the class that deals with all output messages to the user.
 */
public class Ui {

    /**
     * Returns the message when the program is ready to accept user commands.
     *
     * @return A String object containing the start-up message.
     */
    public String printStartInteractionsMessage() {
        return "Hello, I'm Tipsy. What may I do for you?";
    }

    /**
     * Returns a printout the current list of tasks.
     *
     * @param tasks The TaskList containing all the current tasks.
     * @return A String object containing the text printout of the TaskList.
     */
    public String printTaskList(TaskList tasks) {
        StringBuilder taskListSummary = new StringBuilder();
        taskListSummary.append("Current list of tasks:\n");
        for (int i = 1; i <= tasks.getSize(); i++) {
            taskListSummary.append(String.format("\t%d.%s\n", i, tasks.getTask(i)));
        }
        taskListSummary.append("Now you have " + tasks.getSize() + " tasks in the list.");
        return taskListSummary.toString();
    }

    /**
     * Returns the message when the program has added a task to the list.
     *
     * @param tasks The TaskList containing all the current tasks.
     * @param newTask The task that was added to the list.
     * @return A String object containing the message.
     */
    public String printAddTask(TaskList tasks, Task newTask) {
        return "Added:\n\t" + newTask + "\n"
                + "Now you have " + tasks.getSize() + " tasks in the list.";
    }

    /**
     * Returns the message when the program has marked a task as completed.
     *
     * @param completedTask The task that was marked as completed.
     * @return A String object containing the message.
     */
    public String printCompleteTask(Task completedTask) {
        return "Marking task as completed:\n"
                + "\t" + completedTask;
    }

    /**
     * Returns the message when the program has deleted a task from the list.
     *
     * @param tasks The TaskList containing all the current tasks.
     * @param deletedTask The task that was deleted from the list.
     * @return A String object containing the message.
     */
    public String printDeleteTask(TaskList tasks, Task deletedTask) {
        return "Removing task:\n"
                + "\t" + deletedTask + "\n"
                + "Now you have " + tasks.getSize() + " tasks in the list.";
    }

    /**
     * Returns a printout of the list of tasks whose names contain the subject.
     *
     * @param tasks The TaskList containing all the current tasks.
     * @param subject The keyword that is being searched for.
     * @return A String object containing the printout of the search results.
     */
    public String printTasksWithSubject(TaskList tasks, String subject) {
        StringBuilder searchResults = new StringBuilder();
        int numOfResults = 0;
        searchResults.append("Tasks with \"" + subject + "\":\n");
        for (int i = 1; i <= tasks.getSize(); i++) {
            if (tasks.getTask(i).getTaskName().contains(subject)) {
                numOfResults++;
                searchResults.append(String.format("\t%d.%s\n", i, tasks.getTask(i)));
            }
        }

        if (numOfResults == 0) {
            searchResults.append("\nNo results found.");
        } else if (numOfResults == 1) {
            searchResults.append("Total of 1 result found.");
        } else {
            searchResults.append("Total of " + numOfResults + " results found.");
        }
        return searchResults.toString();
    }

    /**
     * Returns the message when the program has loaded the list of tasks
     * from a previous save file.
     *
     * @param tasks The TaskList containing all the current tasks
     *             that was loaded.
     * @return A String object containing the message.
     */
    public String printLoadTasks(TaskList tasks) {
        return "I have loaded your past tasks list!\n"
                + printTaskList(tasks);
    }

    /**
     * Returns the error message when the program encountered an error.
     *
     * @param message The error message from the exception.
     * @return A String object containing the error message.
     */
    public String printErrorMessage(String message) {
        return "Error: " + message + ".";
    }

    /**
     * Returns the message describing the current path of the
     * save file location.
     *
     * @param storage The Storage object whose path is to be returned.
     * @return The current save file path.
     */
    public String printCurrentStoragePath(Storage storage) {
        return "Current path is:\n" + storage.getPath();
    }

    /**
     * Returns the message describing the new path of the save file
     * location and also print out the loaded task list.
     *
     * @param storage The Storage object whose new path is to be returned.
     * @param tasks The TaskList object containing the loaded tasks.
     * @return The new save file path and the list of loaded tasks.
     */
    public String printNewStoragePath(Storage storage, TaskList tasks) {
        return "New path set:\n" + storage.getPath() + "\n\n"
                + printLoadTasks(tasks);
    }
}
