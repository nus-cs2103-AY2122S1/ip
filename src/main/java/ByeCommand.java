public class ByeCommand extends Command {

    private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";

    public ByeCommand() {
        // nothing to do
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printOut(BYE_MESSAGE);
    }

    @Override
    public boolean hasExited() {
        return true;
    }
}
