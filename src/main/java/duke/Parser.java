package duke;

import duke.command.*;

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
        if (command.equals("bye")) {
            return new ByeCommand();
        } else if (command.equals("list")) {
            return new ListCommand();
        } else if (inputValues[0].equals("done") && inputValues.length == 2) {
            //treat as unknown command if there is more than 1 number after "done".
            return new DoneCommand();
        } else if (inputValues[0].equals("delete") && inputValues.length == 2) {
            //treat as unknown command if there is more than 1 number after "delete".
            return new DeleteCommand();
        } else if (inputValues[0].equals("deadline")) {
            return new DeadlineCommand();
        } else if (inputValues[0].equals("event")) {
            return new EventCommand();
        } else if (inputValues[0].equals("todo")) {
            return new TodoCommand();
        } else if (inputValues[0].equals("find")) {
            return new FindCommand();
        } else {
            return new InvalidCommand();
        }
    }
}
