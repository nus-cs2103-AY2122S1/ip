public class DeleteCommand extends Command{
    private boolean isExit = false;
    private int taskid;


    public DeleteCommand(int taskid) {
        this.taskid = taskid;
    }

    @Override
    public void execute(TaskList tasks, UI userInt, Storage storage) throws DukeException {
        Task removedTask = tasks.delete(this.taskid);
        userInt.notifyDelete(removedTask);
        storage.save(tasks);
    }

    public boolean isExit() {
        return this.isExit;
    }
}
