package duke.utils;
import duke.commands.*;

public class Parser {

    public Command parseInput(String fullCommand){

        String[] commandSplit = fullCommand.split(" ");
        String actionWord = commandSplit[0];

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

        }

        return new ErrorCommand();

    }


}
