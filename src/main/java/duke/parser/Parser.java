package duke.parser;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddToDoCommand;
import duke.command.Command;
import duke.command.CompleteTaskCommand;
import duke.command.DeleteTaskCommand;
import duke.command.ExitCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;

public class Parser {
    public static Command parse(String fullCommand) {
        final String trimmedCommand = fullCommand.trim();
        final String commandWord = trimmedCommand.split("\\s+")[0];
        final String commandArguments = trimmedCommand.replace(commandWord, "").trim();

        switch (commandWord) {
        case ListCommand.COMMAND_WORD:
            return new ListCommand(commandArguments);
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
        default:
            return new HelpCommand();
        }
    }
}
