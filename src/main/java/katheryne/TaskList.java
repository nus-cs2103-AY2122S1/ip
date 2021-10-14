package katheryne;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import katheryne.task.Task;

/**
 * A container for tasks which contains Katheryne's tasks.
 */
public class TaskList {
    private final ArrayList<Task> lst = new ArrayList<>();

    public TaskList() {

    }

    /**
     * Adds a task to this list
     *
     * @param t
     * @throws KatheryneException if there are duplicates.
     */
    public void add(Task t) throws KatheryneException {
        boolean hasDuplicates = checkForDuplicates(t);
        if (hasDuplicates) {
            throw new KatheryneException(Message.ERROR_DUPLICATE_TASK);
        }
        lst.add(t);
    }

    void addAll(List<Task> tasks) throws KatheryneException {
        for (Task t : tasks) {
            add(t);
        }
    }
    
    // abstracted method to check if duplicates in the task list exist
    private boolean checkForDuplicates(Task t) {
        return !lst.stream()
                .filter(task -> task.equals(t))
                .collect(Collectors.toList())
                .isEmpty();
    }

    /**
     * Returns true if you are able to mark the task as done, and false if index is out of bounds
     *
     * @param index to mark as done
     * @return
     */
    public boolean doTask(int index) {
        if (index >= getSize() || index < 0) {
            return false;
        }

        lst.get(index).markAsDone();
        return true;
    }

    /**
     * Removes task from your list
     *
     * @param index of the task to delete.
     * @return the task which was deleted.
     */
    public boolean deleteTask(int index) {
        if (index >= getSize() || index < 0) {
            return false;
        }

        lst.remove(index);
        return true;
    }

    /**
     * Finds all the tasks which contain a certain keyword within the description.
     *
     * @param keyword
     * @return a TaskList of the tasks found.
     */
    public TaskList tasksContaining(String keyword) throws KatheryneException {
        TaskList taskWithKeyword = new TaskList();
        for (int i = 1; i <= this.getSize(); i++) {
            Task t = this.getTask(i - 1);
            if (t.find(keyword)) {
                taskWithKeyword.add(t);
            }
        }
        return taskWithKeyword;
    }

    /**
     * Gets the task at the index given in the task list. Note that this method may
     * throw errors if index is out of bounds.
     *
     * @param index
     * @return
     */
    public Task getTask(int index) {
        return lst.get(index);
    }

    public boolean isEmpty() {
        return lst.isEmpty();
    }

    /**
     * Gets the size of the taskList.
     *
     * @return
     */
    public int getSize() {
        return lst.size();
    }

    // for Jackson to deserialize the task list
    protected ArrayList<Task> getList() {
        return lst;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TaskList) {
            TaskList taskList = (TaskList) obj;
            return this.lst.equals(taskList.getList());
        }
        return false;
    }
}
