package duke;

import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.HelpCommand;
import duke.commands.ListCommand;
import duke.commands.TodoCommand;
import duke.commands.WelcomeCommand;

/**
 * Parses user commands into the program.
 */
public class Parser {

    /**
     * Parses user commands into the program to determine which command they are categorised as.
     *
     * @param userCommand Full user input string.
     * @return Command object depending on the command keyword.
     * @throws DukeException If command keyword is invalid.
     */
    public static Command parse(String userCommand) throws DukeException {
        String[] words = userCommand.strip().split(" ");

        switch (words[0].toLowerCase()) {
        case WelcomeCommand.COMMAND_WORD:
            return new WelcomeCommand();

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case DoneCommand.COMMAND_WORD:
            return new DoneCommand(userCommand.strip());

        case TodoCommand.COMMAND_WORD:
            return new TodoCommand(userCommand.strip());

        case DeadlineCommand.COMMAND_WORD:
            return new DeadlineCommand(userCommand.strip());

        case EventCommand.COMMAND_WORD:
            return new EventCommand(userCommand.strip());

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommand(userCommand.strip());

        case FindCommand.COMMAND_WORD:
            return new FindCommand(userCommand.strip());

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        default:
            throw new DukeException("I'm sorry, but I don't know what that means! D:");
        }
    }
}
