package duke.command;

import java.io.FileNotFoundException;

import duke.Storage;
import duke.UI;
import duke.task.TaskList;

public class DoneCommand extends Command {

    private int taskNumber;

    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws FileNotFoundException {
        tasks.markTaskAsDone(taskNumber, storage, ui);
    }
}
