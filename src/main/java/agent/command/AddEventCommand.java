package agent.command;

import agent.exceptions.EmptyEventBodyException;
import agent.exceptions.InvalidEventBodyException;
import agent.exceptions.TaskFileIoException;
import agent.messages.TaskAddMessage;
import agent.tasks.Event;
import agent.tasks.Task;
import agent.tasks.TaskList;

/**
 * Represents user command to add an <code>Event</code> to persisted Tasks.
 *
 * @author kevin9foong
 */
public class AddEventCommand extends Command {

    /**
     * Constructs instance of <code>AddEventCommand</code> which adds a <code>Event</code> based
     * on data provided within the given <code>userInputBody</code> <code>String</code>.
     *
     * @param userInputBody <code>String</code> containing description of event and venue.
     */
    public AddEventCommand(String userInputBody) {
        super(userInputBody);
    }

    /**
     * Adds an <code>Event</code> to the <code>TaskList</code> and writes to user <code>EventAddMessage</code>.
     *
     * @param taskList handles task operations including adding, deleting, marking as done and retrieval.
     * @return response message by chat bot for adding event.
     * @throws TaskFileIoException       thrown when failure due to reading or writing to Task save file occurs.
     * @throws InvalidEventBodyException thrown when the data String representing the
     *                                   <code>Event</code> is invalid.
     * @throws EmptyEventBodyException   thrown when the data String representing the
     *                                   <code>Event</code> is missing.
     */
    @Override
    public String execute(TaskList taskList)
            throws TaskFileIoException, EmptyEventBodyException, InvalidEventBodyException {
        Task addedTask = taskList.addTask(new Event(super.getUserInputBody()));
        return new TaskAddMessage(addedTask.toString(),
                taskList.getNumOfTasks()).toString();
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
