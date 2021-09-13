package duke.command;

import java.time.LocalDateTime;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskType;
import duke.task.Todo;
import duke.util.DukeException;
import duke.util.Storage;


/**
 * Add task to task list. A Task can be of type Event, Deadline or Todo.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructs an command that adds a task to the task list.
     *
     * @param taskType type of task.
     * @param name task name.
     * @param dateTime date time information of task.
     */
    public AddCommand(TaskType taskType, String name, LocalDateTime dateTime) {
        switch (taskType) {
        case DEADLINE:
            this.task = new Deadline(false, name, dateTime);
            break;
        case EVENT:
            this.task = new Event(false, name, dateTime);
            break;
        default:
            this.task = new Todo(false, name);
        }
    }

    /**
     * Adds the appropriate task to the task list and return a string
     *  confirmation response.
     *
     * @param storage storage instance initialised when duke is created.
     * @param taskList task list instance initialised when duke is created.
     * @return String of confirmation message to indicate task added.
     */
    @Override
    public String execute(Storage storage, TaskList taskList) throws DukeException {
        taskList.addTask(task);
        storage.writeToDisk(taskList.compileTasks());
        return String.format("Can Do!\n  added: %s\nLook at me! %d tasks in the list now!",
                this.task,
                taskList.getSize());
    }
}
