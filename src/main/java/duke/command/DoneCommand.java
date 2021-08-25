package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.TaskList;

public class DoneCommand extends Command {
    private int taskNumber;

    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.doneTask(taskNumber);
        storage.save(tasks);
        ui.showDone(tasks.getTask(taskNumber));
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
