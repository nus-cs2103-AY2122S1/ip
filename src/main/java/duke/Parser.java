package duke;

/**
 * Parser processes user input and commands.
 *
 * @author Jovyn Tan
 * @version CS2103 AY21/22 Sem 1
 */
public interface Parser {
    /**
     * Gets the description supplied by the user after a command.
     * @param command a String that describes the command (e.g. "event")
     * @param message the String that is input by the user (e.g. "event Meeting /at tomorrow")
     * @return a String representing the description after the command (e.g. "Meeting /at tomorrow")
     * @throws DukeException if that the description is invalid
     */
    static String getStringFrom(String command, String message) throws DukeException {
        assert command.length() > 0;
        assert message.length() > 1;
        if (message.length() <= command.length()) {
            throw new DukeException(
                    String.format("The description of a %s cannot be empty.", command)
            );
        }
        String description = message.substring(command.length()).trim();
        if (description.length() == 0) {
            throw new DukeException(
                    String.format("The description of a %s cannot be empty.", command)
            );
        }
        return description;
    }

    /**
     * Parses a user-input for a number supplied after a given command.
     * @param command A String command that comes before the desired number. (e.g. "delete")
     * @param message The String that is the full input by the user. (e.g. "delete 2")
     * @return an int representing the description of the command. (e.g. "2")
     * @throws DukeException if the description is invalid
     */
    static int getIntFrom(String command, String message) throws DukeException {
        return Integer.parseInt(getStringFrom(command, message));
    }
}