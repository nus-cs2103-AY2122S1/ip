package duke.util;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;

/**
 * Represents how Duke processes user input and commands.
 *
 * @author Javier Phon Zhee Kai.
 * @version CS2103T AY21/22 Sem 1.
 */
public class Parser {
    /**
     * Returns the appropriate Command Object after Duke processes the user input.
     *
     * @param command A string representing the command that the user typed to Duke.
     * @return A Command that correctly corresponds to the user's input.
     */
    public static Command parse(String command) {
        String[] inputValues = command.split(" ");
        String firstWord = inputValues[0];
        int commandLength = inputValues.length;
        if (command.equals("bye")) {
            return new ByeCommand();
        } else if (command.equals("list")) {
            return new ListCommand();
        } else if (firstWord.equals("done") && commandLength == 2) {
            //treat as unknown command if there is more than 1 number after "done".
            return new DoneCommand(command);
        } else if (firstWord.equals("delete") && commandLength == 2) {
            //treat as unknown command if there is more than 1 number after "delete".
            return new DeleteCommand(command);
        } else if (firstWord.equals("deadline")) {
            return new DeadlineCommand(command);
        } else if (firstWord.equals("event")) {
            return new EventCommand(command);
        } else if (firstWord.equals("todo")) {
            return new TodoCommand(command);
        } else if (firstWord.equals("find")) {
            return new FindCommand(command);
        } else {
            return new InvalidCommand();
        }
    }
}
