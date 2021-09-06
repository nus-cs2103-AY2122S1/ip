package duke.ui;

import duke.constant.EditType;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * The Ui class encapsulates the interface that the user interacts with and what the user sees.
 */
public class Ui {
    private static final String GENERAL_HELP_MESSAGE =
            "Click on any of the buttons below to find out more about each command.\n"
            + "Alternatively, you may type 'help <command>'";

    private static final String BYE_COMMAND_HELP_MESSAGE = "Use this command to exit Duke.";

    private static final String LIST_COMMAND_HELP_MESSAGE = "Use this command to tell Duke to list all of your tasks.";

    private static final String TODO_COMMAND_HELP_MESSAGE =
            "The todo command lets you add a generic task to your task list.\n"
            + "Tasks in the 'todo' category will be marked with a 'T'\n"
            + "Usage: todo <task description>\n"
            + "Example: todo Buy groceries";

    private static final String DEADLINE_COMMAND_HELP_MESSAGE =
            "The deadline command lets you add a task with a deadline to your task list.\n"
                    + "Tasks in the 'deadline' category will be marked with a 'D'\n"
                    + "Usage: deadline <task description> /by <date in YYYY-MM-DD format>\n"
                    + "Example: deadline Math assignment /by 2021-10-10";

    private static final String EVENT_COMMAND_HELP_MESSAGE =
            "The event command lets you add an event to your task list.\n"
                    + "Tasks in the 'event' category will be marked with a 'E'\n"
                    + "Usage: event <task description> /at <date in YYYY-MM-DD format>\n"
                    + "Example: event Meeting /at 2021-05-22";

    private static final String DONE_COMMAND_HELP_MESSAGE =
            "The done command lets you mark a task in your task list as completed.\n"
            + "Completed tasks will be marked with an 'X'\n"
            + "Usage: done <task number as shown in task list>\n"
            + "Example: done 3";

    private static final String DELETE_COMMAND_HELP_MESSAGE =
            "The delete command lets you remove a task from your task list.\n"
                    + "Usage: delete <task number as shown in task list>\n"
                    + "Example: delete 3";

    private static final String FIND_COMMAND_HELP_MESSAGE =
            "The find command lets you search for tasks containing your keyword of interest.\n"
                    + "Usage: find <keyword>"
                    + "Example: find book";

    private static final String INVALID_COMMAND_ERROR_MESSAGE = "Invalid command. List of valid commands include:\n"
            + "list | todo | deadline | event | done | delete | find | bye";

    /**
     * Returns a string to be displayed when the user starts Duke.
     *
     * @return A string containing Duke's welcome message.
     */
    public String getStartMessage() {
        return "Hello! I'm Duke\n"
                + "What can I do for you?";
    }

    /**
     * Returns a string to be displayed when the user exits Duke.
     *
     * @return A string containing Duke's exit message.
     */
    public String getGoodbyeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns a string to display a task list's contents to the user.
     *
     * @param taskList The task list to be displayed.
     * @return A string containing the task list's contents, formatted to be displayed to the user.
     */
    public String formatTaskList(TaskList taskList) {
        if (taskList.getNumberOfTasks() < 1) {
            return "You currently have no tasks in your list";
        } else {
            return "Here are the tasks in your list:\n"
                    + taskList.toString();
        }
    }

    /**
     * Returns a string to display all tasks matching the user's keyword search, if any.
     *
     * @param taskList The task list containing the tasks that match the keyword searched.
     * @return A string containing the matching tasks, formatted to be displayed to the user.
     * If there are no matching tasks, a standard message will be displayed to the user.
     */
    public String formatMatchingTasks(TaskList taskList) {
        if (taskList.getNumberOfTasks() < 1) {
            return "No matching tasks were found in your list.";
        } else {
            return "Here are the matching tasks I found in your list:\n"
                    + taskList.toString();
        }
    }

    /**
     * Returns a string to display a task that has been edited to the user. If the task was deleted,
     * the number of remaining tasks on the task list is displayed as well.
     *
     * @param editedTask The task that has been edited.
     * @param numberOfTasks The number of tasks in the user's task list.
     * @param editType The edit done to the task.
     * @return A string containing the edited task, formatted to be displayed to the user.
     * If the task was deleted, the number of remaining tasks on the task list is displayed as well.
     */
    public String formatEditedTask(Task editedTask, int numberOfTasks, EditType editType) {
        switch (editType) {
        case DONE:
            return "Nice! I've marked this task as done:\n"
                    + editedTask.toString();
        case DELETE:
            return "Got it! I've removed this task from the list:\n"
                    + editedTask.toString() + '\n'
                    + "Now you have "
                    + numberOfTasks + (numberOfTasks == 1 ? " task" : " tasks") + " in the list.";
        default:
            throw new DukeException("Error in updating edited task.");
        }
    }

    /**
     * Returns a string to display a newly added task to the user,
     * along with the number of tasks in the user's task list.
     *
     * @param newTask The recently added task.
     * @param numberOfTasks The number of tasks in the user's task list.
     * @return A string containing the new task and the current number of tasks in the user's task list.
     */
    public String formatAddedTask(Task newTask, int numberOfTasks) {
        return "Got it. I've added this task:\n"
                + newTask + '\n'
                + "Now you have "
                + numberOfTasks + (numberOfTasks == 1 ? " task" : " tasks") + " in the list.";
    }

    /**
     * Returns a string to display an error message to the user if Duke encounters one.
     *
     * @param e The Duke exception encountered.
     * @return A string containing the error encountered.
     */
    public String formatErrorMessage(DukeException e) {
        return e.toString();
    }

    public String getGeneralHelpMessage() {
        return GENERAL_HELP_MESSAGE;
    }

    public String getHelpMessageForCommand(String command) {
        switch (command) {
        case "bye":
            return BYE_COMMAND_HELP_MESSAGE;
        case "list":
            return LIST_COMMAND_HELP_MESSAGE;
        case "todo":
            return TODO_COMMAND_HELP_MESSAGE;
        case "deadline":
            return DEADLINE_COMMAND_HELP_MESSAGE;
        case "event":
            return EVENT_COMMAND_HELP_MESSAGE;
        case "done":
            return DONE_COMMAND_HELP_MESSAGE;
        case "delete":
            return DELETE_COMMAND_HELP_MESSAGE;
        case "find":
            return FIND_COMMAND_HELP_MESSAGE;
        default:
            throw new DukeException(INVALID_COMMAND_ERROR_MESSAGE);
        }
    }
}
