public class ByeCommand extends Command {

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {}

    @Override
    public boolean isExit() {
        return true;
    }
}
