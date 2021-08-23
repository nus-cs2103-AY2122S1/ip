public class DeleteCommand extends Command {
    private int taskNum;

    public DeleteCommand (int taskNum) {
        this.taskNum = taskNum;
    }

    public void execute(Storage storage, Ui ui) {
        try {
            int taskListLen = storage.taskListLen();
            if (taskNum + 1 <= taskListLen) {
                Task removedTask = storage.deleteTask(taskNum);
                taskListLen -= 1;
                ui.taskDeletedMessage(removedTask, taskListLen);
            } else {
                ui.missingTaskMessage();
            }
        } catch (IndexOutOfBoundsException e) {
            Ui.showErrorMessage("Please enter a valid index!");
        }
    }

    public boolean isExit() {
        return false;
    }
}
