package chad.command;

import chad.task.TaskHandler;
import chad.ui.Ui;

/**
 * Represents a "List Tasks" command.
 *
 * @author Jay Aljelo Saez Ting
 */
public class ListTasksCommand extends Command {

    private static final CommandType COMMAND_TYPE = CommandType.LIST_TASKS;
    private static final String NO_TASKS_MESSAGE = "There are no tasks in your list.";
    private static final String SOME_TASKS_MESSAGE = "Here are the tasks in your list:";
    private static final String UNNECESSARY_ARGUMENTS_ERROR_MESSAGE = "There were unnecessary arguments.";

    /**
     * Creates an ListTasksCommand instance.
     *
     * @param command The command represented by the instance.
     */
    public ListTasksCommand(String command) throws ChadInvalidCommandException {
        super(command);
    }

    @Override
    public void execute(TaskHandler taskHandler, Ui ui) throws ChadInvalidCommandException {
        if (taskHandler.getNumberOfTasks() == 0) {
            ui.startMessage()
                    .addLine(NO_TASKS_MESSAGE)
                    .displayMessage();
        } else {
            ui.startMessage()
                    .addLine(SOME_TASKS_MESSAGE)
                    .addTasksList(taskHandler.getTasks())
                    .displayMessage();
        }
    }

    @Override
    void parseCommand(String[] tokens) throws ChadInvalidCommandException {
        if (tokens.length > 1) {
            throw new ChadInvalidCommandException(UNNECESSARY_ARGUMENTS_ERROR_MESSAGE);
        }
    }

    @Override
    CommandType getCommandType() {
        return COMMAND_TYPE;
    }
}
