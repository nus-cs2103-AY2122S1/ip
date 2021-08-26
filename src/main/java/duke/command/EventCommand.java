package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class EventCommand extends Command {
    public EventCommand(String[] args) {
        super(args);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addEventTask(args[0], args[1]);
        storage.writeToFile(tasks);
        ui.showNewTask(tasks.lastTask(), tasks.size());
    }

    public boolean isExit() {
        return false;
    }
}
