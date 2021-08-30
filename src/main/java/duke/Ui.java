package duke;

/**
 * Class to deal with interactions with the user
 */
public class Ui {
    /**
     * Prints a loading error message.
     */
    public static void showLoadingError() {
        System.out.println("There was an error loading from the save file!\n"
                + "Initializing an empty file...");
    }

    /**
     * Prints a tasks saved message.
     */
    public static void showTasksSaved() {
        System.out.println("Your tasks have been saved!");
    }

    /**
     * Prints a welcome message.
     */
    public static void showWelcomeMessage() {
        System.out.println("Hello! I'm Duke.\n"
                + "What can I do for you?");
    }

    /**
     * Prints a goodbye message.
     */
    public static void showGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints different message depending on whether a task was checked or unchecked.
     *
     * @param task task to be marked
     * @param isDone boolean flag for whether the task is done or not
     */
    public static void showTaskDoneMessage(Task task, boolean isDone) {
        if (isDone) {
            System.out.println("Nice! I've marked this task as done:\n" + task);
        } else {
            System.out.println("I've marked this task as undone:\n" + task);
        }
    }

    public static void showNoTasksMessage() {
        System.out.println("There are currently no tasks!");
    }

    /**
     * Prints a message specifying what task has been removed.
     *
     * @param task the task that is being removed
     * @param size the current size of the task list
     */
    public static void showRemoveTaskMsg(Task task, int size) {
        System.out.println("I have removed the task:\n  "
                + task
                + "\nNow you have " + size + " tasks left!");
    }

    /**
     * Prints a message specifying what task has just been added.
     *
     * @param task the task that is being removed
     * @param size the current size of the task list
     */
    public static void showAddTaskMsg(Task task, int size) {
        System.out.println("I have added the task!\n  "
                + task
                + "\nNow you have " + size + " tasks left!");
    }
}
