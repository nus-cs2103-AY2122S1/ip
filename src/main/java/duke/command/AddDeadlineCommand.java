package duke.command;

import duke.exceptions.EmptyDeadlineBodyException;
import duke.exceptions.InvalidDateTimeFormatException;
import duke.exceptions.InvalidDeadlineBodyException;
import duke.io.UserOutputHandler;
import duke.messages.TaskAddMessage;
import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.io.IOException;

/**
 * Represents user command to add a <code>Deadline</code> to persisted tasks.
 *
 * @author kevin9foong
 */
public class AddDeadlineCommand extends Command {

    public AddDeadlineCommand(String getUserInputBody) {
        super(getUserInputBody);
    }

    /**
     * Adds a <code>Deadline</code> to the <code>TaskList</code> and writes to user <code>TaskAddMessage</code>.
     *
     * @param userOutputHandler handles outputting messages to the output destination.
     * @param taskList          handles task operations including adding, deleting, marking as done and retrieval.
     * @throws IOException                    thrown when failure due to reading or writing occurs.
     * @throws InvalidDateTimeFormatException thrown when String representing <code>DateTime</code> is invalid.
     * @throws InvalidDeadlineBodyException   thrown when the data String representing the
     *                                        <code>Deadline</code> is invalid.
     * @throws EmptyDeadlineBodyException     thrown when the data String representing the
     *                                        <code>Deadline</code> is missing.
     */
    @Override
    public void execute(UserOutputHandler userOutputHandler, TaskList taskList)
            throws IOException, InvalidDateTimeFormatException,
            InvalidDeadlineBodyException, EmptyDeadlineBodyException {
        Task addedDeadline = taskList.addTask(new Deadline(super.getUserInputBody()));
        userOutputHandler.writeMessage(new TaskAddMessage(addedDeadline.toString(),
                taskList.getNumOfTasks()));
    }

    /**
     * Returns false to indicate program should not terminate after command is executed.
     *
     * @return false to indicate program should not terminate after command is executed.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
