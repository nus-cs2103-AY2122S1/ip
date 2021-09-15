package duke.utils;

import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.ErrorCommand;
import duke.commands.EventCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.HelpCommand;
import duke.commands.ListCommand;
import duke.commands.TodoCommand;

/**
 * Class that parses user input and perfoms the corresponding
 * function dictated by user input
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding Command
     *
     * @param fullCommand the user's input into the CLI
     * @return Command command to be executed
     */
    public Command parseInput(String fullCommand) {

        String[] commandSplit = fullCommand.split(" ");
        String actionWord = commandSplit[0];
        assert (actionWord != null);

        switch (actionWord) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();

            case "todo":
                return new TodoCommand(fullCommand);

            case "deadline":
                return new DeadlineCommand(fullCommand);

            case "event":
                return new EventCommand(fullCommand);

            case "done":
                return new DoneCommand(fullCommand);

            case "delete":
                return new DeleteCommand(fullCommand);

            case "find":
                return new FindCommand(fullCommand);

            case "help":
                return new HelpCommand();

            default:
                return new ErrorCommand();
        }
    }
}
