public class BlahCommand extends Command {
    String command;
    public BlahCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.respondToBlah();
    }

    @Override
    public Boolean isExit() {
        return false;
    }
}
