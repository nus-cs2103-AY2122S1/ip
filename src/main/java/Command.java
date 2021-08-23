public abstract class Command {
    private String description;

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        description = runAndGenerateDescription(taskList, ui, storage);
    }

    abstract String runAndGenerateDescription(TaskList taskList, Ui ui, Storage storage);

    public String getDescription() {
        return description;
    }

    public boolean isExit() {
        return false;
    }
}
