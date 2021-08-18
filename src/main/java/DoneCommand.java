public class DoneCommand extends Command {
    public DoneCommand() {
        this.isDone = true;
    }

    public void execute(TaskList tasklist, Ui ui, Storage store) {
        return;
    }
}
