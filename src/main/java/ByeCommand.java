public class ByeCommand extends Command {
    public static final String COMMAND = "Bye!";

    public ByeCommand() {
        this.isExit = true;
    }

    @Override
    public void execute(List list, Ui ui, Storage storage) {
        ui.printBye();
    }
}
