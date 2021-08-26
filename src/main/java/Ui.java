/**
 * Class to deal with interactions with the user
 */
public class Ui {
    public static void showLoadingError() {
        System.out.println("There was an error loading from the save file!\n" +
                "Initializing an empty file...");
    }

    public static void showTasksSaved() {
        System.out.println("Your tasks have been saved!");
    }

    public static void welcomeMessage() {
        System.out.println("Hello! I'm Duke.\n" + "What can I do for you?");
    }

    public static void goodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void taskDoneMessage(Task task, boolean isDone) {
        if (isDone) {
            System.out.println("Nice! I've marked this task as done:\n" + task);
        } else {
            System.out.println("I've marked this task as undone:\n" + task);
        }
    }

    public static void noTasksMessage() {
        System.out.println("There are currently no tasks!");
    }

    /**
     * Prints a message specifying what task has been removed.
     *
     * @param task the task that is being removed
     * @param size the current size of the task list
     */
    public static void removeTaskMsg(Task task, int size) {
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
    public static void addTaskMsg(Task task, int size) {
        System.out.println(
                "I have added the task!\n  "
                        + task
                        + "\nNow you have " + size + " tasks left!");
    }
}
