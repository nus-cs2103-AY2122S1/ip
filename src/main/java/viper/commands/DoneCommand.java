package viper.commands;

import java.io.IOException;

import viper.storage.Storage;
import viper.tasks.Task;
import viper.tasks.TaskList;
import viper.ui.Ui;

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
        index = Integer.parseInt(splitLine[1]);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        if (index < tasks.getSize() + 1) {
            Task curr = tasks.getTask(index - 1);
            storage.doneTask(curr);
            tasks.doneTask(index - 1);
            String[] msg = {"Good job on completing your task!!!", "I've marked this task as done:", curr.toString()};
            ui.showMessage(msg);
        } else {
            ui.showInvalidIndexError();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
