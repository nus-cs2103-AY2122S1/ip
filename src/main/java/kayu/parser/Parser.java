package kayu.parser;

import kayu.commands.ByeCommand;
import kayu.commands.Command;
import kayu.commands.DeadlineCommand;
import kayu.commands.DeleteCommand;
import kayu.commands.DoneCommand;
import kayu.commands.EmptyCommand;
import kayu.commands.EventCommand;
import kayu.commands.FindCommand;
import kayu.commands.HelpCommand;
import kayu.commands.InvalidCommand;
import kayu.commands.ListCommand;
import kayu.commands.TodoCommand;

/**
 * Parses user input into {@link kayu.commands.Command} to execute.
 */
public class Parser {

    private final DateTimeFormat dateTimeFormat = DateTimeFormat.generateInstance();

    /**
     * Parses user input into executable {@link kayu.commands.Command}s.
     *
     * @param userInput User input as String.
     * @return Associated {@link kayu.commands.Command}.
     */
    public Command parseToCommand(String userInput) {
        try {
            String[] inputs = userInput.split(" ", 2);
            String command = inputs[0];
            
            switch (command) {
            case ByeCommand.COMMAND_WORD:
                return new ByeCommand();
                
            case ListCommand.COMMAND_WORD:
                return new ListCommand();
            
            case HelpCommand.COMMAND_WORD:
                return new HelpCommand();
                
            case DoneCommand.COMMAND_WORD:
                return new DoneCommand(inputs[1]);
                
            case DeleteCommand.COMMAND_WORD:
                return new DeleteCommand(inputs[1]);
                
            case FindCommand.COMMAND_WORD:
                return new FindCommand(inputs[1]);
                
            case TodoCommand.COMMAND_WORD:
                return new TodoCommand(inputs[1], dateTimeFormat);
                
            case EventCommand.COMMAND_WORD:
                return new EventCommand(inputs[1], dateTimeFormat);
                
            case DeadlineCommand.COMMAND_WORD:
                return new DeadlineCommand(inputs[1], dateTimeFormat);
                                
            default:
                return (userInput.isBlank()) ? new EmptyCommand() : new InvalidCommand();
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            return new InvalidCommand();
        }
    }
}
