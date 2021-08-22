package duke.command;

import duke.exceptions.EmptyDeadlineBodyException;
import duke.exceptions.EmptyEventBodyException;
import duke.exceptions.InvalidDateTimeFormatException;
import duke.exceptions.InvalidDeadlineBodyException;
import duke.exceptions.InvalidEventBodyException;
import duke.io.UserOutputHandler;
import duke.messages.TaskAddMessage;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.io.IOException;

/**
 * Represents user command to add an <code>Event</code> to persisted Tasks.
 *
 * @author kevin9foong
 */
public class AddEventCommand extends Command {

    public AddEventCommand(String getUserInputBody) {
        super(getUserInputBody);
    }

    /**
     * Adds an <code>Event</code> to the <code>TaskList</code> and writes to user <code>EventAddMessage</code>.
     *
     * @param userOutputHandler handles outputting messages to the output destination.
     * @param taskList          handles task operations including adding, deleting, marking as done and retrieval.
     * @throws IOException                    thrown when failure due to reading or writing occurs.
     * @throws InvalidEventBodyException   thrown when the data String representing the
     *                                        <code>Event</code> is invalid.
     * @throws EmptyEventBodyException     thrown when the data String representing the
     *                                        <code>Event</code> is missing.
     */
    @Override
    public void execute(UserOutputHandler userOutputHandler, TaskList taskList)
            throws IOException, EmptyEventBodyException, InvalidEventBodyException {
        Task addedTask = taskList.addTask(new Event(super.getUserInputBody()));
        userOutputHandler.writeMessage(new TaskAddMessage(addedTask.toString(),
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
