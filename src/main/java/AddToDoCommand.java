import java.io.IOException;

public class AddToDoCommand extends AddCommand {
    public AddToDoCommand(String type, String taskDescription) {
        super(type, taskDescription);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            addToDoTask(taskList, ui);
            storage.saveTasksToFile(taskList);
        } catch (DukeException | IOException e) {
            ui.displayError(e.getMessage());
        }
    }

    public void addToDoTask(TaskList taskList, Ui ui) throws DukeException {
        Task newToDoTask = new ToDo(TaskType.TODO, super.getTaskDescription());
        taskList.add(newToDoTask);

        String response = ui.taskAddedMessage(newToDoTask)
                + System.lineSeparator() + ui.getNumOfTasksInList(taskList);
        ui.displayResponse(response);
    }
}
