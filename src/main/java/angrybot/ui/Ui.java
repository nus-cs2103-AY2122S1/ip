package angrybot.ui;

import java.util.ArrayList;
import java.util.List;

import angrybot.task.Task;

/**
 * Encapsulates the handling of user interactions of AngryBot.
 *
 * @author Zhi Bin
 * @version AngryBot Level 10
 */
public class Ui {
    private static final String INDENTATION = "    ";

    private static final String WELCOME_MSG = "Welcome to AngryBot you little shit. What do you want from me?\n"
            + "Type \"help\" if you are dumb and requires help.";
    private static final String FAREWELL_MSG = "Bye bye, I go sleep already. See you again.";

    private static final String HELP_TODO = "todo <description> - Adds a Todo task with the specified description.";
    private static final String HELP_DEADLINE = "deadline <description> </by> <yyyy-mm-dd> <hh:mm>"
            + "- Adds a Deadline task.";
    private static final String HELP_EVENT = "event <description> </by> <yyyy-mm-dd> <hh:mm>"
            + "- Adds a Event.";
    private static final String HELP_DELETE = "delete <task number> - Deletes the task corresponding to the number.";
    private static final String HELP_DONE = "done <task number> - Mark the task corresponding to the number as done.";
    private static final String HELP_LIST = "list - List out all the tasks added.";
    private static final String HELP_FIND = "find <keyword> - Finds all task with description containing the keyword.";
    private static final String HELP_BYE = "bye - Exits the application";
    private static final String HELP_SORT = "sort <reverse> - Sorts the task list.\n"
            + "Adding the optional reverse flag reverse the sorting order.";

    /**
     * Returns farewell message when the user exits AngryBot.
     *
     * @return A farewell message
     */
    public static String showFarewellMessage() {
        return FAREWELL_MSG;
    }

    /**
     * Returns greeting message when the user exits AngryBot.
     *
     * @return A welcome message.
     */
    public static String showGreetingMessage() {
        return WELCOME_MSG;
    }

    /**
     * Returns a formatted message when a task is added from the task list.
     * Message includes the descriptions of the task and number of task in the task list.
     *
     * @param taskLeft Number of task in the task list after adding the task.
     * @param t     The task to be added.
     * @return A formatted message to show that task has been added.
     */
    public String addTaskMessage(int taskLeft, Task t) {
        String s = "Got it. I've added this task:\n" + INDENTATION + "  " + t.checkStatus();
        s += String.format("\nNow you have %d tasks in the list.", taskLeft);
        return s;
    }

    /**
     * Returns a formatted message when a task is deleted from the task list.
     * Message includes the descriptions of the task and number of task in the task list.
     *
     * @param taskLeft Number of task in the task list after deleting the task.
     * @param t     The task to be deleted.
     * @return A formatted message to show that task has been deleted.
     */
    public String deleteTaskMessage(int taskLeft, Task t) {
        String s = "Noted. I've removed this task:\n" + INDENTATION + "  " + t.checkStatus();
        s += String.format("\nNow you have %d tasks in the list.", taskLeft);
        return s;
    }

    /**
     * Returns a formatted message when a task is mark as done.
     * Message includes the descriptions of the task and number of task in the task list.
     *
     * @param t The task to be marked.
     * @return A formatted message to show that task has been mark as done
     */
    public String markDoneMessage(Task t) {
        String s = "Nice! I've marked this task as done:\n   ";
        s += INDENTATION + t.checkStatus();
        return s;
    }

    /**
     * Returns a formatted message showing the list of task, including the description of the task.
     *
     * @param taskList The task list to be printed.
     * @return The string containing the list of tasks
     */
    public String listTasks(ArrayList<Task> taskList) {
        if (taskList.size() > 0) {
            StringBuilder s = new StringBuilder("Here are the tasks in your list:");
            for (int i = 1; i <= taskList.size(); i++) {
                s.append(String.format("\n%s%d. %s", INDENTATION, i, taskList.get(i - 1).checkStatus()));
            }
            return s.toString();
        }
        return "There are no task in your list currently, please add some.";
    }

    /**
     * Shows the list of task containing the tasks related to the keyword provided by user.
     *
     * @param list The list of related tasks.
     * @return A string showing the list of related tasks.
     */
    public String showRelatedTasks(List<Task> list) {
        StringBuilder s = new StringBuilder();

        if (list.size() > 0) {
            s.append("Here are the matching tasks in your list:");
            for (int i = 1; i <= list.size(); i++) {
                s.append(String.format("\n%s%d. %s", INDENTATION, i, list.get(i - 1).checkStatus()));
            }
        } else {
            s.append("There are no matching task in your list.");
        }
        return s.toString();
    }

    /**
     * Shows the sorted list with a message.
     *
     * @param list The sorted list.
     * @return A string with a message and the sorted list.
     */
    public String sortListMessage(ArrayList<Task> list) {
        StringBuilder s = new StringBuilder("I have sorted the list according to the chronological order. "
                + "Here is the sorted list.\n");
        s.append(listTasks(list));
        return s.toString();
    }

    /**
     * Shows a help message with instructions on how the commands work.
     *
     * @return A help message.
     */
    public String showHelpMessage() {
        StringBuilder s = new StringBuilder(addLineBreakAfter("Here are the supported commands."));
        s.append(addIndentationAndLineBreakAfter(HELP_BYE));
        s.append(addIndentationAndLineBreakAfter(HELP_TODO));
        s.append(addIndentationAndLineBreakAfter(HELP_DEADLINE));
        s.append(addIndentationAndLineBreakAfter(HELP_EVENT));
        s.append(addIndentationAndLineBreakAfter(HELP_DELETE));
        s.append(addIndentationAndLineBreakAfter(HELP_DONE));
        s.append(addIndentationAndLineBreakAfter(HELP_LIST));
        s.append(addIndentationAndLineBreakAfter(HELP_FIND));
        s.append(addIndentation(HELP_SORT));
        return s.toString();
    }

    private String addIndentation(String s) {
        return INDENTATION + s;
    }

    private String addLineBreakAfter(String s) {
        return String.format("%s\n", s);
    }

    private String addIndentationAndLineBreakAfter(String s) {
        return addLineBreakAfter(addIndentation(s));
    }
}
