public class ListCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui u, Storage storage) {
        u.displayTaskList(tasks);
    }
}
