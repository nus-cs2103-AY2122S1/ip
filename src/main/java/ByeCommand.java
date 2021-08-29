public class ByeCommand extends Command {

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(Tasklist tasklist, Ui ui, Storage storage) {
        ui.showByeMessage();
    }

}