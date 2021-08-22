public class ExitCommand extends Command {
    public void execute(TaskList tasks, UI ui, FileController fc) {
        ui.printText("Bye bye");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
