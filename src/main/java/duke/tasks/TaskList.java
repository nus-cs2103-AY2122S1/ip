package duke.tasks;

import java.util.ArrayList;

import duke.exceptions.IndexNotInListException;


public class TaskList {
    private static final ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Empty constructor for when nothing is passed into the instantiation of a tasklist object.
     */
    public TaskList() {

    }

    /**
     * Creates a task list object with a list of tasks passed into the constructor
     *
     * @param list is a list of tasks loaded from the txt file
     */
    public TaskList(ArrayList<Task> list) {
        taskList.addAll(list);
    }

    /**
     * Inserts a task into the list of tasks
     *
     * @param task the task to be inserted.
     */
    public void insert(Task task) {
        taskList.add(task);
    }

    /**
     * Marks the referenced task as completed.
     *
     * @param index The index of the task in the list
     * @return the task that is completed
     * @throws IndexNotInListException when the index passed in is less than 0 or greater than size of list.
     */
    public Task complete(int index) throws IndexNotInListException {
        if (index < 0 || index >= taskList.size()) {
            throw new IndexNotInListException("Haiyo, you sure there is a task " + index + " anot...");
        }
        return taskList.get(index).completeTask();
    }

    /**
     * Deletes the reference task
     *
     * @param index index of the task to be deleted
     * @return the task that was deleted
     * @throws IndexNotInListException when the index passed in is less than 0 or greater than size of list.
     */
    public Task delete(int index) throws IndexNotInListException {
        if (index < 0 || index >= taskList.size()) {
            throw new IndexNotInListException("Haiyo, you sure there is a task " + index + " anot...");
        }
        return taskList.remove(index);
    }

    /**
     * Returns the complete list of tasks currently
     *
     * @return a list of tasks.
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> searches = new ArrayList<>();
        for (Task t: taskList) {
            if (t.getName().toLowerCase().contains(keyword.toLowerCase())) {
                searches.add(t);
            }
        }
        return searches;
    }

    /**
     * Deletes all the tasks that are currently on the list.
     */
    public void deleteAll() {
        taskList.clear();
    }

    /**
     * Returns the size of the current list.
     *
     * @return an int representing the size of the list.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Snooze the task by 1 week.
     * @param index the index of the task the user is referring to.
     * @return the task that has been in snoozed
     * @throws IndexNotInListException when the index passed in is less than 0 or greater than size of list.
     */
    public Task snooze(int index) throws IndexNotInListException {
        if (index < 0 || index >= taskList.size()) {
            throw new IndexNotInListException("Haiyo, you sure there is a task " + index + " anot...");
        }
        return taskList.get(index).snoozeTask();
    }
}
