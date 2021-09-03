package duke.commands;

import duke.exceptions.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.tasks.Task;

/**
 * Represents command to delete task
 */
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
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        //TaskList
        Task deletedTask = taskList.deleteTask(this.index);

        //Storage
        storage.save(taskList);

        //Ui
        String output = "";
        output += ui.showDeleteTask(deletedTask) + "\n";
        output += ui.showNumTask(taskList.getNumTask());
        return output;
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

