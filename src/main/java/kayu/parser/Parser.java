package kayu.parser;

import kayu.commands.ByeCommand;
import kayu.commands.Command;
import kayu.commands.DeadlineCommand;
import kayu.commands.DeleteCommand;
import kayu.commands.DeleteNoteCommand;
import kayu.commands.DoneCommand;
import kayu.commands.EmptyCommand;
import kayu.commands.EventCommand;
import kayu.commands.FindCommand;
import kayu.commands.HelpCommand;
import kayu.commands.InvalidCommand;
import kayu.commands.ListCommand;
import kayu.commands.ListNotesCommand;
import kayu.commands.NoteCommand;
import kayu.commands.TodoCommand;

/**
 * Parses user input into {@link kayu.commands.Command} to execute.
 */
public class Parser {

    /**
     * Parses user input into executable {@link kayu.commands.Command}s.
     *
     * @param userInput User input as String.
     * @return Associated {@link kayu.commands.Command}.
     */
    public Command parseToCommand(String userInput) {
        try {
            String[] inputs = userInput.trim().split(" ", 2);
            String command = inputs[0];
            String params;

            switch (command) {
            case ByeCommand.COMMAND_WORD:
                return new ByeCommand();

            case ListCommand.COMMAND_WORD:
                return new ListCommand();

            case ListNotesCommand.COMMAND_WORD:
                return new ListNotesCommand();

            case HelpCommand.COMMAND_WORD:
                return new HelpCommand();

            case DoneCommand.COMMAND_WORD:
                params = inputs[1];
                return new DoneCommand(params);

            case DeleteCommand.COMMAND_WORD:
                params = inputs[1];
                return new DeleteCommand(params);

            case DeleteNoteCommand.COMMAND_WORD:
                params = inputs[1];
                return new DeleteNoteCommand(params);

            case FindCommand.COMMAND_WORD:
                params = inputs[1];
                return new FindCommand(params);

            case NoteCommand.COMMAND_WORD:
                params = inputs[1];
                return new NoteCommand(params);

            case TodoCommand.COMMAND_WORD:
                params = inputs[1];
                return new TodoCommand(params);

            case EventCommand.COMMAND_WORD:
                params = inputs[1];
                return new EventCommand(params);

            case DeadlineCommand.COMMAND_WORD:
                params = inputs[1];
                return new DeadlineCommand(params);

            default:
                return (userInput.isBlank()) ? new EmptyCommand() : new InvalidCommand();
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            return new InvalidCommand();
        }
    }
}
