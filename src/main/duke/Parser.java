package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;


public class Parser {
    
    public static Command parse(String fullCommand) throws DukeException{
        String type = fullCommand.split(" ")[0];
        switch (type) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand(fullCommand);
        case "done":
            return new DoneCommand(fullCommand);
        case "delete":
            return new DeleteCommand(fullCommand);
        default:
            return new AddCommand(fullCommand);
        }
    }
}
