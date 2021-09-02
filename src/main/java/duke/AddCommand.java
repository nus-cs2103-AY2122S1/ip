package duke;

/**
 * Command that adds tasks.
 */
public class AddCommand extends Command {
    private Task taskToBeAdded;

    public AddCommand(Task taskToBeAdded) {
        this.taskToBeAdded = taskToBeAdded;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        storage.addTaskToFile(taskToBeAdded);
        tasks.add(taskToBeAdded);
        return ui.addTask(taskToBeAdded, tasks.size());
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AddCommand) {
            return true;
        } else {
            return false;
        }
    }

}
