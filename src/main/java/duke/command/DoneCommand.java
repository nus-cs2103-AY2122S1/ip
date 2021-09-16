package duke.command;

import duke.exception.DukeException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Utils;
import duke.exception.TaskNotFoundException;
import duke.task.Task;

public class DoneCommand extends Command {

    public DoneCommand(String userCommand, String userArgument) {
        super(userCommand, userArgument);
    }

    public String execute(TaskList tasks, Storage storage) throws DukeException {

        assert userArgument != null;

        int done = Utils.getInputNumber(userArgument);

        if (done >= tasks.numberOfTasks() || done < 0) {
            throw new TaskNotFoundException();
        }

        Task doneTask = tasks.getTask(done);
        doneTask.markAsDone();
        storage.editTaskFromFile(done, tasks);

        return String.format("I've marked this task as done:\n"
                + "%s\n", doneTask.toString());
    }

}
