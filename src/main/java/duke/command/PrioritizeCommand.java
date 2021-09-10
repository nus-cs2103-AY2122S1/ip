package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Priority;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class PrioritizeCommand extends Command {
    private int index;
    private Priority priority;

    public PrioritizeCommand(int index, Priority priority) {
        this.index = index;
        this.priority = priority;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        taskList.getTask(this.index - 1).setPriority(this.priority);
        ui.sayPrioritizeTask(this.index, this.priority);
        storage.store(taskList.serialize());
    }
}
