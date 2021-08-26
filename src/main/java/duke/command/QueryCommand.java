package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidCommandException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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
    public void execute(TaskList taskList, Storage storage) throws InvalidCommandException {
        switch (this.commandType) {
        case LIST: {
            Ui.printMessage(taskList.printList());
            break;
        }
        case QUERY: {
            try {
                Ui.printMessage(taskList.printList(LocalDate.parse(this.commandBody)));
            } catch (DateTimeParseException e) {
                throw new InvalidCommandException();
            }
            break;
        }
        case FIND: {
            Ui.printMessage(taskList.printList(this.commandBody));
            break;
        }
        }
    }

    public enum CommandType {
        QUERY, LIST, FIND
    }
}
