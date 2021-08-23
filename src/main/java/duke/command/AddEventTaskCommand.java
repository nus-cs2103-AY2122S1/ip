package duke.command;

import duke.task.EventTask;
import duke.task.Task;

public class AddEventTaskCommand extends AddTemporalTaskCommand {

    private static final CommandType COMMAND_TYPE = CommandType.ADD_EVENT_TASK;
    private static final String TIME_RELATION = "at";

    public AddEventTaskCommand(String command) {
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
