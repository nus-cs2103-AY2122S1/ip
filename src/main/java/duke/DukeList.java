package duke;

import java.util.List;
import java.util.stream.IntStream;

import duke.database.Database;
import duke.database.Sqlite;
import duke.exception.InvalidArgumentException;
import duke.task.Task;

/**
 * Encapsulates a task list in Duke, able to provide relevant responses on its
 * manipulation.
 */
public class DukeList {
    private final Database database;

    /**
     * Creates a list interface for Duke bot.
     */
    public DukeList() {
        this.database = new Sqlite();
        this.database.load();
    }

    /**
     * Adds a task to the list and return a response on the added task.
     *
     * @param task to be added to the list
     */
    public void addTask(Task task) {
        this.database.addTask(task);
    }

    /**
     * Marks the given task (by index) as completed, and returns a response on
     * whether the operation is successful.
     *
     * @param i index of task to be marked as completed
     * @return response on whether it is succcessful
     */
    public Task markCompleted(int i) {
        Task task = this.database.markCompleted(i);
        if (task == null) {
            throw new InvalidArgumentException("Invalid index!\n" + currentSizeMessage());
        }
        return task;
    }

    /**
     * Removes the task in the given index from the list, and returns a response on
     * whether the operation is successful.
     *
     * @param i index of task to be removed
     * @return response on whether it is succcessful
     */
    public Task deleteTask(int i) {
        Task task = this.database.removeTask(i);
        if (task == null) {
            throw new InvalidArgumentException("Invalid index!\n" + currentSizeMessage());
        }
        return task;
    }

    /**
     * Finds tasks by searching for a keyword/phrase in the name.
     *
     * @param pattern keyword/phrase to search
     * @return list of tasks
     */
    public List<Task> findTasksByName(String pattern) {
        return this.database.findTasksByName(pattern);
    }

    // TODO Change to get size instead
    /**
     * Returns a message regarding the current size of the list.
     *
     * @return message on the current list size
     */
    public String currentSizeMessage() {
        int size = this.database.getTasksList().size();
        String unit = size == 1 ? "task" : "tasks";
        return "You now have " + size + " " + unit + " in the list!";
    }

    @Override
    public String toString() {
        List<Task> list = this.database.getTasksList();
        String message = IntStream.range(0, list.size()).mapToObj(i -> (i + 1) + ". " + list.get(i))
                .reduce((str1, str2) -> str1 + "\n" + str2).orElse("You have not added any tasks!");
        return message;
    }

}
