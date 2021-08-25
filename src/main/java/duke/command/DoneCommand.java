package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidArgumentException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class DoneCommand extends Command {
    private int doneTask;

    public DoneCommand(int doneTask) {
        this.doneTask = doneTask;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        // Check for invalid task argument
        if (doneTask >= tasks.getSize()) {
            throw new InvalidArgumentException(tasks.getSize());
        }

        tasks.getTask(doneTask).markAsDone();

        String response = "Nice! I've marked this task as done:\n" +
                "       " + tasks.getTask(doneTask);
        ui.showResponse(response);

        storage.save(tasks.getTaskList());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
