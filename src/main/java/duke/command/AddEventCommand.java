package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskType;

import java.io.IOException;

/**
 * A class that contains methods to add Event tasks
 */
public class AddEventCommand extends AddCommand {
    private String taskDateTime;

    /**
     * Initializes an instance of AddEventCommand class
     * @param type Type of task
     * @param taskInfo Further information about the task
     */
    public AddEventCommand(String type, String taskInfo) {
        super(type, taskInfo.split("/", 2)[0].trim());

        String taskAtDateTime = taskInfo.split("/", 2)[1].trim();
        taskDateTime = taskAtDateTime.split("\\s+", 2)[1].trim();
    }

    /**
     * Executes an Event task
     * @param taskList List of tasks
     * @param ui An object to handle i/o operations through screen
     * @param storage An object to read from and write to file
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            addEventTask(taskList, ui);
            storage.saveTasksToFile(taskList);
        } catch (DukeException | IOException e) {
            ui.displayError(e.getMessage());
        }
    }

    /**
     * Adds an Event task
     * @param taskList List of tasks
     * @param ui An object to handle i/o operations through screen
     * @throws DukeException Any exception
     */
    public void addEventTask(TaskList taskList, Ui ui) throws DukeException {
        Task newEventTask = new Event(TaskType.EVENT, super.getTaskDescription(), taskDateTime);
        taskList.add(newEventTask);

        String response = ui.taskAddedMessage(newEventTask)
                + System.lineSeparator() + ui.getNumOfTasksInList(taskList);
        ui.displayResponse(response);
    }
}
