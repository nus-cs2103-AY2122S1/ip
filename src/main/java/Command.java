public abstract class Command {
    protected TaskList taskList;

    public Command(TaskList tasklist) {
        this.taskList = tasklist;
    }

    abstract void execute();
}
