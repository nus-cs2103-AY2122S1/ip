package duke.tasks;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Searches the task list for tasks with matching keyword within description
     *
     * @param keyword keyword to be searched
     * @return the list of tasks with the keyword
     */
    public String find(String keyword) {
        StringBuilder taskString = new StringBuilder();
        int len = tasks.size();
        int count = 1;

        for (int i = 0; i < len; i++) {
            Task task = tasks.get(i);

            if (task.contains(keyword)) {
                taskString.append(count + ". " + task.toString() + "\n");
                count++;
            }
        }

        return taskString.toString().trim();
    }

    /**
     * Sorts the Tasks according their due dates
     *
     */
    public void sort() {
        this.tasks.sort(null);
    }

    /**
     * Returns the list of tasks as a String
     *
     * @return formatted list of task
     */
    public String getFormattedData() {
        StringBuilder data = new StringBuilder();

        int len = tasks.size();

        for (int i = 0; i < len - 1; i++) {
            data.append(tasks.get(i).getFormattedData() + "\n");
        }

        data.append(tasks.get(len - 1).getFormattedData());

        return data.toString();
    }

    /**
     * Returns the number of tasks as a String response
     *
     * @return formatted response
     */
    public String getTaskCountString() {
        return String.format("You have %d tasks", this.tasks.size());
    }

    /**
     * Returns task at index
     *
     * @param index index of task
     * @return task at index
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Adds task to list of tasks
     *
     * @param task task to be added
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Delete task at index from task list
     *
     * @param index index of task
     * @return task that was deleted
     */
    public Task delete(int index) {
        return this.tasks.remove(index);
    }

    /**
     * Return the size of the task list
     *
     * @return size of task list
     */
    public int getSize() {
        return this.tasks.size();
    }

    @Override
    public String toString() {
        StringBuilder taskString = new StringBuilder();
        int len = tasks.size();

        for (int i = 0; i < len - 1; i++) {
            taskString.append(i + 1 + ". " + tasks.get(i).toString() + "\n");
        }

        taskString.append(len + ". " + tasks.get(len - 1).toString());

        return taskString.toString();
    }

}
