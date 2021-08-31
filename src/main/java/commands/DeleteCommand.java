package commands;

import viper.Storage;
import viper.TaskList;
import viper.Ui;
import tasks.Task;

import java.io.IOException;

/**
 * Deletes command specified by user based on index
 */
public class DeleteCommand extends Command {
    String t;
    String[] s;
    int index;
    Task deleteTask;

    public DeleteCommand(String t) {
        this.t = t;
        this.s = t.split(" ", 2);
        this.index = Integer.parseInt(s[1]);
    }

    /**
     * Executes the delete command.
     * @param tasks current tasklist.
     * @param ui user input format.
     * @param storage stores created command into the txt file.
     * @throws IOException if task to be deleted is null.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        if (index < tasks.getSize() + 1) {
            deleteTask = tasks.getTask(index - 1);
            
            tasks.deleteTask(index - 1);
            storage.deleteTask(deleteTask);
            String[] msg = {"OK!! I have removed the following task from your list: " + deleteTask,
                    "Now you have " + tasks.getSize() + " task(s) left~ Yay!!!!!"};
            ui.showMessage(msg);
        }
        else {
            ui.showInvalidIndexError();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
