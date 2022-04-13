package duke;

/**
 * Class to deal with interactions with the user
 */
public class Ui {
    /**
     * Returns a loading error message.
     */
    public static String getLoadingError() {
        return "There was an error loading from the save file!\n"
                + "Initializing an empty file...";
    }

    /**
     * Returns a tasks saved message.
     */
    public static String getTasksSaved() {
        return "Your tasks have been saved!";
    }

    /**
     * Returns a welcome message.
     */
    public static String getWelcomeMessage() {
        return "Hello! I'm Duke.\n"
                + "What can I do for you?";
    }

    /**
     * Returns a goodbye message.
     */
    public static String getGoodbyeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns different message depending on whether a task was checked or unchecked.
     *
     * @param task task to be marked
     * @param isDone boolean flag for whether the task is done or not
     * @return The message to be returned to the user
     */
    public static String getTaskDoneMessage(Task task, boolean isDone) {
        if (isDone) {
            return "Nice! I've marked this task as done:\n" + task;
        } else {
            return "I've marked this task as undone:\n" + task;
        }
    }

    public static String getNoTasksMessage() {
        return "There are currently no tasks!";
    }

    /**
     * Returns a message specifying what task has been removed.
     *
     * @param task the task that is being removed
     * @param size the current size of the task list
     * @return Message to be returned to the user
     */
    public static String getRemoveTaskMsg(Task task, int size) {
        return "I have removed the task:\n  "
                + task
                + "\nNow you have " + size + " tasks left!";
    }

    /**
     * Returns a message specifying what task has just been added.
     *
     * @param task the task that is being removed
     * @param size the current size of the task list
     * @return Message to be returned to the user
     */
    public static String getAddTaskMsg(Task task, int size) {
        return "I have added the task!\n  "
                + task
                + "\nNow you have " + size + " tasks left!";
    }

    public static String getDeleteError() {
        return "Sorry! That task could not be deleted.";
    }
}
