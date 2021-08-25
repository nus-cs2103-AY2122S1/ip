abstract class Command {
    private final boolean isExit;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }
    
    abstract public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return this.isExit;
    }
    
}
