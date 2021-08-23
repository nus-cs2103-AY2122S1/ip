public class ExitCommand implements Command{

    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.stringWithDivider("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
