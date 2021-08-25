public class ExitCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Bye bye! See you again soon!");
    }

    public boolean isExit() {
        return true;
    }
}
