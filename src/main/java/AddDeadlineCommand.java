import java.io.IOException;

public class AddDeadlineCommand extends AddCommand {
    private String taskDateTime;

    public AddDeadlineCommand(String type, String taskInfo) {
        super(type, taskInfo.split("/", 2)[0]);

        String taskByDateTime = taskInfo.split("/", 2)[1];
        taskDateTime = taskByDateTime.split("\\s+", 2)[1];
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            addDeadlineTask(taskList, ui);
            storage.saveTasksToFile(taskList);
        } catch (DukeException | IOException e) {
            ui.displayError(e.getMessage());
        }
    }

    public void addDeadlineTask(TaskList taskList, Ui ui) throws DukeException {
        Task newDeadlineTask = new Deadline(TaskType.DEADLINE, super.getTaskDescription(), taskDateTime);
        taskList.add(newDeadlineTask);

        String response = ui.taskAddedMessage(newDeadlineTask)
                + System.lineSeparator() + ui.getNumOfTasksInList(taskList);
        ui.displayResponse(response);
    }
}
