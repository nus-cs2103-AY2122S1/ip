package duke;

/**
 * HelpUi is a static class that handles help statements that the user will see.
 *
 * @author meerian
 */
public class HelpUi {
    /**
     * Returns help text
     *
     * @return the string for help text.
     */
    public static String helpText() {
        return " Help Guide:\n"
            + " /general: general description of Soy Bot\n"
            + " /task: tasks available to add\n"
            + " /commands1-2: list of commands\n";
    }

    /**
     * Returns general description of Soy Bot
     *
     * @return the string for description text.
     */
    public static String generalText() {
        return " General information:\n"
            + "This is SoyBot, a robot that helps you manage your tasks. Current version is: 1.3";
    }

    /**
     * Returns the tasks available
     *
     * @return the string for task text.
     */
    public static String taskText() {
        return "Task information:\n"
            + "You can add tasks to the list at the side."
            + "There are 3 types of tasks available: Todo, Deadline, Event.\n"
            + "/todo, /deadline, /event to find out more.";
    }

    /**
     * Returns description for todo.
     *
     * @return the string for todo text.
     */
    public static String todoText() {
        return "Todo information:\n"
            + "Todo is a task that does not have any date attached.\n"
            + "Format: todo <description of task>\n";
    }

    /**
     * Returns description for deadline.
     *
     * @return the string for deadline text.
     */
    public static String deadlineText() {
        return "Deadline information:\n"
            + "Deadline is a task that have a deadline attached.\n"
            + "Format: deadline <description of task> /by:<date>\n";
    }

    /**
     * Returns description for event.
     *
     * @return the string for event text.
     */
    public static String eventText() {
        return "Event information:\n"
            + "Event is a task that have a date of event attached.\n"
            + "Format: deadline <description of task> /at:<date>\n";
    }

    /**
     * Returns list of commands available.
     *
     * @return the string for some commands.
     */
    public static String commandText1() {
        return "Available commands:\n"
            + "done <number>: marks a task as done\n"
            + "delete <number>: deletes the task\n"
            + "find <text>: returns a list of tasks containing <text>";
    }

    /**
     * Returns list of commands available.
     *
     * @return the string for some commands.
     */
    public static String commandText2() {
        return "Available commands:\n"
            + "save: saves the list\n"
            + "bye: saves the list and close the program";
    }
}
