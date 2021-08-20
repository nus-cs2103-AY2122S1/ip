public class ExitCommand extends Command {

    @Override
    public void execute(Tasklist tasklist, Storage storage, Ui ui) {
        // Does nothing
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
