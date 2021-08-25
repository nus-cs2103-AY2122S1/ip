package duke.command;

import duke.Duke;
import duke.DukeException;
import duke.Parser;

/**
 * This class encapsulates a user's command.
 */
public abstract class Command {
    public static final Command[] COMMANDS = new Command[] { new DeadlineCommand(), new DeleteCommand(),
            new DoneCommand(), new EndCommand(), new EventCommand(), new FindCommand(), new ListCommand(),
            new TodoCommand() };

    /**
     * Identifies the command from the user's input.
     *
     * @param input User's input.
     * @return Command associated with the user's input.
     * @throws DukeException If user's input does not match any command.
     */
    public static Command identifyCommand(String input) throws DukeException {
        for (Command c : COMMANDS) {
            if (c.getCommandWord().equals(input)) {
                return c;
            }
        }
        throw new DukeException("Sorry, I don't understand this command");
    }

    /**
     * Gets the command word associated with a command.
     *
     * @return Command word of the command.
     */
    abstract public String getCommandWord();

    /**
     * Runs the action associated with the command.
     *
     * @param duke   Duke instance that the command is called from.
     * @param parser Parser with the user's input
     * @throws DukeException If input is invalid.
     */
    abstract public void run(Duke duke, Parser parser) throws DukeException;
}
