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
     * @throws FileNotFoundException when the file cannot be found.
     */
    public TaskList() throws IOException {
        storage = new Storage("data/tasks.txt", "data");
        list = storage.load();
    }

    /**
     * Returns the task in the task list at the index passed in.
     *
     * @param index Index of the desired task.
     * @return The task at that index.
     */
    public Task get(int index) {
        return list.get(index - 1);
    }

    /**
     * Returns the size of the task list.
     *
     * @return Size of the task list.
     */
    public int size() {
        return list.size();
    }

    /**
     * Removes the task at the index passed in.
     *
     * @param index The index of the task to be removed.
     */
    public void remove(int index) throws IOException {
        list.remove(index - 1);
        storage.delete(index);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) throws IOException {
        list.add(task);
        storage.save(task);
    }

    /**
     * Marks a task as done and reflects it in the storage.
     *
     * @param index Index of task to be marked as done.
     * @throws IOException
     */
    public void markAsDone(int index) throws IOException {
        list.get(index - 1).markAsDone();
        storage.markAsDone(index);
    }

    /**
     * Returns a list of task whose description includes the keyword.
     *
     * @param keyword The keyword to be searched
     * @return ArrayList containing tasks whose description contains the keyword.
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
