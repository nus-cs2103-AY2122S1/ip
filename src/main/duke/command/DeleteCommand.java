package duke.command;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    protected final int taskNum;

    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (0 <= taskNum && taskNum < tasks.size()) {
            tasks.get(taskNum).markAsDone();
            ui.showFramedMsg("Got it. I've removed this task:\n  "
                    + tasks.remove(taskNum)
                    + "\nNow you have " + tasks.size() + " tasks in the list.");
            storage.update(tasks);
        } else {
            throw new DukeException("OOPS!!! Please enter a valid task number ☹");
        }
    }
}
