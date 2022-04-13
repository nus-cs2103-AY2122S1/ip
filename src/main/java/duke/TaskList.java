package duke;

import java.util.ArrayList;

/**
 * Represents the TaskList.
 */
public class TaskList {

    /** The array list representing the tasks */
    private ArrayList<Task> arr;

    public TaskList(ArrayList<Task> arr) {
        this.arr = arr;
    }

    /**
     * Adds task to the task arraylist.
     *
     * @param task Task to be added.
     */
    public void addTaskToList(Task task) {
        arr.add(task);
    }

    /**
     * Returns a specific indexed task from the task arraylist.
     *
     * @param i Index of the task to be returned.
     * @return Task with index i.
     */
    public Task getTaskFromList(int i) {
        //insert java assertion to check if index is within bounds.
        assert i >= 0 && i < arr.size();
        return arr.get(i);
    }

    /**
     * Returns number of tasks in the list.
     *
     * @return number of tasks in the task arraylist.
     */
    public int numberOfTasks() {
        return arr.size();
    }

    /**
     * Deletes task from the list.
     *
     * @param i Index of the task to be deleted.
     */
    public void deleteTask(int i) {
        //insert java assertion to check if index is within bounds.
        assert i >= 0 && i < arr.size();
        arr.remove(i);
    }

    /**
     * Converts whole TaskList to String.
     *
     * @return string version of the TaskList.
     */
    public String stringifyWholeList() {
        String res = "";
        for (int j = 0; j < arr.size(); j++) {
            if (!arr.get(j).getPreExisting()) {
                res = res + arr.get(j).toString() + "\n";
            } else {
                res = res + arr.get(j).getDescription() + "\n";
            }
        }
        return res;
    }

    /**
     * Returns the whole TaskList.
     *
     * @return the whole TaskList.
     */
    public ArrayList<Task> getTaskList() {
        return arr;
    }

    /**
     * Checks for duplicate tasks in the TaskList.
     *
     * @param taskContent Content of the new input task.
     * @param category Category of the new input task.
     * @return Boolean value of whether a duplicate exists.
     */
    public boolean hasDuplicate(String taskContent, String category) {
        for (int j = 0; j < arr.size(); j++) {
            if (arr.get(j).getTaskContent().contains(taskContent) && arr.get(j).getCategory().equals(category)) {
                return true;
            }
        }
        return false;
    }
}
