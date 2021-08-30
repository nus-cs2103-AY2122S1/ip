package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.io.IOException;

public class DeleteCommand extends Command {
    private int taskNum;

    public DeleteCommand(String type, int taskNum) {
        super(type);
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            deleteTask(taskList, ui);
            storage.saveTasksToFile(taskList);
        } catch (DukeException | IOException e) {
            ui.displayError(e.getMessage());
        }
    }

    public void deleteTask(TaskList taskList, Ui ui) throws DukeException {
        if (taskNum > 0 && taskNum <= taskList.size()) {
            Task taskDeleted = taskList.remove(taskNum - 1);

            String response = ui.taskDeletedMessage(taskDeleted)
                    + System.lineSeparator() + ui.getNumOfTasksInList(taskList);
            ui.displayResponse(response);
        } else {
            throw new DukeException("Task not found. Please try again!");
        }
    }
}
