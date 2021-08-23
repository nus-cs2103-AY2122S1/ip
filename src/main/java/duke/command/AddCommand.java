package duke.command;


import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.Ui;

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

    @Override
    public void execute() {
        tasks.add(task);
        storage.save(tasks);
        ui.add(task, tasks);
    }
}
