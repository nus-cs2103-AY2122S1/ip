package duke.commands;

import duke.exceptions.DukeException;
import duke.main.Ui;
import duke.main.Storage;
import duke.tasks.Task;
import duke.main.TaskList;

public class DeleteCommand extends Command {
    private int index;

    /**
     * {@inheritDoc}
     */
    public DeleteCommand(String userInput) throws DukeException {
        super(userInput);
        this.index = this.getIndex();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        //TaskList
        Task deletedTask = taskList.deleteTask(this.index);

        //Storage
        storage.save(taskList);

        //Ui
        ui.showDeleteTask(deletedTask);
        ui.showNumTask(taskList.getNumTask());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }

    private int getIndex() {
        return Integer.parseInt(this.userInputList.get(1)) - 1;
    }
}

