public class DoneCommand extends Command {
    private int taskNum;

    public DoneCommand (int taskNum) {
        this.taskNum = taskNum;
    }

    public void execute(Storage storage, Ui ui) {
        try {
            Task taskToMark = storage.getTask(taskNum);
            taskToMark.markDone();
            ui.markedDoneMessage(taskToMark);
        } catch (IndexOutOfBoundsException e) {
            Ui.showErrorMessage("Please enter a valid index!");
        }
    }

    public boolean isExit() {
        return false;
    }
}
