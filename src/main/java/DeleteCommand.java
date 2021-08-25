public class DeleteCommand extends SaberCommand {
    private int taskIndex;
    private boolean isBadArgument;

    private DeleteUI deleteUI = new DeleteUI();

    public DeleteCommand(int taskIndex, boolean isBadArgument) {
        this.taskIndex = taskIndex;
        this.isBadArgument = isBadArgument;
    }

    public void execute (TaskList taskList) {
        if (isBadArgument) {
            deleteUI.showArgumentError();
            return;
        }
        if (taskIndex >= taskList.size() || taskIndex < 0) {
            deleteUI.showUnableToFindTaskError();
            return;
        }
        Task task = taskList.get(taskIndex);
        taskList.delete(taskIndex);
        int totalTask = taskList.size();
        deleteUI.setSuccessMessage(task, totalTask);
        deleteUI.showSuccess();
    }

    public boolean isExit() {
        return false;
    }
}
