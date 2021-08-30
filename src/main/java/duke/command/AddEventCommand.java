package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskType;

import java.io.IOException;

public class AddEventCommand extends AddCommand {
    private String taskDateTime;

    public AddEventCommand(String type, String taskInfo) {
        super(type, taskInfo.split("/", 2)[0].trim());

        String taskAtDateTime = taskInfo.split("/", 2)[1].trim();
        taskDateTime = taskAtDateTime.split("\\s+", 2)[1].trim();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            addEventTask(taskList, ui);
            storage.saveTasksToFile(taskList);
        } catch (DukeException | IOException e) {
            ui.displayError(e.getMessage());
        }
    }

    public void addEventTask(TaskList taskList, Ui ui) throws DukeException {
        Task newEventTask = new Event(TaskType.EVENT, super.getTaskDescription(), taskDateTime);
        taskList.add(newEventTask);

        String response = ui.taskAddedMessage(newEventTask)
                + System.lineSeparator() + ui.getNumOfTasksInList(taskList);
        ui.displayResponse(response);
    }
}
