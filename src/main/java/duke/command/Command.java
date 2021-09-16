package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.ui.Ui;

/**
 * Command abstract class.
 */
public abstract class Command {
    protected String commandName;
    protected String description;
    protected String[] arguments;

    /**
     * Executes the action that the command represents
     *
     * @return String to display as a response
     */
    public abstract String execute();

    /**
     * Represents Command as a string.
     *
     * @return Command name, description, as well as its arguments on newlines.
     */
    @Override
    public String toString() {
        assert commandName != null: "Command Name cannot be null";
        assert description != null: "Description cannot be null";
        assert arguments != null: "Arguments cannot be null";

        StringBuilder argString = new StringBuilder();
        for (String arg : arguments) {
            argString.append(Ui.OUTPUT_DISPLAY).append(arg).append('\n');
        }
        return commandName + " - " + description + '\n'
                + argString + '\n';
    }

    protected static String getCommand(String str)
            throws IllegalArgumentException{
        int index = getCommandArgumentSplitIndex(str);
        return str.substring(0, index);
    }

    protected static String getArgument(String str)
            throws IllegalArgumentException {

        // Ensure validity; has a command and an argument
        int index = getCommandArgumentSplitIndex(str);
        return str.substring(index).trim();
    }

    private static int getCommandArgumentSplitIndex(String str)
            throws IllegalArgumentException {
        // Ensure validity; has a command and an argument
        int index = str.indexOf(' ');

        if (index == -1) {
            throw new IllegalArgumentException(Ui.MESSAGE_INVALID_ARG);
        }

        return index;
    }
}
