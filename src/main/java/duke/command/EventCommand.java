package duke.command;

import java.time.LocalDateTime;

import duke.exception.InvalidInputException;
import duke.exception.NoActionException;
import duke.exception.NoTimeException;
import duke.exception.SaveFileException;
import duke.task.Event;
import duke.task.Task;
import duke.util.Parser;
import duke.util.Reply;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * A command class encapsulating the logic that occurs when the user issues a 'event' command.
 */
public class EventCommand extends Command {
    private final String action;

    /**
     * Constructor of the EventCommand
     *
     * @param action Text input of the EventCommand
     */
    public EventCommand(String action) {
        super(false);
        this.action = action;
    }

    /**
     * Instantiates an Event task and adds it to the tasklist
     *
     * @param tasks List of existing tasks
     * @param storage Storage class handling the persistence of the tasks
     * @return CommandResult of the encapsulating the effects of the command after it completes
     * @throws InvalidInputException if invalid datetime is provided
     * @throws NoTimeException if no event time is provided
     * @throws NoActionException if no event name is provided
     * @throws SaveFileException if save file errors occur
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage) throws InvalidInputException,
        NoTimeException, NoActionException, SaveFileException {
        assert tasks != null;
        assert storage != null;
        if (action.trim().length() == 0) {
            throw new NoActionException("Command 'event' requires a task action");
        }
        String[] eventInputs = action.split("/at", 2);
        if (eventInputs.length <= 1) {
            throw new NoTimeException(
                    "Command 'event' requires a time to be specified. Use /at to specify a time.");
        }
        LocalDateTime date = Parser.parseDate(eventInputs[1].trim());
        Task newTask = new Event(eventInputs[0].trim(), date);
        tasks.add(newTask);
        storage.save(tasks);
        return new CommandResult(Reply.showTaskAdded(newTask, tasks), true, super.isExit());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof EventCommand) {
            EventCommand otherEventCommand = (EventCommand) obj;
            return otherEventCommand.action.equals(this.action);
        }
        return false;
    }
}
