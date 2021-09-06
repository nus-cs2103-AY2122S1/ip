package duke.commands;

import duke.Tasklist;
import duke.exceptions.NoSuchTaskException;
import duke.tasks.Task;
import duke.Ui;
import duke.Storage;

import java.io.IOException;

/**
 * Represents a Delete Command which includes information about the Task
 * as well as how it should be executed
 */

public class DeleteCommand extends Command {

    private int index;

    /**
     * Constructor of a Delete Command
     *
     * @param index Index of task to be Deleted
     */

    public DeleteCommand(int index) {
        this.index = index;
    }


    /**
     * Executes the Delete Command by deleting it from the tasklist, updating the user
     * and updating the save file
     *
     * @param tasklist Tasklist containing list of Tasks
     * @param ui Ui that handles what is shown to the user
     * @param storage Storage that handles writing and reading save file
     * @throws NoSuchTaskException If task with input index cannot be retrieved
     */

    @Override
    public void execute(Tasklist tasklist, Ui ui, Storage storage) throws NoSuchTaskException {
        try {
            Task temp = tasklist.getTask(index);
            tasklist.deleteTask(index);
            ui.showDeleteMessage(temp, tasklist);
            storage.writeToFile(tasklist);
        } catch (NoSuchTaskException | IOException e) {
            throw new NoSuchTaskException("Task index is out of bounds");
        }

    }
}