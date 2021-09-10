package duke;

public class Ui {
    private static final String LINE = "    --------------------------------------------------\n";
    private static final String INDENTATION = "      ";
    /**
     * Prints a response to the user.
     *
     * @param lines A variable number of lines to be printed as a repsonse to the user.
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
        stringToPrint += (INDENTATION + "4. deadline task_name /by date_time:" + INDENTATION
                + "creates a new Deadline task with the name and datetime given\n");
        stringToPrint += (INDENTATION + "5. bye:" + INDENTATION
                + "exits the program\n");
        stringToPrint += LINE;
        return stringToPrint;
    }
}
