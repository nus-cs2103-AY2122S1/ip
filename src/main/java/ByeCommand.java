public class ByeCommand extends Command {

    @Override
    public void execute(Tasklist tasks, Storage storage, Ui ui) {
        ui.byeResponse();
        ui.stopLoop();
    }
}
