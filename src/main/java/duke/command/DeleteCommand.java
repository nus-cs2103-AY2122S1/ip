package duke.command;

import duke.Ui;
import duke.Storage;
import duke.DukeException;

import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command {

    public DeleteCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        deleteTask(this.getUserInput(), taskList, ui, storage);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private void deleteTask(String userInput, TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String[] inputArray = userInput.split(" ");
        Task removedTask = taskList.deleteTask(Integer.parseInt(inputArray[1]) - 1);
        storage.saveData(taskList);
        String deleteMessage = String.format("Noted. I've removed this duke.task: \n%s\nNow you have %d tasks in the list.",
                                    removedTask, taskList.size());
        ui.printMessage(deleteMessage);
    }
}
