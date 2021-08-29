public class DeleteCommand extends Command {
    private int taskNo;

    public DeleteCommand(String taskNo) throws DukeException {
        try {
            this.taskNo = Integer.parseInt(taskNo);
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS! Please enter a valid task number.");
        }
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.deleteTask(taskNo);
    }

    public boolean isExit() {
        return false;
    }
}
