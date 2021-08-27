public class DeleteCommand extends Command {
    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    private Task deleteTask(TaskList tasks) throws InvalidInputException {
        int taskPosition = taskNumber - 1;
        if (taskPosition >= tasks.size()) {
            throw new InvalidInputException("invalid task number entered");
        } else {
            Task removedTask = tasks.getTask(taskPosition);
            tasks.removeTask(taskPosition);
            return removedTask;
        }
    }

    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task removedTask = deleteTask(taskList);
        storage.update(taskList);
        ui.showDeleteTask(removedTask, taskList);
    }
}
