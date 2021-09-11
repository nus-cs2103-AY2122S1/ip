package chad.command;

import chad.task.DeadlineTask;
import chad.task.Task;

/**
 * Represents an "Add Deadline Task" command.
 *
 * @author Jay Aljelo Saez Ting
 */
public class AddDeadlineTaskCommand extends AddTemporalTaskCommand {

    private static final CommandType COMMAND_TYPE = CommandType.ADD_DEADLINE_TASK;
    private static final String TIME_RELATION = "by";

    /**
     * Creates an AddDeadlineTaskCommand instance.
     *
     * @param command The command represented by the instance.
     */
    public AddDeadlineTaskCommand(String command) throws ChadInvalidCommandException {
        super(command);
    }

    @Override
    String getTimeRelation() {
        return TIME_RELATION;
    }

    @Override
    Task createTask() {
        return new DeadlineTask(getTaskDescription(), getTaskTime());
    }

    @Override
    CommandType getCommandType() {
        return COMMAND_TYPE;
    }
}
