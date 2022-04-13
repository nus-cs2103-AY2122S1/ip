package duke.command;

import duke.DukeException;
import duke.task.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a Command that adds a Task to the TaskList.
 */
public class AddCommand extends Command {
    /**
     * The type of task to add to the task list.
     */
    private final TaskList.TaskType taskType;
    /**
     * The description of the task to add to the task list.
     */
    private final String taskDescription;

    /**
     * Constructs an add command with type of task and task description.
     *
     * @param taskType    The type of task to add to the task list.
     * @param description The description of the task to add to the task list.
     */
    public AddCommand(TaskList.TaskType taskType, String description) {
        this.taskType = taskType;
        this.taskDescription = description;
    }

    /**
     * Executes the add task command.
     *
     * @param tasks   The task list to execute the command on.
     * @param storage The storage for the tasks.
     * @return a string output.
     * @throws DukeException
     */
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        Task task = tasks.addTask(taskType, taskDescription);

        storage.save(tasks.getListData());

        return formatOutput("I've added this task:", task.toString(),
            "Now you have " + tasks.getListSize() + " tasks in the list.");
    }

    /**
     * Returns false to continue the program.
     *
     * @return false.
     */
    public boolean isExit() {
        return false;
    }
}
