package duke.parser;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddToDoCommand;
import duke.command.Command;
import duke.command.CompleteTaskCommand;
import duke.command.DeleteTaskCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.LoadCommand;

/**
 * Parses user input.
 */
public class CommandParser {
    /**
     * Parses the command as well as any arguments entered by the user.
     *
     * @param fullCommand Full command entered by the user.
     * @return Corresponding <code>Command</code>, or the <code>HelpCommand</code> if the command is not recognised.
     */
    public static Command parse(String fullCommand) {
        final String trimmedCommand = fullCommand.trim();
        final String commandWord = trimmedCommand.split("\\s+")[0];
        final String commandArguments = trimmedCommand.replace(commandWord, "").trim();

        switch (commandWord) {
        case ListCommand.COMMAND_WORD:
            return new ListCommand(commandArguments);
        case FindCommand.COMMAND_WORD:
            return new FindCommand(commandArguments);
        case AddToDoCommand.COMMAND_WORD:
            return new AddToDoCommand(commandArguments);
        case AddDeadlineCommand.COMMAND_WORD:
            return new AddDeadlineCommand(commandArguments);
        case AddEventCommand.COMMAND_WORD:
            return new AddEventCommand(commandArguments);
        case CompleteTaskCommand.COMMAND_WORD:
            return new CompleteTaskCommand(commandArguments);
        case DeleteTaskCommand.COMMAND_WORD:
            return new DeleteTaskCommand(commandArguments);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case LoadCommand.COMMAND_WORD:
            return new LoadCommand(commandArguments);
        default:
            return new HelpCommand();
        }
    }
}
