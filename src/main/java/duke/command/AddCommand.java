package duke.command;


import duke.Storage;
import duke.Ui;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskType;
import duke.task.Todo;

public class AddCommand extends Command {

    private Task task;
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    public AddCommand(Task task, TaskList tasks, Ui ui, Storage storage) {

        this.task = task;
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
    }


    public TaskType getTaskType() {
        if (task instanceof Event) {
            return TaskType.EVENT;
        } else if (task instanceof Todo) {
            return TaskType.TODO;
        } else {
            return TaskType.DEADLINE;
        }
    }

    @Override
    public void execute() {
        tasks.add(task);
        storage.save(tasks);
        ui.add(task, tasks);
    }
}
