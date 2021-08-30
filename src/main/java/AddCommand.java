public abstract class AddCommand extends Command {
    private String taskDescription;

    public AddCommand(String type, String taskDescription) {
        super(type);
        this.taskDescription = taskDescription;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    @Override
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);
}
