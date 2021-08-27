package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.Arrays;

public class DeleteCommand extends Command {
    public DeleteCommand(String[] args) {
        super(args);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.deleteTask(Integer.parseInt(args[0]));
        storage.writeToFile(tasks);
        ui.showDeletedTask(task, tasks.size());
    }

    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof DeleteCommand otherCommand) {
            return Arrays.equals(this.args, otherCommand.args);
        }
        return false;
    }
}
