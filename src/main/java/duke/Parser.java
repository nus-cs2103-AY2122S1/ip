package duke;

import duke.commands.*;

public class Parser {

    public static Command parse(String userCommand) throws DukeException {
        String[] words = userCommand.split(" ");

        switch (words[0].toLowerCase()) {
        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case DoneCommand.COMMAND_WORD:
            return new DoneCommand(userCommand);

        case TodoCommand.COMMAND_WORD:
            return new TodoCommand(userCommand);

        case DeadlineCommand.COMMAND_WORD:
            return new DeadlineCommand(userCommand);

        case EventCommand.COMMAND_WORD:
            return new EventCommand(userCommand);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommand(userCommand);

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        default:
            throw new DukeException("I'm sorry, but I don't know what that means! D:");
        }
    }
}
