package duke.commands;

import java.io.IOException;

import duke.Storage;
import duke.Tasklist;
import duke.Ui;
import duke.tasks.Task;

/**
 * Represents an Add Command which includes information about the Task
 * as well as how it should be executed
 */

public class AddCommand extends Command {

    private Task toAdd;

    /**
     * Constructor of an Add Command
     *
     * @param toAdd Task to be Added
     */

    public AddCommand(Task toAdd) {
        this.toAdd = toAdd;
    }

    /**
     * Executes the Add Command by adding it to tasklist, updating the user
     * and updating the save file
     *
     * @param tasklist Tasklist containing list of Tasks
     * @param ui Ui that handles what is shown to the user
     * @param storage Storage that handles writing and reading save file
     * @throws IOException If scanner is not working as intended
     */

    @Override
    public String execute(Tasklist tasklist, Ui ui, Storage storage) throws IOException {
        try {
            tasklist.addTask(toAdd);
            String result = ui.showAddMessage(toAdd, tasklist);
            storage.writeToFile(tasklist);
            return result;
        } catch (IOException e) {
            throw new IOException();
        }
    }

}
