package duke;

public class ExitCommand extends Command {
    private final boolean isExit;
    
    public ExitCommand() {
        this.isExit = true;
    }
    
    @Override
    public boolean isExit() {
        return this.isExit;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws StorageSavingException {
        String message = "Bye. Hope to see you again soon!";
        ui.printMessage(message);
        ui.closeScanner();
        storage.save(list.convertToStorageString());
    }
}
