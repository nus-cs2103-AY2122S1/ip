package duke;

public class UndoCommand extends Command {
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        if (storage.isHistoryEmpty()) {
            throw new DukeException("Can no longer undo any more commands.");
        }
        HistoryState lastHistoryState = storage.popFromHistory();
        tasks.replaceWithTaskList(lastHistoryState.getTaskList());
        storage.updateTasks(tasks);
        ui.showUndone(lastHistoryState);
    }

    @Override
    public String toString() {
        return "Undo previous command Command";
    }
}
