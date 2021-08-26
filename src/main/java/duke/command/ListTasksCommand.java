package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.task.Task;

public class ListTasksCommand extends Command {
    /**
     * Creates a command that lists all the user's tasks.
     * @param arguments Command arguments.
     */
    public ListTasksCommand(String arguments) throws Exception {
        if (arguments.length() > 0) {
            throw new Exception("Command `list` does not accept arguments");
        }
    }

    /**
     * Lists all tasks to the user.
     * @param taskList The tasklist.
     * @param ui The instance of the {@link Ui} class.
     * @param storage The instance of the {@link Storage} class.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        StringBuilder builder = new StringBuilder();
        int numTasks = taskList.size();

        if (numTasks == 0) {
            ui.printMessage("No tasks saved");
        } else {
            for (int i = 0; i < numTasks; i++) {
                Task item = taskList.getTask(i);
                builder.append(i + 1);
                builder.append(". ");
                builder.append(item.toString());
                if (i < numTasks - 1) {
                    builder.append("\n");
                }
            }

            ui.printMessage(builder.toString());
        }
    }

    public boolean shouldExit() {
        return false;
    }
}
