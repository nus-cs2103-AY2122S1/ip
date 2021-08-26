public abstract class Command {
    protected TaskHandler taskHandler;
    protected Storage storage;

    public Command(TaskHandler th, Storage str) {
        this.taskHandler = th;
        this.storage = str;
    }

    public abstract void execute(String cmd);

}
