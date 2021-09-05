package tokio.commands;

import java.io.IOException;

import tokio.storage.Storage;
import tokio.tasks.Task;
import tokio.tasks.TaskList;
import tokio.ui.Ui;

/**
 * Deletes command specified by user based on index
 */
public class DeleteCommand extends Command {
    private String cmdLine;
    private int index;

    /**
     * Deletes command based on user input.
     *
     * @param cmdLine User input.
     */
    public DeleteCommand(String cmdLine) {
        this.cmdLine = cmdLine;
        String[] splitLine = cmdLine.split(" ", 2);
        if (splitLine.length < 2) {
            this.index = Integer.MAX_VALUE;
        } else {
            this.index = Integer.parseInt(splitLine[1]);
        }
    }

    /**
     * Executes the delete command.
     *
     * @param tasks current tasklist.
     * @param ui user input format.
     * @param storage stores created command into the txt file.
     * @throws IOException if task to be deleted is null.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        if (index < tasks.getSize() + 1) {
            Task deleteTask = tasks.getTask(index - 1);
            tasks.deleteTask(index - 1);
            storage.deleteTask(deleteTask);
            return ui.printDeleteCommand(deleteTask, tasks);
        } else {
            return ui.printInvalidIndexError();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
