package duke;

public class ListSortedCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui u, Storage storage) {
        tasks.sortDeadlineList();
        u.displaySortedTaskList(tasks);
    }
}
