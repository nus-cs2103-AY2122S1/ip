package duke.util;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EditCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.OccurringOnCommand;
import duke.command.UnknownCommand;

/**
 * Class that parses the user's input.
 */
public class Parser {

    /**
     * Parses the user's input.
     * @param command The user's input.
     * @return The Command object to be executed.
     * @throws DukeException If the command is invalid.
     */
    public static Command parse(String command) throws DukeException {
        if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.startsWith("delete ")) {
            String taskNo = command.substring(7);
            return new DeleteCommand(taskNo);
        } else if (command.equals("list")) {
            return new ListCommand();
        } else if (command.startsWith("done ")) {
            String taskNo = command.substring(5);
            return new DoneCommand(taskNo);
        } else if (command.startsWith("occurring on ")) {
            String date = command.substring(13);
            return new OccurringOnCommand(date);
        } else if (command.startsWith("todo ") || command.startsWith("deadline ") || command.startsWith("event ")) {
            return new AddCommand(command);
        } else if (command.startsWith("find ")) {
            return new FindCommand(command.substring(5));
        } else if (command.equals("help")) {
            return new HelpCommand();
        } else if (command.startsWith("edit ")) {
            char fieldToEdit = command.charAt(5);
            int pos = command.indexOf("/to");
            String taskNo = command.substring(7, pos - 1);
            String thingToChangeTo = command.substring(pos + 4);
            return new EditCommand(taskNo, fieldToEdit, thingToChangeTo);
        } else {
            return new UnknownCommand();
        }
    }

}
