package duke;

public class DoneCommand implements Command {
    private int doneIndex;

    public DoneCommand(int doneIndex) {
        this.doneIndex = doneIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (doneIndex < 0 || doneIndex >= tasks.size()) {
            throw new DukeException("Task with that task number does not exist.");
        }
        ui.announceTaskCompletion(tasks.completeTask(doneIndex));
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
