package duke;

public class DeleteCommand extends Command {
    
    private boolean isExit;
    private int index;

    public DeleteCommand(int index) {
        this.isExit = false;
        this.index = index;
    }
    
    @Override
    public boolean isExit() {
        return isExit;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        Task removed = list.deleteAtIndex(this.index);
        if (removed != null) {
            String message =
                    "Noted.I've removed this task:\n" +
                    "  " + removed + "\n" +
                    String.format("Now you have %d tasks in the list.", list.size());
            ui.printMessage(message);
            storage.save(list.convertToStorageString());
        } else {
            throw new InvalidIndexException();
        }
    }
}
