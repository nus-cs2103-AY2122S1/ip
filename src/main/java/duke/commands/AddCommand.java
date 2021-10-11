package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.TaskType;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

public class AddCommand extends Command {
    private TaskType taskType;
    private String[] taskDescription;

    public AddCommand(TaskType taskType, String[] taskDescription) {
        this.taskType = taskType;
        this.taskDescription = taskDescription;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task;
        switch (this.taskType) {
        case TODO:
            task = new Todo(taskDescription[0]);
            break;
        case DEADLINE:
            task = new Deadline(taskDescription[0], taskDescription[1]);
            break;
        case EVENT:
            task = new Event(taskDescription[0], taskDescription[1]);
            break;
        default:
            throw new DukeException("Unexpected value: " + this.taskType);
        }
        tasks.addTask(task);
        storage.appendTask(task);
        return ui.showAdded(task, tasks.getLength());
    }
}
