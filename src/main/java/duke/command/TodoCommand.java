package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class TodoCommand extends Command {
    public TodoCommand(String[] args) {
        super(args);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTodoTask(args[0]);
        storage.writeToFile(tasks);
        ui.showNewTask(tasks.lastTask(), tasks.size());
    }

    public boolean isExit() {
        return false;
    }
}
