package chad.command;

import chad.task.EventTask;
import chad.task.Task;

/**
 * Represents an "Add Event Task" command.
 *
 * @author Jay Aljelo Saez Ting
 */
public class AddEventTaskCommand extends AddTemporalTaskCommand {

    private static final CommandType COMMAND_TYPE = CommandType.ADD_EVENT_TASK;
    private static final String TIME_RELATION = "at";

    /**
     * Creates an AddEventTaskCommand instance.
     *
     * @param command The command represented by the instance.
     */
    public AddEventTaskCommand(String command) throws ChadInvalidCommandException {
        super(command);
    }

    @Override
    String getTimeRelation() {
        return TIME_RELATION;
    }

    @Override
    Task createTask() {
        return new EventTask(getTaskDescription(), getTaskTime());
    }

    @Override
    CommandType getCommandType() {
        return COMMAND_TYPE;
    }
}
