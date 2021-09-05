package tokio.parser;

import tokio.commands.AddCommand;
import tokio.commands.ByeCommand;
import tokio.commands.Command;
import tokio.commands.DeleteCommand;
import tokio.commands.DoneCommand;
import tokio.commands.FindCommand;
import tokio.commands.ListCommand;
import tokio.exceptions.DukeException;
import tokio.ui.Ui;

/**
 * deals with making sense of the user command.
 */
public class Parser {
    protected static Ui ui = new Ui();

    /**
     * Returns a command based on user input.
     *
     * @param str User input.
     * @return Command class.
     * @throws DukeException If user input is invalid.
     */
    public static Command parse(String str) throws DukeException {
        try {
            String[] splitStr = str.split(" ", 2);
            if (splitStr[0].equalsIgnoreCase("list")) {
                return new ListCommand();
            } else if (splitStr[0].equalsIgnoreCase("done")) {
                return new DoneCommand(str);
            } else if (splitStr[0].equalsIgnoreCase("delete")) {
                return new DeleteCommand(str);
            } else if (splitStr[0].equalsIgnoreCase("todo")
                    || splitStr[0].equalsIgnoreCase("deadline")
                    || splitStr[0].equalsIgnoreCase("event")) {
                return new AddCommand(str);
            } else if (splitStr[0].equalsIgnoreCase("find")) {
                return new FindCommand(splitStr[1]);
            } else if (splitStr[0].equalsIgnoreCase("bye")) {
                return new ByeCommand();
            } else {
                throw new DukeException("Please enter a valid command so that I will be able to help you...");
            }
        } catch (Exception e) {
            throw new DukeException("Río! Please enter a command to proceed...");
        }
    }
}
