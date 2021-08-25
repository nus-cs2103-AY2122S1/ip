public class ExitCommand implements Command {

    @Override
    public void execute(TaskList tasks, Storage storage) {
        Ui.sayGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
