package commands;

import exceptions.DukeException;
import main.Ui;
import main.Storage;
import tasks.Task;
import main.TaskList;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(String userInput) {
        super(userInput);
        this.index = this.getIndex();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        //TaskList
        Task deletedTask = taskList.deleteTask(this.index);

        //Storage
        storage.save(taskList);

        //Ui
        ui.showDeleteTask(deletedTask);
        ui.showNumTask(taskList.getNumTask());
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private int getIndex() {
        return Integer.parseInt(this.userInputList.get(1)) - 1;
    }
}

