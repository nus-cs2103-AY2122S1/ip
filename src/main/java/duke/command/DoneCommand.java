package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Utils;
import duke.task.Task;

public class DoneCommand extends Command {

    public DoneCommand(String userCommand, String userArgument) {
        super(userCommand, userArgument);
    }

    public String execute(TaskList tasks, Storage storage) throws DukeException {
        int done = Utils.getInputNumber(userArgument);

        if (done >= tasks.numberOfTasks() || done < 0) {
            throw new DukeException("Task does not exist!");
        }

        Task doneTask = tasks.getTask(done);
        doneTask.markAsDone();
        storage.editTaskFromFile(done, tasks);

        return String.format("I've marked this task as done:\n" +
                "%s\n", doneTask.toString());
    }

}
