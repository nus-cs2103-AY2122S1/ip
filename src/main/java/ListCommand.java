public class ListCommand extends Command{

    public ListCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String message = tasks.toString();
        ui.reply(message);
    }
}
