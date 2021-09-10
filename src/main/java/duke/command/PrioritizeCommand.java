package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Priority;
import duke.task.TaskList;
import duke.ui.Ui;

public class PrioritizeCommand extends Command {
    private int index;
    private Priority priority;

    /**
     * Constructs PrioritizeCommand objects.
     *
     * @param index The index of task to be set its priority.
     * @param priority The priority level.
     */
    public PrioritizeCommand(int index, Priority priority) {
        this.index = index;
        this.priority = priority;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        taskList.prioritizeTask(this.index - 1, this.priority);
        ui.sayPrioritizeTask(this.index, this.priority);
        storage.store(taskList.serialize());
    }
}
