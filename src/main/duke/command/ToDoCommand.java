package duke.command;

import duke.data.TaskList;
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
     * Adds a task to the taskList.
     *
     * @param tasks   The task to be added to the list.
     * @param ui      The ui handler for the current Duke instance.
     * @param storage The storage handler fot the current Duke instance.
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