public class DoneCommand extends Command {
    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        try {
            String doneString = taskList.markAsDone(index);
            storage.rewrite(taskList);
            ui.showDoneMessage(doneString);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid task number!");
        }
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
