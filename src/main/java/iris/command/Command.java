package iris.command;

import iris.IrisException;
import iris.Storage;
import iris.TaskList;
import iris.Ui;

public abstract class Command {
    public abstract void runSilently(TaskList tasks) throws IrisException;

    public abstract void say(TaskList tasks, Ui ui);

    public void run(TaskList tasks, Ui ui, Storage storage) throws IrisException {
        runSilently(tasks);
        say(tasks, ui);
    };
}
