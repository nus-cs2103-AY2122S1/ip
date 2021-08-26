package duke;

public class MarkDoneCommand extends Command {

    private boolean isExit;
    private int index;
    
    public MarkDoneCommand(int index) {
        this.isExit = false;
        this.index = index;
    }
    
    @Override
    public boolean isExit() {
        return isExit;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        boolean markedDone = list.markDoneAtIndex(index);
        if (markedDone) {
            String message =
                    "Nice! I've marked this task as done:\n" +
                    list.get(index);
            ui.printMessage(message);
            storage.save(list.convertToStorageString());
        } else {
            throw new InvalidIndexException();
        }
    }
}
