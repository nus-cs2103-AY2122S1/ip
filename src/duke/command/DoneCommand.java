package duke.command;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    protected final int taskNum;

    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (0 <= taskNum && taskNum < tasks.size()) {
            tasks.get(taskNum).markAsDone();
            ui.showFramedMsg("Nice! I've marked this task as done:\n  "
                    + tasks.get(taskNum).toString());
            storage.update(tasks);
        } else {
            throw new DukeException("OOPS!!! Please enter a valid task number ☹");
        }
    }
}
