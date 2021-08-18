public class MarkDoneCommand extends Command{
    private final int index;

    public MarkDoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage store) {
        tasklist.get(index).setFlag(true);
        ui.notifySuccessfulMarkDone(tasklist, index);
    }
}
