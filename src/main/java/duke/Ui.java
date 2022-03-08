package duke;

/**
 * Handles the UI aspect of the program, crafting messages to the user.
 */
public class Ui {
    /** A line demarcating the start and end of the message. */
    private static final String LINE = "    -------------------------------------------------\n";
    /** An indentation for each line of the message (within the dotted lines). */
    private static final String INDENTATION = "      ";

    /**
     * Composes the response to the user based on the strings passed to it.
     *
     * @param lines A variable number of lines to be printed as a response to the user.
     */
    public static String getResponse(String ... lines) {
        String stringToPrint = "";
        stringToPrint += LINE;
        for (String line : lines) {
            stringToPrint += (INDENTATION + line + "\n");
        }
        stringToPrint += LINE;
        return stringToPrint;
    }

    /**
     * Concatenates together the list of available commands to form a help message to the user.
     *
     * @return The help message to guide the user.
     */
    public static String getHelpMessage() {
        String stringToPrint = "";
        stringToPrint += LINE;
        stringToPrint += (INDENTATION + "Here are a list of commands you can use:\n");
        stringToPrint += (INDENTATION + "1. list:" + INDENTATION
                + "shows a list of all the tasks you currently have\n");
        stringToPrint += (INDENTATION + "2. todo task_name:" + INDENTATION
                + "creates a new ToDo task with the name given\n");
        stringToPrint += (INDENTATION + "3. event task_name /at date_time:" + INDENTATION
                + "creates a new Event task with the name and datetime given\n");
        stringToPrint += (INDENTATION + "4. deadline task_name /by date_time(yyyy-mm-dd):" + INDENTATION
                + "creates a new Deadline task with the name and datetime given\n");
        stringToPrint += (INDENTATION + "5. find search_term:" + INDENTATION
                + "shows a list of all the tasks with that search term in its name\n");
        stringToPrint += (INDENTATION + "6. done task_index:" + INDENTATION
                + "marks the task at that index as done (hint: use list to see the task index)\n");
        stringToPrint += (INDENTATION + "7. delete task_index:" + INDENTATION
                + "deletes the task at that index permanently (hint: use list to see the task index)\n");
        stringToPrint += (INDENTATION + "8. bye:" + INDENTATION
                + "exits the program\n");
        stringToPrint += LINE;
        return stringToPrint;
    }
}
