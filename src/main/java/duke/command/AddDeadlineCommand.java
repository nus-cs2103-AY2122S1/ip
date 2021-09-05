package duke.command;

import duke.exceptions.EmptyDeadlineBodyException;
import duke.exceptions.InvalidDateTimeFormatException;
import duke.exceptions.InvalidDeadlineBodyException;
import duke.exceptions.TaskFileIoException;
import duke.io.UserOutputHandler;
import duke.messages.TaskAddMessage;
import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Represents user command to add a <code>Deadline</code> to persisted tasks.
 *
 * @author kevin9foong
 */
public class AddDeadlineCommand extends Command {

    /**
     * Constructs instance of <code>AddDeadlineCommand</code> which adds a <code>Deadline</code> based
     * on data provided within the given <code>userInputBody</code> <code>String</code>.
     *
     * @param userInputBody <code>String</code> containing description of deadline and deadline <code>DateTime</code>.
     */
    public AddDeadlineCommand(String userInputBody) {
        super(userInputBody);
    }

    /**
     * Adds a <code>Deadline</code> to the <code>TaskList</code> and writes to user <code>TaskAddMessage</code>.
     *
     * @param userOutputHandler handles outputting messages to the output destination.
     * @param taskList          handles task operations including adding, deleting, marking as done and retrieval.
     * @throws TaskFileIoException            thrown when failure due to reading or writing to Task save file occurs.
     * @throws InvalidDateTimeFormatException thrown when String representing <code>DateTime</code> is invalid.
     * @throws InvalidDeadlineBodyException   thrown when the data String representing the
     *                                        <code>Deadline</code> is invalid.
     * @throws EmptyDeadlineBodyException     thrown when the data String representing the
     *                                        <code>Deadline</code> is missing.
     */
    @Override
    public void execute(UserOutputHandler userOutputHandler, TaskList taskList)
            throws InvalidDateTimeFormatException,
            InvalidDeadlineBodyException, EmptyDeadlineBodyException, TaskFileIoException {
        Task addedDeadline = taskList.addTask(new Deadline(super.getUserInputBody()));
        userOutputHandler.handleOutput(new TaskAddMessage(addedDeadline.toString(),
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
