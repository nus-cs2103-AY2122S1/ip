package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.ToDo;
import duke.tasklist.TaskList;
import duke.utils.DukeException;

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
            String[] info = DESCRIPTION.split("/p", 2);
            if (info.length < 2) {
                throw new DukeException("Your add todo command is incomplete.");
            }
            String desc = info[0].trim();
            String priority = info[1].trim();
            if (desc.equals("") || priority.equals("")) {
                throw new DukeException("Your add todo command is incomplete.");
            }
            int priorityInt = Integer.parseInt(priority);
            if (priorityInt < 1 || priorityInt > 3) {
                throw new DukeException("Duke only allows priority of 1, 2 and 3!");
            }
            storage.add("T", desc, "", priorityInt);
            return tasks.addTask(new ToDo(false, desc, priorityInt));
        } catch (IOException e) {
            throw new DukeException("There is an error in adding the Todo task to your saved data.");
        }
    }
}
