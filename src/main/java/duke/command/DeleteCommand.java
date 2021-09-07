package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command {

    public DeleteCommand(String userInput) {
        super(userInput);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        return deleteTask(this.getUserInput(), taskList, ui, storage);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private String deleteTask(String userInput, TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String[] inputArray = userInput.split(" ");
        assert inputArray.length >= 2 : "Delete command should be in the form delete (index)";
        Task removedTask = taskList.deleteTask(Integer.parseInt(inputArray[1]) - 1);
        storage.saveData(taskList);
        String deleteMessage = String
                .format("Noted. I've removed this duke.task: \n%s\nNow you have %d tasks in the list.",
                        removedTask, taskList.size());

        return ui.getDukeMessage(deleteMessage);
    }
}
