package duke.commands;

import duke.tasks.Task;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

public abstract class AddTaskCommand extends Command {
    private Task task;

    public AddTaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        String[] messages = new String[] {
                "Got it. I've added this task:",
                "    " + task.toString(),
                String.format("Now you have %d tasks in the list.", tasks.getSize())
        };
        ui.printOut(messages);
        storage.save(tasks);
    }

    @Override
    public String toString() {
        return String.format("TO ADD: %s", this.task);
    }
}
