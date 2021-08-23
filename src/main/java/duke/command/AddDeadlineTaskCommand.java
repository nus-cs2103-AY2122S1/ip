package duke.command;

import duke.task.DeadlineTask;
import duke.task.Task;

public class AddDeadlineTaskCommand extends AddTemporalTaskCommand {

    private static final CommandType COMMAND_TYPE = CommandType.ADD_DEADLINE_TASK;
    private static final String TIME_RELATION = "by";

    public AddDeadlineTaskCommand(String command) {
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
