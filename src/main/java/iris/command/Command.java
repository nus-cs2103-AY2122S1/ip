package iris.command;

import iris.IrisException;
import iris.Storage;
import iris.TaskList;
import iris.Ui;

public abstract class Command {
    public boolean isExit() {
        return false;
    }

    public abstract void runSilently(TaskList tasks) throws IrisException;

    public abstract void say(TaskList tasks, Ui ui);

    /**
     * Run the given command's actions (runSilently) and say
     *
     * @param tasks   tasks of current Iris instance
     * @param ui      ui of current Iris instance
     * @param storage storage of current Iris instance
     * @throws IrisException for invalid command
     */
    public void run(TaskList tasks, Ui ui, Storage storage) throws IrisException {
        runSilently(tasks);
        say(tasks, ui);
    };
}
