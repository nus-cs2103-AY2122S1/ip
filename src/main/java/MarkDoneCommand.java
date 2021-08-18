public class MarkDoneCommand extends Command{
    private int index;

    public MarkDoneCommand(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage store) {
        tasklist.get(index).setFlag(true);
        ui.notifySuccessfulMarkDone(tasklist, index);
    }
}
