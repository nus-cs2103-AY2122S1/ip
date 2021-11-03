package duke.command;

import duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

import duke.Storage;
import duke.Ui;

/**
 * Represents a command to add a task to be done
 */
public class TodoCommand extends Command {

    public static final String COMMAND_WORD = "todo";
    public static final String USAGE_TEXT = "Usage: todo [description]";

    /** Description of task to be done */
    private String description;

    /**
     * TodoCommand constructor.
     *
     * @param desc Description of task to be done.
     */
    public TodoCommand(String desc) {
        description = desc;
    }

    /**
     * Adds new Todo task to given TaskList and displays the relevant message.
     *
     * @param taskList List of tasks.
     * @param ui User interface.
     * @param storage Storage of tasks.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = new Todo(description);
        taskList.addTask(task);
        return ui.showTasksReply(true, task.toString(), taskList.size());
    }
}
