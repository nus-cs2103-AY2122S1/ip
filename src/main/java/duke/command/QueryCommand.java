package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.exception.InvalidCommandException;

/**
 * A class that represents a command that will query tasks.
 */
public class QueryCommand extends Command {
    /**
     * The type of the command.
     */
    private final CommandType commandType;

    /**
     * Constructs a {@code QueryCommand} object.
     *
     * @param commandType The command type.
     * @param commandBody The command body.
     */
    public QueryCommand(CommandType commandType, String commandBody) {
        super(commandBody);
        this.commandType = commandType;
    }

    /**
     * Executes the command.
     *
     * @param taskList The task list that may be modified or referenced by the command.
     * @param storage  The storage that may be modified of referenced by the command.
     * @throws InvalidCommandException If the command body is invalid.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        switch (this.commandType) {
        case LIST: {
            return taskList.printList();
        }
        case QUERY: {
            try {
                return taskList.printList(LocalDate.parse(this.commandBody));
            } catch (DateTimeParseException e) {
                throw new InvalidCommandException();
            }
        }
        case FIND: {
            return taskList.printList(this.commandBody);
        }
        default:
            assert false;
            throw new DukeException("Something wrong happened when executing Duke!");
        }
    }

    public enum CommandType {
        QUERY, LIST, FIND
    }
}
