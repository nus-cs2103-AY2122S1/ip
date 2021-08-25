public class DisplayCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.displayList();
    }

    public boolean isExit() {
        return false;
    }
}
