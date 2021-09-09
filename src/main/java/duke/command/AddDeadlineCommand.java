package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskType;

import java.io.IOException;

/**
 * A class that contains methods to add a Deadline command
 */
public class AddDeadlineCommand extends AddCommand {
    private String taskDateTime;

    /**
     * Initializes an instance of AddDeadlineCommand class.
     * @param type Type of task
     * @param taskInfo Further information about the task
     */
    public AddDeadlineCommand(String type, String taskInfo) {
        super(type, taskInfo.split("/", 2)[0].trim());

        String taskByDateTime = taskInfo.split("/", 2)[1].trim();
        taskDateTime = taskByDateTime.split("\\s+", 2)[1].trim();
    }

    /**
     * Executes the task
     * @param taskList List of tasks
     * @param ui An object to handle i/o operations through screen
     * @param storage An object to read from and write to file
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            addDeadlineTask(taskList, ui);
            storage.saveTasksToFile(taskList);
        } catch (DukeException | IOException e) {
            ui.displayError(e.getMessage());
        }
    }

    /**
     *
     * @param taskList List of tasks
     * @param ui An object to handle i/o operations through screen
     * @throws DukeException Any exception found
     */
    public void addDeadlineTask(TaskList taskList, Ui ui) throws DukeException {
        Task newDeadlineTask = new Deadline(TaskType.DEADLINE, super.getTaskDescription(), taskDateTime);
        taskList.add(newDeadlineTask);

        String response = ui.taskAddedMessage(newDeadlineTask)
                + System.lineSeparator() + ui.getNumOfTasksInList(taskList);
        ui.displayResponse(response);
    }
}
