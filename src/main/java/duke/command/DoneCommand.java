package duke.command;

import duke.data.exceptions.DukeException;
import duke.data.exceptions.InvalidInputException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class DoneCommand extends Command {
    private int taskNumber;

    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    private Task markTaskAsDone(TaskList tasks) throws InvalidInputException {
        int taskPosition = taskNumber - 1;
        if (taskPosition >= tasks.size()) {
            throw new InvalidInputException("invalid task number entered");
        } else {
            Task taskMarked = tasks.getTask(taskPosition);
            taskMarked.markAsDone();
            return taskMarked;
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task markedTask = markTaskAsDone(taskList);
        storage.update(taskList);
        ui.showMarkTaskMessage(markedTask);
    }
}
