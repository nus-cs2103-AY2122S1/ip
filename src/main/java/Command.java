public abstract class Command {
    private String command;
    
    public Command(String command) {
        this.command = command;
    }
    
    public abstract void execute(TaskList taskList, Storage storage);
}