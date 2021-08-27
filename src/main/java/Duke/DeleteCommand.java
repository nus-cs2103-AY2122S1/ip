package Duke;

public class DeleteCommand implements ICommand {

    String taskIndex;

    public DeleteCommand(String taskIndex) {
        this.taskIndex = taskIndex;
    }

    public void execute(TaskManager tm, Ui ui, Storage storage) {
        try {
            Task deletedTask = tm.deleteTask(taskIndex);
            if (deletedTask == null) {
                ui.printInvalidIndexMessage();
            } else {
                ui.printTaskDeletion(deletedTask, tm.getTasks().size());
                storage.updateSave(tm.getTasks());
            }
        } catch (DukeException.InvalidInputException e) {
            ui.printErrorMessage(e);
        }
    }
}