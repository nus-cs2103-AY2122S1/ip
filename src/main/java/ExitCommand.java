public class ExitCommand extends Command {
    public ExitCommand() {}

    public void execute(Storage storage, Ui ui) {
        ui.closeReader();
        storage.saveToFile();
        ui.goodbyeMessage();
    }

    public boolean isExit() {
        return true;
    }

}
