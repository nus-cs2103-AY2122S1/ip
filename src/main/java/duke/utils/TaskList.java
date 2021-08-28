package duke.utils;

import duke.exceptions.InvalidTaskIdException;
import duke.tasks.Task;

import java.util.ArrayList;
import java.util.List;

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

<<<<<<< HEAD
    /**
     * Deletes a Task object from the TaskList.
     *
     * @param index Index of the Task object to be deleted.
     * @throws InvalidTaskIDException If the index provided does not correspond to a Task in the TaskList.
     */
    public void delete(int index) throws InvalidTaskIDException {
=======
    public void delete(int index) throws InvalidTaskIdException {
>>>>>>> branch-A-CodingStandard
        if (index >= 0 && index < this.taskList.size()) {
            this.taskList.remove(index);
        } else {
            throw new InvalidTaskIdException();
        }
    }

<<<<<<< HEAD
    /**
     * Returns a Task object by its index in the TaskList.
     *
     * @param index The index of the Task object.
     * @return Task object corresponding to the index provided.
     * @throws InvalidTaskIDException If the index provided does not correspond to a Task in the TaskList.
     */
    public Task get(int index) throws InvalidTaskIDException {
=======
    public Task get(int index) throws InvalidTaskIdException {
>>>>>>> branch-A-CodingStandard
        if (index >= 0 && index < this.taskList.size()) {
            return this.taskList.get(index);
        } else {
            throw new InvalidTaskIdException();
        }
    }

<<<<<<< HEAD
    /**
     * Marks a Task object in the TaskList as completed.
     *
     * @param index The index of the Task object.
     * @throws InvalidTaskIDException If the index provided does not correspond to a Task in the TaskList.
     */
    public void markAsCompleted(int index) throws InvalidTaskIDException {
=======
    public void markAsCompleted(int index) throws InvalidTaskIdException {
>>>>>>> branch-A-CodingStandard
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

    @Override
    public String toString() {
        List<String> stringsArray = new ArrayList<>();
        for (Task t : this.taskList) {
            stringsArray.add(t.toString());
        }
        String tasks = String.join("\n", stringsArray);
        return "Current List:\n" + "---------------\n" + tasks;
    }
}
