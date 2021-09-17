package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.InvalidCommandException;
import duke.task.Task;
import duke.ui.Ui;

public class ListTasksCommand extends Command {
    /**
     * Creates a command that lists all the user's tasks.
     *
     * @param arguments Command arguments.
     */
    public ListTasksCommand(String arguments) throws InvalidCommandException {
        if (arguments.length() > 0) {
            throw new InvalidCommandException("Command `list` does not accept arguments");
        }
    }

    /**
     * Lists all tasks to the user.
     *
     * @param taskList The tasklist.
     * @param ui The instance of the {@link Ui} class.
     * @param storage The instance of the {@link Storage} class.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        StringBuilder builder = new StringBuilder();
        int numTasks = taskList.size();

        if (numTasks == 0) {
            ui.printMessage("No tasks saved");
        } else {
            for (int i = 0; i < numTasks; i++) {
                Task item = taskList.getTask(i);
                builder.append(i + 1)
                        .append(". ")
                        .append(item.toString());
                if (i < numTasks - 1) {
                    builder.append("\n");
                }
            }

            ui.printMessage(builder.toString());
        }
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}
