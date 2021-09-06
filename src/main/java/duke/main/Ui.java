package duke.main;

import duke.task.Task;
import duke.task.TaskList;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;


/**
 * Handles the user interface.
 */
public class Ui {
    private Map<String, Consumer<String>> uiCommands;

    /**
     * Overloaded Constructor for Duke UI
     */
    public Ui() {
    }

    /**
     * Overloaded constructor for Duke UI
     */
    public Ui(Map<String, Consumer<String>> uiCommands) {
        this.uiCommands = uiCommands;
    }

    /**
     * Returns a response to the add task command.
     *
     * @param task     Task added.
     * @param taskList TaskList to be displayed.
     * @return String response to added task.
     */
    public static String getAddTaskMessage(Task task, TaskList taskList) {
        return "\t Got it. I've added this task:\n"
                + "\t \t " + task + taskList.getTaskListSummary();
    }

    /**
     * Returns a response to the clear command.
     *
     * @return String tasks cleared message.
     */
    public static String getClearTasksMessage() {
        return "Your previous tasks have been cleared.";
    }

    /**
     * Returns a response to the remove task command.
     *
     * @param task     Task removed.
     * @param taskList TaskList to be displayed.
     * @return String response to removed task.
     */
    public static String getRemoveTaskMessage(Task task, TaskList taskList) {
        return "\t Got it. I've removed this task:\n"
                + "\t \t " + task + taskList.getTaskListSummary();
    }

    /**
     * Returns a response to the mark task as done command.
     *
     * @param task Task marked as done.
     * @return String response to completed task.
     */
    public static String getTaskDoneMessage(Task task) {
        return "\t Nice! I've marked this task as done:\n" + "\t\t " + task + "\n";
    }


    /**
     * Returns a response to the taskList reset command.
     *
     * @return String response to reset task.
     */
    public static String getResetTasksMessage() {
        return "\tClearing tasks...\n" + "\tYou can now start anew...\n";
    }

    /**
     * Returns error when user input is empty.
     *
     * @return String message.
     */
    public String getEmptyInputMessage() {
        return "\tTake your time :)\n";
    }

    /**
     * Returns error when user command is not found.
     *
     * @return String message.
     */
    public String getUnknownCommandMessage(String command) {
        assert (!command.equals(""));
        return "\tI don't understand " + command + " (yet...)\n";
    }

    /**
     * Returns the TaskList.
     *
     * @param taskList to display.
     * @return String message.
     */
    public String getTaskListSummary(TaskList taskList) {
        if (taskList.isEmpty()) {
            return "\tYou haven't added any tasks yet\n";
        } else {
            return taskList.toString();
        }
    }

    /**
     * Returns the message in the DukeException.
     *
     * @param exception message to display.
     */
    public void showDukeException(Exception exception) {
        uiCommands.get("showDukeResponse").accept(exception.getMessage());
    }

    /**
     * Greets an existing user.
     *
     * @param tasks User's existing tasks to be displayed with the greeting.
     */
    public void greetWithFamiliarity(TaskList tasks) {
        String greeting = "\tNice to see you again.\n";
        greeting += tasks.getTaskListSummary() + "\n";

        if (!tasks.isEmpty()) {
            greeting += tasks;
        }
        uiCommands.get("showDukeResponse").accept(greeting);
    }

    /**
     * Returns matching tasks.
     *
     * @param matchingTasks to be displayed.
     * @return String message.
     */
    public String getMatchingTasksSummary(List<Task> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            return "\tNo matching tasks found!\n";
        } else {
            return "\tHere are the matching tasks from your list:\n"
                    + TaskList.enumerateTasks(matchingTasks);
        }
    }

    /**
     * Terminates the program with a parting message.
     *
     * @return String parting message.
     */
    public String exitWithGoodbye() {
        new Thread(() -> uiCommands.get("exit").accept("")).start();
        return "Hope to see you soon!!";
    }

}
