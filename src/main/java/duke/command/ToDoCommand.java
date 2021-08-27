package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.Ui;
import duke.task.ToDo;

import java.io.IOException;

/**
 * ToDoCommand is a duke.command which adds a ToDo duke.task to the duke.task list.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S1
 */
public class ToDoCommand extends Command {

    /**
     * Constructor.
     *
     * @param description it should contain the ToDo description and the date
     */
    public ToDoCommand(String description) {
        super(description);
    }

    /**
     * Adds the ToDo into the duke.task list.
     *
     * @param tasks   the duke.task list
     * @param ui      the ui
     * @param storage the storage for the saved duke.task list
     * @throws DukeException if the saved data is deleted midway /
     *                       the data file is missing by other factors
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (description.replace(" ", "").equals("")) {
                throw new DukeException("The description for the Todo duke.task cannot be empty.");
            }
            String desc = description.trim();
            tasks.addTask(new ToDo(false, desc));
            storage.add("T", desc, "");
        } catch (IOException e) {
            throw new DukeException("There is an error in adding the Todo duke.task to your saved data.");
        }
    }
}
