public class DeleteCommand implements Command {
    private String description;

    public DeleteCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int taskId  = Integer.parseInt(description);
            return tasks.deleteTask(taskId);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("delete should be in format: delete [TASK NUMBER]");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
