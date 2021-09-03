package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UiInterface;
import duke.exception.DukeException;
import duke.tasks.Task;

public class Add extends Command {

    private final Task t;

    public Add(Task t) {
        this.t = t;
    }

    @Override
    public void execute(TaskList taskList, UiInterface ui, Storage storage) throws DukeException {
        taskList.addTask(t);
        String plurality = " task";
        if (taskList.getSize() != 1) {
            plurality += "s";
        }

        ui.print("Got it! I've added this task:\n   "
                + t.toString() + "\nNow you have " + taskList.getSize()
                + plurality + " in the list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
