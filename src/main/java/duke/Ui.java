package duke;

import duke.task.Task;

/**
 * Ui handles printing messages to UI interface.
 *
 * @author Gabriel Goh
 */
public class Ui {
    private static final String GREET = "Meow-ning!";
    private static final String EXIT = "See you again, meow!";
    private static final String SAVE = "Your list has been saved to DATA/DUKE.TXT";
    private static final String DONE = "Good job, meow! Marked this task as done:\n   ";

    /**
     * Returns greeting string.
     *
     * @return Greeting string
     */
    public String showGreet() {
        return GREET;
    }

    /**
     * Returns task saved message.
     *
     * @return Task saved string
     */
    public String showSavedMessage() {
        return SAVE;
    }

    /**
     * Returns marked done message.
     *
     * @param task Task
     * @return Marked done string
     */
    public String showMarkedDoneMessage(Task task) {
        return DONE + task;
    }

    /**
     * Returns task deleted message.
     *
     * @param task Task
     * @param size Task list size
     * @return Task deleted string
     */
    public String showDeletedMessage(Task task, int size) {
        return "Understood, meow! Deleted this task:\n   "
                + task + "\n" + "Now you have " + size + " tasks in the list.";
    }

    /**
     * Returns task added message.
     *
     * @param task Task
     * @param size Task list size
     * @return Task added string
     */
    public String showAddedMessage(Task task, int size) {
        return "Meow. I've added this task:\n   "
                + task + "\n" + "Now you have " + size + " tasks in the list.";
    }

    /**
     * Returns tasks loaded message.
     *
     * @param filePath File path
     * @return Tasks loaded string
     */
    public String showLoadedMessage(String filePath) {
        return "I've fetched your tasks from " + filePath + "!\n";
    }

    /**
     * Returns exit message.
     *
     * @return Exit message
     */
    String showExitMessage() {
        return EXIT;
    }
}
