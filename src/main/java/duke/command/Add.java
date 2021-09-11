package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.tasks.Task;
import duke.ui.UiInterface;

public class Add extends Command {

    private final Task t;

    /**
     * Constructs a new Add instance.
     *
     * @param t Task to add
     */
    public Add(Task t) {
        this.t = t;
    }

    /**
     * Executes the Add command.
     *
     * @param taskList Current list of tasks
     * @param ui Ui to interact with user
     * @param storage Storage that allows loading/saving
     * @throws DukeException if an error is encountered
     */
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

    /**
     * Returns if the command is an exit.
     *
     * @return boolean indicating if command is exit
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
