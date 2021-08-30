package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.io.IOException;

public class DoneCommand extends Command {
    private int taskNum;

    public DoneCommand(String type, int taskNum) {
        super(type);
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            markDone(taskList, ui);
            storage.saveTasksToFile(taskList);
        } catch (DukeException | IOException e) {
            ui.displayError(e.getMessage());
        }
    }

    public void markDone(TaskList taskList, Ui ui) throws DukeException {
        if (taskNum > 0 && taskNum <= taskList.size()) {
            Task taskDone = taskList.get(taskNum - 1);
            taskDone.setDone();

            String response = ui.taskDoneMessage(taskDone);
            ui.displayResponse(response);
        } else {
            throw new DukeException("Task not found. Please try again!");
        }
    }
}
