abstract public class Command {
    private String desc;
    public Command (String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return this.desc;
    }
    abstract boolean isExit();
    abstract void execute(TaskList tasks, Storage storage);
}
