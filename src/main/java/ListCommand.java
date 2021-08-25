public class ListCommand extends Command{

    public ListCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        String message = tasks.toString();
        System.out.println(message);
    }
}
