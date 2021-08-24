public class ExitCommand extends Command {
    @Override
    public void execute(Tasklist task, UI ui, FileManager fileManager) {
        ui.sayBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
