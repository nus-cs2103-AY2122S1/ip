package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class DeadlineCommand extends Command {
    public DeadlineCommand(String[] args) {
        super(args);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addDeadlineTask(args[0], args[1]);
        storage.writeToFile(tasks);
        ui.showNewTask(tasks.lastTask(), tasks.size());
    }

    public boolean isExit() {
        return false;
    }
}
