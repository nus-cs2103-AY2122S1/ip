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
                    .addLine("You have no tasks in the list.")
                    .displayMessage();
        } else {
            ui.startMessage()
                    .addLine("Here are the tasks in your list:")
                    .addTasksList(taskHandler.getTasks())
                    .displayMessage();
        }
    }

    @Override
    void parseCommand(String[] tokens) throws ChadInvalidCommandException {
        if (tokens.length > 1) {
            throw new ChadInvalidCommandException("There were unnecessary arguments.");
        }
    }

    @Override
    CommandType getCommandType() {
        return COMMAND_TYPE;
    }
}
