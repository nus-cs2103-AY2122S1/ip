package duke.commands;

import duke.DukeException;
import duke.tasks.Task;
import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class DoneCommand extends Command {
    private final int taskIndex;
    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }
    
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.markTaskDone(taskIndex);
        ui.showMarkDone(task);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DoneCommand) {
            DoneCommand other = (DoneCommand) obj;
            return taskIndex == other.taskIndex;
        }
        return false;
    }
}
