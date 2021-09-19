package duke.ui;

import duke.task.TaskList;

/**
 * Represents a User Interface (Ui). The user will be greeted by Duke as they start and end their interactions with it.
 */
public class Ui {
    /**
     * Returns a welcome message to the user
     *
     * @return a welcome message
     */
    public String welcomeMessage() {
        return "Hello! I'm Duke\n" + "What can I do for you?\n";
    }

    /**
     * Returns a goodbye message to the user
     *
     * @return a goodbye message
     */
    public String goodbyeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns a message letting the user know that their ToDo task has been successfully added.
     *
     * @param type type of the task: ToDo
     * @param doneStatus whether the task was done or not
     * @param taskDescription full description of the task
     * @param tList list of tasks
     * @return message saying the task has been successfully added
     */
    public String todoAddedMessage(
            String type, String doneStatus, String taskDescription, TaskList tList) {
        return "     Got it. I've added this task:\n"
                + "       " + "[" + type + "]"
                + doneStatus + " "
                + taskDescription + "\n"
                + "     Now you have " + tList.length() + " tasks in the list.\n";
    }

    /**
     * Returns a message letting the user know that their Deadline/Event task has been
     * successfully added
     *
     * @param type type of the task: Deadline/Event
     * @param doneStatus whether the task was done or not
     * @param taskName name of the task
     * @param date completion date of the task
     * @param time completion time of the task
     * @param tList list of tasks
     * @return message saying that the task has been successfully added
     */
    public String deadlineOrEventAddedMessage(
            String type, String doneStatus, String taskName, String date, int time, TaskList tList) {
        return "     Got it. I've added this task:\n"
                + "       " + "[" + type + "]"
                + doneStatus + " "
                + taskName + " by "
                + date + ", "
                + time + "\n"
                + "     Now you have " + tList.length() + " tasks in the list.\n";
    }

    public String notesAddedMessage(String taskName, String notes) {
        return "     Got it. I've added notes to this task:\n\n"
                + "     " + taskName + "\n"
                + "     " + notes;
    }
}
