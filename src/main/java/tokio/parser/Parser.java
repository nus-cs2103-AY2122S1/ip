package tokio.parser;

import tokio.commands.AddDeadlineCommand;
import tokio.commands.AddEventCommand;
import tokio.commands.AddTodoCommand;
import tokio.commands.ByeCommand;
import tokio.commands.Command;
import tokio.commands.DeleteCommand;
import tokio.commands.DoneCommand;
import tokio.commands.FindCommand;
import tokio.commands.Instruction;
import tokio.commands.ListCommand;
import tokio.exceptions.DukeException;
import tokio.ui.Ui;

/**
 * Makes sense of the user command.
 */
public class Parser {
    protected static Ui ui = new Ui();

    /**
     * Returns a command based on user input.
     *
     * @param str User input.
     * @return Command class.
     * @throws DukeException If user input is invalid.
     */
    public static Command parse(String str) throws DukeException {
        assert str.length() != 0 : "Command cannot be blank";
        String[] splitStr = str.split(" ", 2);
        String commandType = splitStr[0].trim();
        switch (Instruction.comparesTo(commandType)) {
        case LIST:
            return new ListCommand();
        case DONE:
            return new DoneCommand(Integer.parseInt(splitStr[1].trim()));
        case DELETE:
            return new DeleteCommand(Integer.parseInt(splitStr[1].trim()));
        case TODO:
            return new AddTodoCommand(splitStr[1].trim());
        case DEADLINE:
            return new AddDeadlineCommand(splitStr[1].trim());
        case EVENT:
            return new AddEventCommand(splitStr[1].trim());
        case FIND:
            return new FindCommand(splitStr[1].trim());
        case BYE:
            return new ByeCommand();
        default:
            throw new DukeException("Rio, please enter a valid command so that I can help you!");
        }
    }
}
