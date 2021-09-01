package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class DoneTaskCommand extends Command {

    public DoneTaskCommand (String userInput) {
        super(userInput);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        return doneTask(this.getUserInput(), taskList, ui, storage);
    }

    @Override
    public boolean isExit() {
        return false;
    }


    private String doneTask(String userInput, TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String[] inputArray = userInput.split(" ");
        Task completedTask = taskList.get(Integer.parseInt(inputArray[1]) - 1);
        completedTask.markAsDone();
        storage.saveData(taskList);
        String doneMessage = String.format("Nice! I've marked this duke.task as done:\n%s", completedTask);
        return ui.printMessage(doneMessage);
    }

}
