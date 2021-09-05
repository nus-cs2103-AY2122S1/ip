package duke;

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

    String showGreet() {
        return GREET;
    }

    String showSavedMessage() {
        return SAVE;
    }

    String showMarkedDoneMessage(Task task) {
        return DONE + task;
    }

    String showDeletedMessage(Task task, int size) {
        return "Understood, meow! Deleted this task:\n   "
                + task + "\n" + "Now you have " + size + " tasks in the list.";
    }

    String showAddedMessage(Task task, int size) {
        return "Meow. I've added this task:\n   "
                + task + "\n" + "Now you have " + size + " tasks in the list.";
    }

    String showLoadedMessage() {
        return "Meow. Your tasks have been loaded.";
    }

    String showExitMessage() {
        return EXIT;
    }
}
