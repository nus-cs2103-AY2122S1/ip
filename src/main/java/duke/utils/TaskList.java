package duke.utils;

import duke.exceptions.InvalidTaskIdException;
import duke.tasks.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * The TaskList class encapsulates a list of user's tasks.
 */
public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Adds a Task object to the current TaskList.
     *
     * @param t The Task object to be added.
     */
    public void add(Task t) {
        this.taskList.add(t);
    }

    /**
     * Deletes a Task object from the TaskList.
     *
     * @param index Index of the Task object to be deleted.
     * @throws InvalidTaskIdException If the index provided does not correspond to a Task in the TaskList.
     */
    public void delete(int index) throws InvalidTaskIdException {
        if (index >= 0 && index < this.taskList.size()) {
            this.taskList.remove(index);
        } else {
            throw new InvalidTaskIdException();
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
        if (index >= 0 && index < this.taskList.size()) {
            return this.taskList.get(index);
        } else {
            throw new InvalidTaskIdException();
        }
    }

    /**
     * Marks a Task object in the TaskList as completed.
     *
     * @param index The index of the Task object.
     * @throws InvalidTaskIdException If the index provided does not correspond to a Task in the TaskList.
     */
    public void markAsCompleted(int index) throws InvalidTaskIdException {
        if (index >= 0 && index < this.taskList.size()) {
            this.taskList.get(index).markAsCompleted();
        } else {
            throw new InvalidTaskIdException();
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

    public void search(String query) {
        Ui ui = new Ui();
        TaskList result = new TaskList();
        for (Task task : taskList) {
            if (task.getTaskName().toUpperCase().contains(query.toUpperCase())) {
                result.add(task);
            }
        }
        ui.printTasks(result);
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
