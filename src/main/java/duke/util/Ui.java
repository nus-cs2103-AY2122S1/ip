package duke.util;

/**
 * Ui class deals with interactions with the user.
 *
 * @author Chng Zi Hao
 */
public class Ui {
    private static final String DIVIDER = "----------------------------------"
            + "----------------------------------------------";
    private static final String PROMPT = "Enter Command: ";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String WELCOME_MESSAGE = String.format("%s\n%s\n%s\n%s", DIVIDER, LOGO,
            "Hello! I'm Duke :)\nWhat can I do for you? (Type 'help' to see what I can do!)", DIVIDER);
    private static final String GOODBYE_MESSAGE = "Bye :< Hope to see you again soon!";

    public void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    public void printGoodbyeMessage() {
        System.out.println(GOODBYE_MESSAGE);
    }

    public void printPrompt() {
        System.out.println(PROMPT);
    }

    public void printDivider() {
        System.out.println(DIVIDER);
    }

    public String getWelcomeMessage() {
        return WELCOME_MESSAGE;
    }

    /**
     * Formats the input and prints it in a formatted version.
     *
     * @param input Input to be printed.
     */
    public void formatPrint(String input) {
        System.out.println(DIVIDER);
        System.out.println(input);
        System.out.println(DIVIDER);
    }

    /**
     * Prints the help page for users.
     */
    public String getHelp() {
        return "Here is the list of my available commands!\n"
                + "1. todo [description] - Adds a ToDo task to task list\n"
                + "2. deadline [description] /by [date] [time] - Adds a Deadline to task list.\n"
                + "3. event [description] /at [date] [time]-[time] - Adds a Event to task list\n"
                + "4. filter [date] - Filters out list of task on this date\n"
                + "Date formats: dd/mm/yyyy, dd-mm-yyyy, yyyy-mm-dd\n"
                + "Time format: HHmm, HH:mm\n"
                + "5. list - Display list of items you have added\n"
                + "6. done [index of completed task] - Marks specified tasks as completed\n"
                + "7. delete [index of task to be deleted] - Deletes specified task\n"
                + "8. find [keyword to search for] - Finds tasks by specific keyword\n"
                + "9. bye - End the program";
    }
}
