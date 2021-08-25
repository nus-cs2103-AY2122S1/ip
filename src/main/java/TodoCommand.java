public class TodoCommand extends SaberCommand {
    private Todo todo;
    private boolean isMissingDescription;

    private TodoUI todoUI = new TodoUI();

    public TodoCommand(String task, boolean isMissingDescription) {
        Todo todo = new Todo(task, false);
        this.todo = todo;
        this.isMissingDescription = isMissingDescription;
    }

    public void execute (TaskList taskList) {
        if (isMissingDescription) {
            todoUI.showMissingDescriptionError();
            return;
        }
        taskList.add(todo);
        int totalTask = taskList.size();
        todoUI.setSuccessMessage(todo, totalTask);
        todoUI.showSuccess();
    }

    public boolean isExit() {
        return false;
    }
}
