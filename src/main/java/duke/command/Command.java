package duke.command;

import duke.constant.MessageType;
import duke.exception.DukeExtractCommandException;
import duke.exception.DukeTaskNumberOutOfBoundsException;
import duke.listener.Message;
import duke.task.TaskList;
import duke.util.CommandUtils;

/**
 * This is the Command class that contains two execution methods.
 */
public abstract class Command {
    private final Message message;
    private final boolean isExit;

    /**
     * Constructs a Command object.
     *
     * @param message Message interface.
     */
    public Command(Message message) {
        this.message = message;
        this.isExit = false;
    }

    /**
     * Constructs a Command object.
     *
     * @param message Message interface.
     * @param isExit System status: running or exited.
     */
    Command(Message message, boolean isExit) {
        this.message = message;
        this.isExit = isExit;
    }

    /**
     * Returns a Message interface.
     *
     */
    Message getMessage() {
        return message;
    }

    /**
     * Executes operation.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Executes operation.
     */
    public void execute() {
    }

    /**
     * Executes operation on TaskList.
     *
     * @param taskList TaskList object.
     */
    public void execute(TaskList taskList) {
    }

    /**
     * Returns task number.
     *
     * @param command Command from user input.
     */
    int getTaskNumber(String command) {
        int number = 0;
        try {
            number = CommandUtils.extractTaskNumber(command);
        } catch (DukeExtractCommandException | NumberFormatException
                | DukeTaskNumberOutOfBoundsException e) {
            getMessage().show(MessageType.ERROR, e.getMessage());
        }
        return number;
    }
}
