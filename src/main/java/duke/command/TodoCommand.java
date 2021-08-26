package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.util.Arrays;

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

    @Override
    public boolean equals(Object other) {
        if (other instanceof TodoCommand) {
            TodoCommand otherCommand = (TodoCommand) other;
            return Arrays.equals(this.args, otherCommand.args);
        }
        return false;
    }
}
