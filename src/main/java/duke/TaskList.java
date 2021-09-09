package duke;

import duke.exception.DukeException;
import duke.exception.OutOfBoundsException;

import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public int getSize() {
        return taskList.size();
    }

    public void addToList(Task task) {
        taskList.add(task);
    }

    /**
     * Removes the element in the list represented by the index.
     *
     * @param index The index of the element to be removed.
     */
    public Task deleteFromList(int index) throws DukeException {
        if (index <= 0 || index > taskList.size()) {
            throw new OutOfBoundsException(index, this);
        } else {
            // no problems with the input, a task is added
            Task toDelete = taskList.get(index - 1);
            taskList.remove(index - 1);
            return toDelete;
        }
    }

    /**
     * Marks the task at the given index as done.
     *
     * @param index The index of the task to be marked as done.
     */
    public Task markAsDone(int index) throws DukeException {
        if (index <= 0 || index > taskList.size()) {
            throw new OutOfBoundsException(index, this);
        } else {
            // no problems with the input, a task is added
            Task toMark = taskList.get(index - 1);
            taskList.get(index - 1).markAsDone();
            return toMark;
        }
    }

    /**
     * Finds the tasks that match a given String.
     *
     * @param str The given String.
     * @return An ArrayList of the tasks that contain the string.
     */
    public ArrayList<Task> findMatches(String str) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task task : this.taskList) {
            if (task.toString().contains(str)) {
                result.add(task);
            }
        }
        return result;
    }

    public void update(int index, Task task) throws DukeException {
        if (index >= taskList.size() || index < 0) {
            throw new OutOfBoundsException(index, this);
        }
        taskList.set(index, task);
    }
}
