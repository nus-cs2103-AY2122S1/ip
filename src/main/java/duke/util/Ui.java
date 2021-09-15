package duke.util;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Encapsulates user interaction.
 */
public class Ui {
    private static final String WELCOME_MESSAGE= "Hello! I'm Duke "
            + "What can I do for you?\n\n"
            + "Please enter one of the following commands:\n"
            + "- todo <description> \n"
            + "- deadline <description> /by <date> \n  of format dd MMM yyyy\n"
            + "- event <description> /at <date>\n  of format dd MMM yyyy\n"
            + "- list: see task list\n"
            + "- find <keyword> : find tasks with\n  keyword\n"
            + "- delete <task no.>\n"
            + "- done <task no.>\n"
            + "- archive: archive all tasks\n"
            + "- bye: exit duke\n";

    /**
     * Returns welcome message.
     */
    public String showWelcome() {
        return WELCOME_MESSAGE;
    }

    /**
     * Returns string of task added.
     *
     * @param task Task added.
     * @param listLength Length of task list.
     * @return String representation of task list.
     */
    public String showTaskAdded(Task task, int listLength) {
        String taskAdded = String.format("added: " + task.toString()
                + "\nNow you have %s tasks in your list" , listLength);
        return taskAdded;
    }

    /**
     * Returns string of task deleted.
     *
     * @param task Task deleted.
     * @param listLength Length of task list.
     * @return String representation of task deleted.
     */
    public String showTaskDeleted(Task task, int listLength) {
        String taskDeleted = String.format("Noted. I've removed this task:\n" + task.toString()
                + "\nNow you have %s tasks in your list\n" , listLength);
        return taskDeleted;
    }

    /**
     * Returns string of task done.
     *
     * @param task Task set as done.
     * @return String representation of task set as done.
     */
    public String showTaskDone(Task task) {
        String taskDone = "Nice! I've marked this task as done:\n"
                + task.toString();
        return taskDone;
    }

    /**
     * Returns string message for bye.
     *
     * @return String representation for bye.
     */
    public String showBye() {
        String byeMessage = "Bye! Hope to see you again soon!";
        return byeMessage;
    }

    /**
     * Returns head message for list.
     *
     * @return String representation for list head message.
     */
    public String showListMessage() {
        String listMessage = "Here are the tasks in your list:\n";
        return listMessage;
    }

    /**
     * Returns head message for matching list.
     *
     * @return String representation for matching list head message.
     */
    public String showMatchingListMessage() {
        String matchingListMessage = "Here are the matching tasks in your list:\n";
        return matchingListMessage;
    }

    /**
     * Returns string of tasks in list.
     *
     * @return String representation of tasks in list.
     */
    public String getTasks(ArrayList<Task> tasks) {
        String list = "";

        if (tasks.size() == 0) {
            list += "no tasks in list yet...";
        }

        for (int i = 0; i < tasks.size(); i++) {
            list += String.format("\t%s.%s\n", i + 1, tasks.get(i).toString());
        }

        return list;
    }

    public String showArchiveMessage() {
        String message = "All tasks have been archived!";
        return message;
    }


    /**
     * Returns string of error message.
     *
     * @param message Error message.
     * @return String representation of error.
     */
    public String showError(String message) {
        return message;
    }

}
