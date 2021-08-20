public class MarkDoneCommand extends Command {
    private final int parseInt;

    public MarkDoneCommand(int parseInt) {
        this.parseInt = parseInt;
    }

    @Override
    public void execute(Tasklist tasklist, Storage storage, Ui ui) {
        tasklist.markDone(this.parseInt);
        storage.save(tasklist);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}