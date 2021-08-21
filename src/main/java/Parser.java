/**
 * Deals with making sense of the user command.
 *
 * @author marcuspeh
 * @version A-MoreOOP
 * @since 21 Aug 2021
 */
public class Parser {
    /**
     * Static method to parses the user command and make sense of the command by the user.
     *
     * @param message command input by the user.
     * @return the type of command as a enum Keyword.
     * @throws DukeException if the command is not recognised.
     */
    public static Keyword parseChat(String message) throws DukeException {
        String command = message.split(" ")[0].toLowerCase();

        if (command.equals(Keyword.EXIT.getKeyword()))
            return Keyword.EXIT;
        else if (command.equals(Keyword.LIST.getKeyword()))
            return Keyword.LIST;
        else if (command.equals(Keyword.DONE.getKeyword()))
            return Keyword.DONE;
        else if (command.equals(Keyword.DEADLINE.getKeyword()))
            return Keyword.DEADLINE;
        else if (command.equals(Keyword.EVENTS.getKeyword()))
            return Keyword.EVENTS;
        else if (command.equals(Keyword.TODOS.getKeyword()))
            return Keyword.TODOS;
        else if (command.equals(Keyword.DELETE.getKeyword()))
            return Keyword.DELETE;

        throw new DukeException("Command not parsable");
    }
}
