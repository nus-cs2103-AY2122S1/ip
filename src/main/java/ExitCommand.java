public class ExitCommand extends Command{
    private boolean isExit = true;

    @Override
    public void execute(TaskList task, UI userInt, Storage storage) {
        userInt.exit();
    }

    public boolean isExit() {
        return this.isExit;
    }
}
