public class ByeCommand extends Command {
    
    public ByeCommand() {
        super(true);
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        ui.showBye();
    }
}
