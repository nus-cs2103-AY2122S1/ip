package duke;

public class ListCommand extends Command {
    /**
     * A command that displays the tasks in the list.
     * @param tasks
     * @param u
     * @param storage
     */
    @Override
    public void execute(TaskList tasks, Ui u, Storage storage) {
        u.displayTaskList(tasks);
    }
}
