package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import duke.tasks.Task;


/**
 * Class containing the task list.
 */
public class TaskList {

    private ArrayList<Task> list;
    private Storage storage;

    /**
     * Constructor of TaskList.
     *
     *
     * @throws FileNotFoundException when the file cannot be found.
     */
    public TaskList() throws IOException {
        storage = new Storage("data/tasks.txt", "data");
        list = storage.load();
    }

    /**
     * Returns the task in the task list at the index passed in.
     *
     * @param index index of the desired task.
     * @return the task at that index.
     */
    public Task get(int index) {
        return list.get(index);
    }

    /**
     * Returns the size of the task list.
     *
     * @return size of the task list.
     */
    public int size() {
        return list.size();
    }

    /**
     * Removes the task at the index passed in.
     *
     * @param index the index of the task to be removed.
     */
    public void remove(int index) throws IOException {
        list.remove(index);
        storage.delete(index + 1);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task the task to be added.
     */
    public void add(Task task) throws IOException {
        list.add(task);
        storage.save(task);
    }

    public void markAsDone(int index) throws IOException {
        list.get(index).markAsDone();
        storage.markAsDone(index + 1);
    }

    /**
     * Returns a list of task whose description includes the keyword.
     * @param keyword
     * @return
     */
    public ArrayList<Task> filter(String keyword) {
        ArrayList<Task> temp = new ArrayList<>();
        for (Task task : list) {
            if (task.getDescription().contains(keyword)) {
                temp.add(task);
            }
        }
        return temp;
    }
}
