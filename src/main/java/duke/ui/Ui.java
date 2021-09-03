package duke.ui;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Encapsulates the handling of user interactions of duke.
 *
 * @author Zhi Bin
 * @version Duke Level 10
 */
public class Ui {
    private static final String INDENTATION = "    ";
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    /**
     * Introduction message with duke's logo.
     */
    private static final String INTRO = "Hello from\n"
            + INDENTATION + " ____        _        \n"
            + INDENTATION + "|  _  \\ _   _| | _____ \n"
            + INDENTATION + "| | | | | | | |/ / _ \\\n"
            + INDENTATION + "| |_| | |_| |   <  __/\n"
            + INDENTATION + "|____/ \\__,_|_|\\_\\___|\n"
            + INDENTATION + "What can I do for you?";

    /**
     * Prints the argument with indentation and horizontal lines.
     *
     * @param s The message to be printed on screen in between horizontal lines and with indentation.
     */
    public static void printMessageWithFormat(String s) {
        System.out.println(INDENTATION + HORIZONTAL_LINE);
        System.out.println(INDENTATION + s);
        System.out.println(INDENTATION + HORIZONTAL_LINE);
    }


    /**
     * Returns farewell message when the user exits duke.
     *
     * @return A farewell message
     */
    public String farewellMessage() {
        return "Bye bye, i go sleep already. See you again.";
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
    public String getRelatedTasks(ArrayList<Task> list) {
        StringBuilder s = new StringBuilder("");

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
}
