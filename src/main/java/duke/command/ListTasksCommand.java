package duke.command;

import duke.task.TaskHandler;
import duke.ui.Ui;

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
    public ListTasksCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskHandler taskHandler, Ui ui) {
        if (taskHandler.getNumberOfTasks() == 0) {
            ui.startMessage()
                    .addLine("You have no tasks in the list.")
                    .printFormatted();
        } else {
            ui.startMessage()
                    .addLine("Here are the tasks in your list:")
                    .addTasksList(taskHandler.getTasks())
                    .printFormatted();
        }
    }

    @Override
    void parseCommand(String[] tokens) {}

    @Override
    CommandType getCommandType() {
        return COMMAND_TYPE;
    }
}
