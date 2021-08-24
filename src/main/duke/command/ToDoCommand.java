package duke.command;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.data.task.ToDo;
import duke.storage.Storage;
import duke.ui.Ui;

public class ToDoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    protected final ToDo newTask;

    public ToDoCommand(String description) {
        this(new ToDo(description));
    }

    public ToDoCommand(ToDo newTask) {
        this.newTask = newTask;
    }

    /**
     * Execute the command to add a task to the given TaskList.
     *
     * @param tasks   The TaskList of the Duke instance.
     * @param ui      The UI handler of the Duke instance.
     * @param storage The storage handler of the Duke instance.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(newTask);
        storage.update(tasks);
        ui.showFramedMsg("Got it. I've added this task:\n  "
                + newTask
                + "\nNow you have " + tasks.size() + " tasks in the list.");
    }
}