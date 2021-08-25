public class DeleteTask extends Command {
    // zero-indexed
    int taskNum;

    public DeleteTask(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    void execute(ToDo taskList, Ui ui, Storage storage) throws KermitException {
        try {
            Task deletedTask = taskList.deleteTask(taskNum);
            ui.showDeleteTaskMessage(deletedTask, taskList);
            storage.save(taskList);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new KermitException("That is an invalid task!");
        }
    }

    @Override
    boolean isExit() {
        return false;
    }
}