public class ByeCommand extends Command{
    ByeCommand(String input) {
        super(input);
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui) {
        ui.goodbye();
        return true;
    }
}
