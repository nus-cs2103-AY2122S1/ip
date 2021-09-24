package poseidon.ui;

import java.util.ArrayList;

import poseidon.command.AddDeadline;
import poseidon.command.AddEvent;
import poseidon.command.AddTodo;
import poseidon.command.Bye;
import poseidon.command.Delete;
import poseidon.command.Done;
import poseidon.command.Find;
import poseidon.command.Help;
import poseidon.command.List;
import poseidon.command.Sort;
import poseidon.task.Task;

/**
 * Represents an {@code Ui} object that contains all the functionality for creating responses from the Bot.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class Ui {

    private static final String LOGO = "                   /\\                   \n"
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
    private static final String HELLO_MSG = "Hello! I'm POSEIDON\n"
            + "What can I do for you?\n"
            + "Try " + Help.CMD_USER_FORMAT + ".";
    private static final String LIST_TASKS_INTRO_MSG = "Here are the tasks in your list:\n";
    private static final String LIST_TASKS_EMPTY_MSG = "There are no tasks in your list.";
    private static final String LIST_TASKS_FIND_INTRO_MSG = "Here are the matching tasks in your list:\n";
    private static final String LIST_TASKS_FIND_EMPTY_MSG = "There are no matching tasks in your list.";
    private static final String COMMAND_FAIL_MSG = "I didn't get that. Please try again.";
    private static final String ERROR_INTRO_MSG = "Oops... Something's wrong.\n";
    private static final String GOODBYE_MSG = "Bye. Hope to see you again soon!";
    private static final String HELP_MSG = "Here's a list of all the commands I can understand:\n\n"
            + "FOR HELP - " + Help.CMD_USER_FORMAT + "\n\n"
            + "ADD TODO - " + AddTodo.CMD_USER_FORMAT + "\n\n"
            + "ADD DEADLINE - " + AddDeadline.CMD_USER_FORMAT + "\n\n"
            + "ADD EVENT - " + AddEvent.CMD_USER_FORMAT + "\n\n"
            + "MARK TASK DONE - " + Done.CMD_USER_FORMAT + "\n\n"
            + "DELETE TASK - " + Delete.CMD_USER_FORMAT + "\n\n"
            + "LIST TASKS - " + List.CMD_USER_FORMAT + "\n\n"
            + "SORT TASKS - " + Sort.CMD_USER_FORMAT + "\n\n"
            + "FIND CONTENT - " + Find.CMD_USER_FORMAT + "\n\n"
            + "EXIT - " + Bye.CMD_USER_FORMAT;

    /**
     * Constructs a Ui object.
     */
    public Ui() {

    }

    /**
     * Returns a human-readable {@code String} that conveys a welcome message from the Bot to the User.
     *
     * @return {@code String} welcome message.
     */
    public String getWelcomeMessage() {
        return LOGO + HELLO_MSG;
    }

    /**
     * Returns a human-readable {@code String} representation of a list.
     *
     * @param tasks List of {@code Task}s to be converted to {@code String}.
     * @return {@code String} list representation.
     */
    public String getListMessage(ArrayList<Task> tasks) {
        StringBuilder message = new StringBuilder();
        if (tasks.size() > 0) {
            message.append(LIST_TASKS_INTRO_MSG);
            for (int i = 0; i < tasks.size(); i++) {
                message.append("  " + (i + 1) + ". " + tasks.get(i) + "\n");
            }
        } else {
            message.append(LIST_TASKS_EMPTY_MSG);
        }
        return message.toString();
    }

    /**
     * Returns a human-readable {@code String} representation of a list resulting from a find operation.
     *
     * @param tasks List of {@code Task}s to be converted to {@code String}.
     * @return {@code String} list representation with added "find" message.
     */
    public String showFindList(ArrayList<Task> tasks) {
        StringBuilder message = new StringBuilder();
        if (tasks.size() > 0) {
            message.append(LIST_TASKS_FIND_INTRO_MSG);
            for (int i = 0; i < tasks.size(); i++) {
                message.append("  " + (i + 1) + ". " + tasks.get(i) + "\n");
            }
        } else {
            message.append(LIST_TASKS_FIND_EMPTY_MSG);
        }
        return message.toString();
    }

    /**
     * Returns a human-readable {@code String} that conveys that an unknown/non-existent command has been encountered.
     *
     * @return {@code String} error message.
     */
    public String showCommandFail() {
        return COMMAND_FAIL_MSG;
    }

    /**
     * Returns a human-readable {@code String} that conveys that the given error message.
     *
     * @param message Error message to be shown.
     * @return {@code String} error message.
     */
    public String showError(String message) {
        return ERROR_INTRO_MSG + message;
    }

    /**
     * Returns a {@code String} that conveys the given message.
     *
     * @param message Message to be shown.
     * @return {@code String} message.
     */
    public String showMessage(String message) {
        return message;
    }

    /**
     * Returns a {@code String} that conveys a goodbye message from the Bot to the User.
     *
     * @return {@code String} goodbye message.
     */
    public String showGoodbye() {
        return GOODBYE_MSG;
    }

    /**
     * Returns a {@code String} that contains a list of all the commands as a help to the User.
     *
     * @return {@code String} help message.
     */
    public String showHelp() {
        return HELP_MSG;
    }
}
