package Duke;

public class DoneCommand implements ICommand {

    String taskIndex;

    public DoneCommand(String taskIndex) {
        this.taskIndex = taskIndex;
    }

    public void execute(TaskManager tm, Ui ui, Storage storage) {
        try {
            Task completedTask = tm.completeTask(taskIndex);
            if (completedTask == null) {
                ui.printInvalidIndexMessage();
            } else {
                ui.printTaskCompletion(completedTask, tm.getTasks().size());
                storage.updateSave(tm.getTasks());
            }
        } catch (DukeException.InvalidInputException e) {
            ui.printErrorMessage(e);
        }
    }

}
