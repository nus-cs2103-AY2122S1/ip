package duke.commands;

import java.io.IOException;

import duke.Storage;
import duke.Tasklist;
import duke.Ui;
import duke.exceptions.NoSuchTaskException;
import duke.tasks.Task;



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
    public String execute(Tasklist tasklist, Ui ui, Storage storage) throws NoSuchTaskException {
        try {
            Task temp = tasklist.getTask(index - 1);
            tasklist.deleteTask(index);
            String result = ui.showDeleteMessage(temp, tasklist);
            storage.writeToFile(tasklist);
            return result;
        } catch (NoSuchTaskException | IOException e) {
            throw new NoSuchTaskException("Task index is out of bounds");
        }

    }
}
