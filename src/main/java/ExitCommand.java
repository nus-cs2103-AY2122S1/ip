public class ExitCommand extends Command { //ExitCommand to handle the shutting down of bots

    public ExitCommand() {
            super(false);
    }

    @Override
    public void execute (TaskList taskList, Ui ui, Storage storage) {
        ui.showGoodBye();
    }
}
