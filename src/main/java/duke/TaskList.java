package duke;

import java.util.ArrayList;

/**
 * Class that holds the list of tasks
 */
public class TaskList {
    public ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Add a task to the list.
     *
     * @param t Task to be added.
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Delete a task from the list.
     *
     * @param index Index of the task to be deleted.
     */
    public void deleteTask(int index) throws DukeException {
        if (index > tasks.size() - 1) {
            throw new DukeException("Index out of range");
        }
        tasks.remove(index);
    }

    /**
     * Get task.
     *
     * @param i Index of the task to be retrieve.
     */
    public Task get(Integer i) throws DukeException {
        if (i > tasks.size() - 1) {
            throw new DukeException("Index out of range");
        }
        return tasks.get(i);
    }

    /**
     * Marks a task complete.
     * @param i Index of the task.
     */
    public void completeTask(Integer i) throws DukeException {
        if (i > tasks.size() - 1) {
            throw new DukeException("Index out of range");
        }
        tasks.get(i).setStatus(true);
    }

    /**
     * Returns number of tasks in the list.
     * @return Number of task in the list.
     */
    public int count() {
        return tasks.size();
    }

    /**
     * Returns the index of tasks that contains the query in teh description.
     *
     * @param query Query to be searched.
     * @return Array of indexes.
     */
    public ArrayList<Integer> find(String query) {
        ArrayList<Integer> output = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(query)) {
                output.add(i);
            }
        }

        return output;
    }

    /**
     * Returns string representation of the TaskList.
     *
     * @return String representation of the TaskList.
     */
    @Override
    public String toString() {
        int index = 1;
        StringBuilder output = new StringBuilder();
        int len = tasks.size();
        for (int i = 0; i < len; i++) {
            Task t = tasks.get(i);
            output.append(index).append(". ").append(t.toString());

            if (i < len - 1) {
                output.append("\n");
            }
            index++;
        }
        return output.toString();
    }

    /**
     * Returns the TaskList save format.
     *
     * @return TaskList in save format.
     */
    public String saveString() {
        StringBuilder output = new StringBuilder();
        for (Task t : tasks) {
            output.append(t.save()).append("\n");
        }
        return output.toString();
    }

}
