package duke;

import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.InvalidCommand;
import duke.commands.ListCommand;
import duke.commands.ReminderCommand;
import duke.commands.ToDoCommand;
import duke.exceptions.DukeException;

/**
 * Deals with making sense of the user command.
 *
 * Organisation adapted from addressbook-level2.
 */
public class Parser {

    /**
     * Constructs a Parser object.
     */
    public Parser() {}

    /**
     * Parses the full command and returns the corresponding Command type.
     *
     * The only instance method in Parser class.
     * @param fullCommand The full command.
     */
    public static Command parse(String fullCommand) {

        //Get first command word
        String[] splitCmd = fullCommand.split(" ", 2);
        String cmd = splitCmd[0];

        try {
            switch (cmd) {
            case ListCommand.COMMAND_WORD:
                return new ListCommand(fullCommand);

            case ToDoCommand.COMMAND_WORD:
                return new ToDoCommand(fullCommand);

            case DeadlineCommand.COMMAND_WORD:
                return new DeadlineCommand(fullCommand);

            case EventCommand.COMMAND_WORD:
                return new EventCommand(fullCommand);

            case DoneCommand.COMMAND_WORD:
                return new DoneCommand(fullCommand);

            case DeleteCommand.COMMAND_WORD:
                return new DeleteCommand(fullCommand);

            case FindCommand.COMMAND_WORD:
                return new FindCommand(fullCommand);

            case ReminderCommand.COMMAND_WORD:
                return new ReminderCommand(fullCommand);

            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();

            default:
                return new InvalidCommand("Sorry! I do not understand you? Try another command!");
            }
        } catch (DukeException e) {
            return new InvalidCommand(e.getMessage());
        }
    }
}
