package duke.parser;

import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.NonsenseCommand;
import duke.commands.TaskType;
import duke.commands.UpdateCommand;

public class Parser {

    /**
     * Parses the user's command and returns a command object
     *
     * @param fullCommand Command inputted by user.
     * @return Command type object that will be used to execute the command inputted by the user.
     */
    public static Command parseCommand(String fullCommand) {
        String[] commands = fullCommand.split(" ");
        String first = commands[0];
        String rest = "";
        if (commands.length > 1) {
            rest = fullCommand.substring(first.length() + 1);
        }
        
        switch (first) {
        case "list": {
            return new ListCommand();
        }
        case "done": {
            return new UpdateCommand(rest);
        }
        case "deadline": {
            return new AddCommand(TaskType.DEADLINE, rest);
        }
        case "todo": {
            return new AddCommand(TaskType.TO_DO, rest);
        }
        case "event": {
            return new AddCommand(TaskType.EVENT, rest);
        }
        case "date" : {
            return new FindCommand(TaskType.FIND_BY_DATE, rest);
        }
        case "find": {
            return new FindCommand(TaskType.FIND, rest);
        }
        case "delete": {
            return new DeleteCommand(rest);
        }
        case "bye": {
            return new ExitCommand();
        }
        default: {
            return new NonsenseCommand();
        }
        }
    }

}
