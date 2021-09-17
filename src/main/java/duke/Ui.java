package duke;

/**
 * To deal with interactions with the user.
 */
public class Ui {

    /**
     * Prints the welcome message.
     */
    public String showWelcome() {
        return "Guten Tag. I'm Dwight Schrute.\n" +
                "Type \"help\" if you are clueless.";
    }

    /**
     * Prints the given task.
     *
     * @param task
     * @return
     */
    public String printTask(Task task) {
        return task.toString() + "\n";
    }

    /**
     * Prints the number of tasks on hand.
     *
     * @param tasks
     * @return
     */
    public String listTaskNumber(TaskList tasks) {
        if (tasks.size() <= 0) {
            return "You now have no tasks in the list.\n";
        }
        return "You now have " + tasks.size() + " tasks in the list.\n";
    }

    /**
     * Prints a delete message.
     *
     * @param task
     * @return
     */
    public String printDeleteMessage(Task task) {
        return "Noted. I've removed this task:\n" + task + "\n";
    }

    /**
     * Prints the done message.
     *
     * @param task
     * @return
     */
    public String printDoneTask(Task task) {
        return "Noted. I've marked this task as done:\n" + task;
    }

    /**
     * Prints a message when a task is not found with given keyword.
     *
     * @param keyword
     * @return
     */
    public String printNoKeyword(String keyword) {
        return "There were no tasks found with keyword: " + keyword;
    }

    /**
     * Prints the list of tasks that match the given keyword.
     *
     * @param message
     * @return
     */
    public String printFoundKeyword(String message) {
        return "Here are the tasks in your list that match the keyword:\n" + message;
    }

    /**
     * Prints all existing tasks.
     *
     * @param tasks
     * @return
     */
    public String listTasks(TaskList tasks) {
        if (tasks.size() <= 0) {
            return "There are currently no tasks in your list.";
        }
        String returnMessage = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            int j = i + 1;
            returnMessage += (j + ". " + tasks.get(i) + "\n");
        }
        return returnMessage;
    }

    /**
     * Prints the error message based on the given error.
     *
     * @param errorMessage The error message input
     * @return
     */
    public String printErrorMessage(String errorMessage) {
        return "FALSE!\n" + errorMessage;
    }

    /**
     * Prints the closing message.
     *
     * @return
     */
    public String saveMessage() {
        return "Saved. I have everything stored in my brain";
    }

    /**
     * Prints a message to confirm task is added.
     *
     * @return
     */
    public String addedTaskMessage() {
        return "Got it. I've added this task: \n";
    }

    /**
     * Prints the current statistics.
     *
     * @return
     */
    public String printStatistics(Statistics stats) {
        String returnMessage = "";
        returnMessage += "Total Tasks Done: " + stats.getTasksDone() + "\n";
        returnMessage += "Total Tasks Added: " + stats.getTasksAdded() + "\n";
        returnMessage += "Total Tasks Deleted: " + stats.getTasksDeleted() + "\n";
        return returnMessage;
    }

    /**
     * Prints the help message.
     *
     * @return
     */
    public String helpMessage() {
        String returnMessage = "";
        returnMessage += "Here are my commands:\n";
        returnMessage += "Todo: \"todo [task]\"\n";
        returnMessage += "Deadline: \"deadline [task] /by YYYY-MM-DD\"\n";
        returnMessage += "Event: \"event [task] /at YYYY-MM-DD\"\n";
        returnMessage += "Done: \"done [task number]\"\n";
        returnMessage += "List: \"list\"\n";
        returnMessage += "Delete: \"delete [task number]\"\n";
        returnMessage += "Find: \"find [keyword]\"\n";
        returnMessage += "Delete: \"delete [task number]\"\n";
        returnMessage += "Save: \"save\"\n";
        returnMessage += "Exit: \"bye\"\n";
        return returnMessage;

    }
    
}
