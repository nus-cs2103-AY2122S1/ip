package chadbot.io;

import java.time.LocalDateTime;

import chadbot.ChadException;
import chadbot.Command;
import chadbot.data.TaskList;
import chadbot.task.Task;

public class Ui {

    /**
     * Returns a new Ui object.
     */
    public Ui() {
    }

    private static String format(String... inputs) {
        StringBuilder str = new StringBuilder();
        for (String s : inputs) {
            str.append(s).append("\n");
        }
        return str.toString();
    }

    /**
     * Returns a formatted String that signifies Chad is launched.
     *
     * @return String that shows a welcome message.
     */
    public static String showWelcome() {
        return format("I am Chad.", "What can I do for you?");
    }

    /**
     * Returns a formatted String that signifies that a user fails to input an int when required.
     *
     * @return String that shows a integer error.
     */
    public String showIntError() {
        return format("The index of a task must be specified.");
    }

    /**
     * Returns a formatted String that signifies that the saved file cannot be loaded.
     *
     * @return String that shows a loading error.
     */
    public String showLoadingError() {
        return format("Task description cannot be found in database.", "A new file will be created.");
    }

    /**
     * Returns a formatted String that signifies that the data file cannot be saved.
     *
     * @return String that shows saving error.
     */
    public String showSavingError() {
        return format("File cannot be saved.");
    }

    /**
     * Returns a formatted String containing the ChadException error message.
     *
     * @param e ChadException to whose error message will be returned.
     * @return String that shows the ChadExeception error message.
     */
    public String showChadException(ChadException e) {
        return format(e.toString());
    }

    /**
     * Returns a formatted String that signifies that the user inputs the date and time in a wrong format.
     *
     * @return String that shows a date time format error.
     */
    public String showDateTimeException() {
        return format("Date'T'time inputted is not of valid format: YYYY-MM-DDThh:mm");
    }

    /**
     * Returns a formatted String that shows the outcome of a the command the user inputted.
     * Used for commands involving a single word or for addition of tasks.
     *
     * @param c Type of command the user has inputted.
     * @param list Current TaskList used.
     * @return String that shows the outcome of a the command the user inputted.
     */
    public String displayAddOrSingleInputCommand(Command.Commands c, TaskList list) {
        switch (c) {
        case HELP:
            return format("bye : Closes the programme\n",
                    "list : Returns all tasks added\n",
                    "todo <description> : Adds a todo task\n",
                    "find <description> : Returns all tasks with <description> in their description\n",
                    "event <description> /at <time: YYYY-MM-DDThh:mm> : Adds an event at time <time>\n",
                    "deadline <description> /by <time: YYYY-MM-DDThh:mm> : Adds a task with deadline at time <time>\n",
                    "done <index> : Marks the task at <index> as done\n",
                    "delete <index> : deletes the task at <index>\n",
                    "at <time: YYYY-MM-DDThh:mm> : Returns all events up till <time>\n",
                    "by <time: YYYY-MM-DDThh:mm> : Returns all tasks with deadline due before or at <time>\n",
                    "all <time: YYYY-MM-DDThh:mm> : Returns all timed tasks with times up till <time>");
        case LIST:
            return format(list.returnItems());
        case TODO:
            //Fallthrough
        case EVENT:
            //Fallthrough
        case DEADLINE:
            return format("Noted. I've added this task:",
                    "  " + list.returnLastTask(), list.returnItemCount(0));
        default:
            return "Oops an error has occurred";
        }
    }

    /**
     * Returns a formatted String that that shows the outcome of a the command the user inputted.
     * Used for commands involving involving retrieval of tasks based on date and time.
     *
     * @param c Type of command the user has inputted.
     * @param list Current TaskList used.
     * @param dt Date and Time used by the command.
     * @return String that shows the outcome of a the command the user inputted.
     */
    public String displayDateTimeFilteredCommand(Command.Commands c, TaskList list, LocalDateTime dt) {
        switch (c) {
        case AT:
            return format(list.getEventsAt(dt));
        case BY:
            return format(list.getDeadlinesBy(dt));
        case ALL:
            return format(list.getAllBy(dt));
        default:
            return "Oops an error has occurred.";
        }
    }

    /**
     * Returns a formatted String that shows the outcome of a the command the user inputted.
     * Used for commands involving modification of tasks.
     *
     * @param c Type of command the user has inputted.
     * @param t Task modified by the command.
     * @param list Current TaskList used.
     * @return String that shows the outcome of a the command the user inputted.
     */
    public String displayTaskModificationCommand(Command.Commands c, Task t, TaskList list) {
        switch (c) {
        case DONE:
            return format("Noted. I've marked this task as done:", t.toString());
        case DELETE:
            return format("Noted. I've removed this task:", t.toString(), list.returnItemCount(0));
        default:
            return "Oops an error has occurred.";
        }
    }

    /**
     * Returns a formatted String that shows the outcome of a the command the user inputted.
     * Used for commands involving searching of tasks.
     *
     * @param c Type of command the user has inputted.
     * @param toFind The String to search for among the tasks.
     * @param list Current TaskList used.
     * @return String that shows the outcome of a the command the user inputted.
     */
    public String displayFindTaskCommand(Command.Commands c, String toFind, TaskList list) {
        return format(list.returnFoundItem(toFind));
    }
}
