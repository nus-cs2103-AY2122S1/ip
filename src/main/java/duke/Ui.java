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

    public String showGreet() {
        return GREET;
    }

    public String showSavedMessage() {
        return SAVE;
    }

    public String showMarkedDoneMessage(Task task) {
        return DONE + task;
    }

    public String showDeletedMessage(Task task, int size) {
        return "Understood, meow! Deleted this task:\n   "
                + task + "\n" + "Now you have " + size + " tasks in the list.";
    }

    public String showAddedMessage(Task task, int size) {
        return "Meow. I've added this task:\n   "
                + task + "\n" + "Now you have " + size + " tasks in the list.";
    }

    public String showLoadedMessage(String filePath) {
        return "I've fetched your tasks from " + filePath + "!\n";
    }

    String showExitMessage() {
        return EXIT;
    }
}
