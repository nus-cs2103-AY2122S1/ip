public abstract class Command {
    public abstract void executeCommand(Tasklist taskList, UI ui, PersistentStorage storage) throws DukeException;
    
    public Boolean isExit() {
        return false;
    };
}
