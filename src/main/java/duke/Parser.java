package duke;

import duke.command.*;

public class Parser {

    public Parser() { }

    /**
     * Gets the command entered by the user.
     *
     * @param userInput The string keyed in by the user
     * @return The first word keyed in by the user
     */
    public String getUserCommand(String userInput) {
        if (userInput.indexOf(' ') <= 0) {
            return userInput;
        }
        return userInput.substring(0, userInput.indexOf(' '));
    }

    /**
     * Gets the argument entered by the user.
     *
     * @param userInput The string keyed in by the user
     * @return The arguments entered by the user (all words after the first word)
     */
    public String getUserArgument(String userInput) {
        if (userInput.indexOf(' ') <= 0) {
            return "";
        }
        try {
            return userInput.substring(userInput.indexOf(' ') + 1).trim();
        } catch (StringIndexOutOfBoundsException e) {
            return "";
        }
    }

    public Command parse(String userInput) {
        String userCommand = getUserCommand(userInput);
        String userArgument = getUserArgument(userInput);

        switch (userCommand) {
        case "list":
            return new ListCommand(userCommand, userArgument);
        case "bye":
            return new ByeCommand(userCommand, userArgument);
        case "done":
            return new DoneCommand(userCommand, userArgument);
        case "todo":
        case "deadline":
        case "event":
            return new TaskCommand(userCommand, userArgument);
        case "delete":
            return new DeleteCommand(userCommand, userArgument);
        case "find":
        default:
            return new InvalidCommand(userCommand, userArgument);
        }
    }
}
