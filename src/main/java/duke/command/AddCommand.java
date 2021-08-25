package duke.command;

import duke.task.Task;
import duke.task.TaskType;
import duke.task.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.Ui;

import java.time.LocalDateTime;

/**
 * Add task to task list. A Task can be of type Event, Deadline or Todo.
 */
public class AddCommand extends Command {
    Task task;

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
        case TODO:
            this.task = new Todo(false, name);
            break;
        }
    }

    /**
     * Add the appropriate task to the task list and display confirmation response to
     * the user.
     *
     * @param storage storage instance initialised when duke is created.
     * @param taskList task list instance initialised when duke is created.
     * @param ui ui instance initialised when duke is created.
     */
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws DukeException {
            taskList.addTask(task);
            storage.writeToDisk(taskList.compileTasks());
            ui.respond(
                String.format("Caan Do!\n  added: %s\nLook at me! %d tasks in the list now!",
                    this.task,
                    taskList.getSize())
            );
    }
}
