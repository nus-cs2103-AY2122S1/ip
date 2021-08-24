public class ExitCommand extends Command {
    public static final String INSTRUCTION = "bye";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFarewell();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
