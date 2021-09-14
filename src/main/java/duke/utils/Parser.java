package duke.utils;
import duke.commands.*;

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


        }

        return new ErrorCommand();

    }


}
