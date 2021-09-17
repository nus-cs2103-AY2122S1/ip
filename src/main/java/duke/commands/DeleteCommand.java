package duke.commands;

import java.util.Arrays;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command {
    private String commands;

    public DeleteCommand(String commands) {
        this.commands = commands;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        String response = "Noted. I've removed these tasks:\n";
        try {
            int[] indexesOfTasks = getIndexes();
            for (int i = indexesOfTasks.length - 1; i >= 0; i--) {
                response += deleteTask(indexesOfTasks[i], tasks, storage);
            }
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
        response += "Now you have " + tasks.getLength() + " tasks in the list.";
        return response;
    }

    /**
     * Parses command inputted by user to return array of indexes to be
     * deleted in descending order.
     * @return Array of indexes in descending order.
     * @throws DukeException when there is invalid index.
     */
    private int[] getIndexes() throws DukeException {
        String[] indexes = commands.split(" ");
        int[] indexesOfTasks = new int[indexes.length];
        for (int i = 0; i < indexes.length; i++) {
            if (indexes[i].matches("\\d+")) {
                indexesOfTasks[i] = Integer.parseInt(indexes[i]);
            } else {
                throw new DukeException("☹ OOPS!!! Input a valid index");
            }
        }
        Arrays.sort(indexesOfTasks);
        return indexesOfTasks;
    }

    /**
     * Deletes task from TaskList and storage and returns string
     * format of deleted task.
     * @param location index to be deleted.
     * @param tasks TaskList of tasks to be updated.
     * @param storage Storage object that updates the file in storage.
     * @return String format of task deleted
     * @throws DukeException when there is an invalid index.
     */
    private String deleteTask(int location, TaskList tasks, Storage storage) throws DukeException {
        String response;
        int index = location - 1;
        if (index >= 0 && index < tasks.getLength()) {
            Task task = tasks.deleteTask(index);
            storage.updateFile(tasks);
            response = task + "\n";
        } else {
            throw new DukeException("☹ OOPS!!! Input a valid index");
        }
        return response;
    }
}
