public class DoneCommand extends Command{
    private boolean isExit = false;
    private int taskid;


    public DoneCommand(int taskid) {
        this.taskid = taskid;
    }

    @Override
    public void execute(TaskList tasks, UI userInt, Storage storage) throws DukeException {
        Task doneTask = tasks.markDone(this.taskid);
        userInt.notifyDone(doneTask);
        storage.save(tasks);
    }

    public boolean isExit() {
        return this.isExit;
    }
}
