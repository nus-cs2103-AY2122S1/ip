package blitz;

public class Ui {

    private static String greetingMessage = "Hola! I'm Blitz :)"
            + "\nHere are the tasks in your list:";
    private static String goodbyeMessage = "Adiós. Hope to see you again soon!"
            + "\n\n--Press enter to exit application--";
    private static String loadErrorMessage = "Error loading contents from file!!";
    private static String commandNotFoundMessage = "Sorry, but I don't know what that means :-(";
    private static String listMessage = "Here are the tasks in your list:";
    private static String matchingListMessage = "Here are the matching tasks in your list:";
    private static String incorrectDateTimeFormatMessage = "Incorrect date/time format! Please enter "
            + "deadline date/time in \"d M yyyy HHmm\" format";
    private static String invalidCommandFormatMessage = "Invalid command format!!";
    private static String taskDescriptionCannotBeIncompleteMessage = "Task description cannot be incomplete!!";
    private static String featureList = "What would you like to do today? :\n· Add :\n\t- todo\n\t- event\n\t- deadline"
            + "\n· Delete task (delete) \n· Get task list (list) \n· Mark task as done (done)\n· Find task (find)";
    private static String helpPrompt = "\nUnsure of a command?\nType help <task type/feature name>";
    private static String incompleteCommandMessage = "You've entered an incomplete command!!";
    private static String invalidFeatureMessage = "You've entered an invalid feature!";
    /**
     * Returns greeting message.
     *
     * @return greeting message.
     */
    public static String getGreetingMessage() {
        return greetingMessage;
    }

    /**
     * Returns goodbye message.
     *
     * @return goodbye message.
     */
    public static String getGoodbyeMessage() {
        return goodbyeMessage;
    }

    /**
     * Returns loading error message.
     *
     * @return loading error message
     */
    public static String getLoadErrorMessage() {
        return loadErrorMessage;
    }

    /**
     * Returns command not found message.
     *
     * @return command not found message.
     */
    public static String getCommandNotFoundMessage() {
        return commandNotFoundMessage;
    }

    /**
     * Returns message to be displayed before printing the list of tasks.
     *
     * @return message to be displayed before printing the list of tasks.
     */
    public static String getListMessage() {
        return listMessage;
    }

    /**
     * Returns message to be displayed before printing the list of tasks matching
     * a given keyword.
     *
     * @return message to be displayed before printing the list of tasks matching
     *     a given keyword.
     */
    public static String getMatchingListMessage() {
        return matchingListMessage;
    }

    /**
     * Returns display message when user enters the incorrect format
     * for date and time.
     *
     * @return display message when user enters the incorrect format
     *     for date and time.
     */
    public static String getIncorrectDateTimeFormatMessage() {
        return incorrectDateTimeFormatMessage;
    }

    /**
     * Returns display message when user enters the incorrect format
     * for a command.
     *
     * @return display message when user enters the incorrect format
     *     for the delete/done command.
     */
    public static String getInvalidCommandFormatMessage() {
        return invalidCommandFormatMessage;
    }

    /**
     * Returns display message when a user enters a task without description.
     *
     * @return display message when a user enters a task without description.
     */
    public static String getTaskDescriptionCannotBeIncompleteMessage() {
        return taskDescriptionCannotBeIncompleteMessage;
    }
    public static String getFeatureList() {
        return featureList;
    }

    /**
     * Displays syntax and use of given command
     *
     * @param command command to seek help for
     * @return syntax and use of given command
     */
    public String help(String command) {
        String helpMessage = "Syntax: ";
        switch (command) {
        case "list":
            helpMessage = helpMessage.concat("list\nWhat it does: displays list of existing tasks.");
            break;
        case "done":
            helpMessage = helpMessage.concat("done <#>\nWhat it does: marks task number # as done.");
            break;
        case "delete":
            helpMessage = helpMessage.concat("delete <#>\nWhat it does: deletes task number #.");
            break;
        case "find":
            helpMessage = helpMessage.concat("find <keyword>\nWhat it does: displays list of "
                    + "tasks containing the given keyword.");
            break;
        case "todo" :
            helpMessage = helpMessage.concat("todo <task name>\nWhat it does: adds a todo task "
                    + "with the given name.");
            break;
        case "deadline":
            helpMessage = helpMessage.concat("deadline <task name> /by <date> <time>\nWhat it does: "
                    + "adds a deadline with given name, date(in dd/mm/yyyy format) "
                    + "and time(HHmm in 24 hr format)");
            break;
        case "event":
            helpMessage = helpMessage.concat("event <task name> /on <date> <time>\nWhat it does: "
                    + "adds an event with given name, date(in dd/mm/yyyy format) "
                    + "and time(HHmm in 24 hr format)");
            break;
        default:
            helpMessage = Ui.getInvalidFeatureMessage();
        }
        return helpMessage;
    }

    public static String getHelpPrompt() {
        return helpPrompt;
    }

    public static String getIncompleteCommandMessage() {
        return incompleteCommandMessage;
    }

    public static String getInvalidFeatureMessage() {
        return invalidFeatureMessage;
    }
}
