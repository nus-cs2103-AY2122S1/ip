package duke.utils;

import duke.exceptions.InvalidTaskIdException;
import duke.exceptions.DuplicateTaskException;
import duke.tasks.Task;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * The TaskList class encapsulates a list of user's tasks.
 */
public class TaskList {
    private List<Task> taskList = new ArrayList<>();

    String home = System.getProperty("user.home");
    Path filePath = Paths.get(home, "tasks.txt");
    private Storage storage = new Storage(filePath);

    /**
     * Loads the saved tasks from the user's disk. If not available, will
     * return an empty TaskList.
     *
     * @return The TaskList object containing the saved tasks (if applicable).
     * @throws IOException If there is an error loading from the specified file.
     */
    public void loadFromDisk() throws IOException {
        System.out.println(this.storage);
        this.taskList = this.storage.loadData().taskList;
    }

    /**
     * Adds a Task object to the current TaskList.
     *
     * @param t The Task object to be added.
     */
    public void add(Task t) throws DuplicateTaskException {
        assert t != null : "task to add cannot be null";
        for (Task task : this.taskList) {
            if (t.equals(task)) {
                throw new DuplicateTaskException();
            }
        }
        this.taskList.add(t);
        try {
            this.storage.saveData(this);
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Deletes a Task object from the TaskList.
     *
     * @param index Index of the Task object to be deleted.
     * @throws InvalidTaskIdException If the index provided does not correspond to a Task in the TaskList.
     */
    public void delete(int index) throws InvalidTaskIdException {
        if (index < 0 || index >= this.taskList.size()) {
            throw new InvalidTaskIdException();
        }
        this.taskList.remove(index);
        try {
            this.storage.saveData(this);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns a Task object by its index in the TaskList.
     *
     * @param index The index of the Task object.
     * @return Task object corresponding to the index provided.
     * @throws InvalidTaskIdException If the index provided does not correspond to a Task in the TaskList.
     */
    public Task get(int index) throws InvalidTaskIdException {
        if (index < 0 || index >= this.taskList.size()) {
            throw new InvalidTaskIdException();
        }
        return this.taskList.get(index);
    }

    /**
     * Marks a Task object in the TaskList as completed.
     *
     * @param index The index of the Task object.
     * @throws InvalidTaskIdException If the index provided does not correspond to a Task in the TaskList.
     */
    public void markAsCompleted(int index) throws InvalidTaskIdException {
        if (index < 0 || index >= this.taskList.size()) {
            throw new InvalidTaskIdException();
        }
        this.taskList.get(index).markAsCompleted();
        try {
            this.storage.saveData(this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns the number of Task objects in the TaskList.
     *
     * @return The size of the Task List.
     */
    public int getSize() {
        return this.taskList.size();
    }

    public TaskList search(String query) {
        TaskList result = new TaskList();
        for (Task task : taskList) {
            if (task.getTaskName().toUpperCase().contains(query.toUpperCase())) {
                try {
                    result.add(task);
                } catch (DuplicateTaskException e) {
                    assert false : "tasks in task list should not be duplicated";
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        List<String> stringsArray = new ArrayList<>();
        for (Task t : this.taskList) {
            stringsArray.add(t.toString());
        }
        String tasks = String.join("\n", stringsArray);
        return "List:\n" + "---------------\n" + tasks;
    }
}
