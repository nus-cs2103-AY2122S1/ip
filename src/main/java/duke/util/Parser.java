package duke.util;

import duke.command.AddCommand;
import duke.command.ClearCommand;
import duke.command.ComingCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.constant.MessageType;
import duke.exception.DukeExtractCommandException;
import duke.exception.DukeUnknownException;
import duke.listener.Message;
import duke.task.Operation;

/**
 * This is the Parser class that extracts operation from command
 * and returns respective Command object.
 */
public class Parser {

    /**
     * Returns respective Command objects according to user command.
     *
     * @param command User's command input.
     * @param message Message interface.
     * @return Respective Command object if exists, else return empty.
     */
    public static Command parse(String command, Message message) {
        try {
            Operation operation = CommandUtils.extractOperation(command);
            if (operation == Operation.BYE) {
                return new ExitCommand(message);
            } else if (operation == Operation.LIST) {
                return new ListCommand(message);
            } else if (operation == Operation.DONE) {
                return new DoneCommand(command, message);
            } else if (operation == Operation.DELETE) {
                return new DeleteCommand(command, message);
            } else if (operation == Operation.CLEAR) {
                return new ClearCommand(message);
            } else if (operation == Operation.FIND) {
                return new FindCommand(command, message);
            } else if (operation == Operation.COMING) {
                return new ComingCommand(message);
            } else if (operation == Operation.TODO
                || operation == Operation.DEADLINE
                || operation == Operation.EVENT) {
                return new AddCommand(command, operation, message);
            }
        } catch (DukeExtractCommandException | DukeUnknownException e) {
            message.show(MessageType.ERROR, e.getMessage());
        }
        return null;
    }
}
