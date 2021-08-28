package duke;

import java.io.IOException;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.addTask(task);
            ui.showAddTask(tasks, task);
            storage.saveTasks(tasks);
        } catch (IOException e) {
            throw new DukeException("Oops!!! somthing wehnt wrong :/");
        }

    }
}
