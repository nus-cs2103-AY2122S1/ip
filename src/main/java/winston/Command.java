package winston;

/**
 * Represents the commands that will be used during the usage of winston
 */
public abstract class Command {
    protected TaskList taskList;
    
    public Command(TaskList taskList) {
        this.taskList = taskList;
    }
    
    public abstract String run();
}
