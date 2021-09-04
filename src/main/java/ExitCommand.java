public class ExitCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui u, Storage storage) {
        u.sayBye();
    }
}
