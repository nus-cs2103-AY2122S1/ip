package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class UpdateCommand extends Command {


    public UpdateCommand(String userInput) {
        super(userInput);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String[] inputArray = this.getUserInput().split(" ", 3);
        Task taskToUpdate = taskList.deleteTask(Integer.parseInt(inputArray[1]) - 1);
        String originalTaskInfo = taskToUpdate.toString();
        String updatedData = String.join(" ", inputArray[2]);
        taskToUpdate.update(updatedData);
        taskList.addTask(taskToUpdate);
        storage.saveData(taskList);
        String updateMessage = String.format("Nice! \n%s has been updated to:\n%s", originalTaskInfo, taskToUpdate);
        return ui.getDukeMessage(updateMessage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
