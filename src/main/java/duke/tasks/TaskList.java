package duke.tasks;

import java.util.ArrayList;

import duke.DukeException;

/**
 * List to contain tasks.
 */
public class TaskList extends ArrayList<Task> {

    /**
     * Constructs a list of tasks.
     *
     * @param taskList List of tasks.
     * @throws DukeException When list provided is empty.
     */
    public TaskList(ArrayList<Task> taskList) throws DukeException {
        if (taskList.size() == 0) {
            throw DukeException.emptyList();
        }

        for (Task task : taskList) {
            this.add(task);
        }
    }

    public TaskList() {
    }
}
