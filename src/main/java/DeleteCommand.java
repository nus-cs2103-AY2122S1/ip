public class DeleteCommand extends Command {
    private final int parseInt;

    public DeleteCommand(int parseInt) {
        this.parseInt = parseInt;
    }

    @Override
    public void execute(Tasklist tasklist, Storage storage, Ui ui) {
        tasklist.deleteTask(this.parseInt);
        storage.save(tasklist);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}