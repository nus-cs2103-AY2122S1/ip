public class UnknownCommand extends Command {

    public UnknownCommand (){}

    public void execute(Storage storage, Ui ui) {
        ui.unknownCommandMessage();
    }

    public boolean isExit() {
        return false;
    }
}
