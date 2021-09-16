package poseidon;

import java.util.ArrayList;

import poseidon.task.Task;

public class Ui {

    /**
     * Constructs a Ui object.
     */
    public Ui() {

    }

    /**
     * Returns a human-readable string that conveys a welcome message from the bot to the user.
     *
     * @return String welcome message.
     */
    public String getWelcomeMessage() {
        String logo = "                   /\\                   \n"
                + "           ||\\    /||\\    /||           \n"
                + "           \\\\_\\  ''||''  /_//           \n"
                + "            \\\\     ||     //            \n"
                + "             \\\\    ||    //             \n"
                + "              \\\\   ||   //              \n"
                + "               \\\\__||__//               \n"
                + "                   ||                   \n"
                + "                   ||                   \n"
                + "                   ||                   \n"
                + "                   ||                   \n"
                + "           P O S E || D O N             \n"
                + "                   ||                   \n"
                + "                   ||                   \n\n";
        String helloMsg = "Hello! I'm POSEIDON\n"
                + "What can I do for you?";
        return logo + helloMsg;
    }

    /**
     * Returns a human-readable string representation of a list.
     *
     * @param tasks List of tasks to be converted to string.
     * @return String list representation.
     */
    public String getListMessage(ArrayList<Task> tasks) {
        StringBuilder message = new StringBuilder();
        if (tasks.size() > 0) {
            message.append("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                message.append("  " + (i + 1) + ". " + tasks.get(i) + "\n");
            }
        } else {
            message.append("There are no tasks in your list.");
        }
        return message.toString();
    }

    /**
     * Returns a human-readable string representation of a list resulting from a find operation.
     *
     * @param tasks List of tasks to be converted to string.
     * @return String list representation with added "find" message.
     */
    public String showFindList(ArrayList<Task> tasks) {
        StringBuilder message = new StringBuilder();
        if (tasks.size() > 0) {
            message.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                message.append("  " + (i + 1) + ". " + tasks.get(i) + "\n");
            }
        } else {
            message.append("There are no matching tasks in your list.");
        }
        return message.toString();
    }

    /**
     * Returns a human-readable string that conveys that an unknown/non-existent command has been encountered.
     *
     * @return String error message.
     */
    public String showCommandFail() {
        return "I didn't get that. Please try again.";
    }

    /**
     * Returns a human-readable string that conveys that the given error message.
     *
     * @param message Error message to be shown.
     * @return String error message.
     */
    public String showError(String message) {
        return "Oops... Something's wrong.\n" + message;
    }

    /**
     * Returns a string that conveys the given message.
     *
     * @param message Message to be shown.
     * @return String message.
     */
    public String showMessage(String message) {
        return message;
    }

    /**
     * Returns a string that conveys that a storage error has been encountered.
     *
     * @return String error message.
     */
    public String showStorageError() {
        return showError("File for storage of tasks could not be accessed or written.");
    }

    /**
     * Returns a string that conveys a goodbye message from the bot to the user.
     *
     * @return String goodbye message.
     */
    public String showGoodbye() {
        return "Bye. Hope to see you again soon!";
    }
}
