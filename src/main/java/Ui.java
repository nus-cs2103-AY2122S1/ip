public class Ui {

    private String greetingMessage = "Hola! I'm Blitz :)\nHere are the tasks in your list:";
    private String goodbyeMessage = "Adiós. Hope to see you again soon!\n\n--Press enter to exit application--";
    private String loadErrorMessage = "Error loading contents from file!!";
    private String commandNotFoundMessage = "Sorry, but I don't know what that means :-(";
    private String listMessage = "Here are the tasks in your list:";
    private String matchingListMessage = "Here are the matching tasks in your list:";
    private String incorrectDateTimeFormatMessage = "Incorrect date/time format! Please enter "
            + "deadline date/time in \"d M yyyy HHmm\" format";
    private String invalidCommandFormatMessage = "Invalid command format!!";
    private String taskDescriptionCannotBeEmptyMessage = "Task description cannot be empty!!";

    /**
     * Returns greeting message.
     *
     * @return greeting message.
     */
    public String getGreetingMessage() {
        return greetingMessage;
    }

    /**
     * Returns goodbye message.
     *
     * @return goodbye message.
     */
    public String getGoodbyeMessage() {
        return goodbyeMessage;
    }

    /**
     * Returns loading error message.
     *
     * @return loading error message
     */
    public String getLoadErrorMessage() {
        return loadErrorMessage;
    }

    /**
     * Returns command not found message.
     *
     * @return command not found message.
     */
    public String getCommandNotFoundMessage() {
        return commandNotFoundMessage;
    }

    /**
     * Returns message to be displayed before printing the list of tasks.
     *
     * @return message to be displayed before printing the list of tasks.
     */
    public String getListMessage() {
        return listMessage;
    }

    /**
     * Returns message to be displayed before printing the list of tasks matching
     * a given keyword.
     *
     * @return message to be displayed before printing the list of tasks matching
     *     a given keyword.
     */
    public String getMatchingListMessage() {
        return matchingListMessage;
    }

    /**
     * Returns display message when user enters the incorrect format
     * for date and time.
     *
     * @return display message when user enters the incorrect format
     *     for date and time.
     */
    public String getIncorrectDateTimeFormatMessage() {
        return incorrectDateTimeFormatMessage;
    }

    /**
     * Returns display message when user enters the incorrect format
     * for a command.
     *
     * @return display message when user enters the incorrect format
     *     for the delete/done command.
     */
    public String getInvalidCommandFormatMessage() {
        return invalidCommandFormatMessage;
    }

    /**
     * Returns display message when a user enters a task without description.
     *
     * @return display message when a user enters a task without description.
     */
    public String getTaskDescriptionCannotBeEmptyMessage() {
        return taskDescriptionCannotBeEmptyMessage;
    }
}
