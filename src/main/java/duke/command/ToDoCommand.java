package duke.command;

import java.io.IOException;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.ToDo;
import duke.tasklist.TaskList;

/**
 * ToDoCommand is a command which adds a ToDo task to the task list.
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
     * Adds the ToDo into the task list.
     *
     * @param tasks   the task list
     * @param storage the storage for the saved task list
     * @throws DukeException if the saved data is deleted midway /
     *                       the data file is missing by other factors
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            if (description.replace(" ", "").equals("")) {
                throw new DukeException("The description for the Todo task cannot be empty.");
            }
            String desc = description.trim();
            storage.add("T", desc, "");
            return tasks.addTask(new ToDo(false, desc));
        } catch (IOException e) {
            throw new DukeException("There is an error in adding the Todo task to your saved data.");
        }
    }
}
