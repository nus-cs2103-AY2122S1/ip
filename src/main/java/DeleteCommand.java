public class DeleteCommand implements Command {
    private int deleteIndex;

    public DeleteCommand(int deleteIndex) {
        this.deleteIndex = deleteIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (deleteIndex < 0 || deleteIndex >= tasks.size()) {
            throw new DukeException("Task with that task number does not exist.");
        }
        Task deletedTask = tasks.delete(deleteIndex);
        ui.announceTaskDeletion(deletedTask, tasks.size());
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
