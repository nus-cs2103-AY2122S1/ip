package duke;


import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;



/**
 * Class to handle the input commands of the user.
 */
public class Parser {

    /**
     * Interpret the input from the user and calls the appropriate command.
     *
     * @throws DukeException If input command is not recognized.
     */
    public static Command parseInput(String input) throws DukeException {

        if (input.equalsIgnoreCase("BYE")) {
            return new ExitCommand();
        } else if (input.equalsIgnoreCase("HELP")) {
            return new HelpCommand();
        } else if (input.equalsIgnoreCase("LIST")) {
            return new ListCommand();
        } else if (input.toUpperCase().startsWith("DONE")) {
            return new DoneCommand(input);
        } else if (input.toUpperCase().startsWith("DELETE")) {
            return new DeleteCommand(input);
        } else if (input.toUpperCase().startsWith("FIND")) {
            return new FindCommand(input);
        } else if (input.toUpperCase().startsWith("TODO")
                || input.toUpperCase().startsWith("DEADLINE")
                || input.toUpperCase().startsWith("EVENT")) {
            return new AddCommand(input);

        } else {
            throw new DukeException("Could you say that again?");
        }
    }
}
