public class ExitCommand extends Command {
    public ExitCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String message = "Goodbye, hope to see you again soon!";
        ui.reply(message);
    }
}
