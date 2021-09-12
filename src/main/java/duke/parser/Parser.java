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
        // return command based on user input
        switch (first) {
        case "list": {
            assert rest.length() == 0;
            return new ListCommand();
        }
        case "done": {
            assert rest.length() == 1 : "Specify index to complete";
            return new UpdateCommand(rest);
        }
        case "deadline": {
            assert rest.length() == 1 : "Add a deadline";
            return new AddCommand(TaskType.DEADLINE, rest);
        }
        case "todo": {
            assert rest.length() == 1 : "Add a todo";
            return new AddCommand(TaskType.TO_DO, rest);
        }
        case "event": {
            assert rest.length() == 1 : "Add an event";
            return new AddCommand(TaskType.EVENT, rest);
        }
        case "date" : {
            assert rest.length() == 1 : "Specify date to find";
            return new FindCommand(TaskType.FIND_BY_DATE, rest);
        }
        case "find": {
            assert rest.length() == 1 : "Specify keyword to find";
            return new FindCommand(TaskType.FIND, rest);
        }
        case "delete": {
            assert rest.length() == 1 : "Specify index to delete";
            return new DeleteCommand(rest);
        }
        case "bye": {
            assert rest.length() == 0;
            return new ExitCommand();
        }
        default: {
            return new NonsenseCommand();
        }
        }
    }

}
