public abstract class Command {
    protected TaskList taskList;
    
    public Command(TaskList taskList) {
        this.taskList = taskList;
    }
    
    public abstract void run();
}
