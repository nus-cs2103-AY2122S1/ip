package duke.command;

import duke.Pair;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.Arrays;

public class DoneCommand extends Command {
    public DoneCommand(String[] args) {
        super(args);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int taskNo = Integer.parseInt(args[0]);
        Pair<Boolean, Task> statusTaskPair = tasks.markTaskDone(taskNo);
        boolean isTaskAlreadyDone = statusTaskPair.getFirst();
        Task task = statusTaskPair.getSecond();

        if (isTaskAlreadyDone) {
            ui.showError(String.format("Task %s is already done!\n  %s", taskNo + 1, task));
        } else {
            ui.showDoneTask(task);
        }
        storage.writeToFile(tasks);
    }

    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof DoneCommand otherCommand) {
            return Arrays.equals(this.args, otherCommand.args);
        }
        return false;
    }
}
