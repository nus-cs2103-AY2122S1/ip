package tokio.commands;

import java.io.IOException;

import tokio.storage.Storage;
import tokio.tasks.Task;
import tokio.tasks.TaskList;
import tokio.ui.Ui;

/**
 * Marks task specified by user as done, using index.
 */
public class DoneCommand extends Command {
    protected String cmdLine;
    protected int index;

    /**
     * Marks task as done based on user input.
     *
     * @param cmdLine User input.
     */
    public DoneCommand(String cmdLine) {
        this.cmdLine = cmdLine;
        String[] splitLine = cmdLine.split(" ", 2);
        if (splitLine.length < 2) {
            index = Integer.MAX_VALUE;
        } else {
            index = Integer.parseInt(splitLine[1]);
        }
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        if (index < tasks.getSize() + 1) {
            Task curr = tasks.getTask(index - 1);
            storage.doneTask(curr);
            tasks.doneTask(index - 1);
            return ui.printDoneCommand(curr, tasks);
        } else {
            return ui.printInvalidIndexError();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
