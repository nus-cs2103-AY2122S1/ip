package duke;

import java.util.List;

/**
 * Represents a list that store tasks
 */
public class TaskList {
    private final List<Task> list;

    /**
     * Class constructor.
     */
    public TaskList() {
        this.list = Storage.readData();
    }

    public List<Task> getList() {
        return this.list;
    }

    public void addTask(Task task) {
        list.add(task);
    }

    public Task deleteTask(int index) {
        return this.list.remove(index);
    }

    /**
     * Marks a task as complete
     * @param index Index of the task in the TaskList
     * @return Completed Task
     */
    public Task setComplete(int index) {
        this.list.get(index).setCompleted();
        return this.list.get(index);
    }

    /**
     * Saves the TaskList to storage
     */
    public void saveList() {
        Storage.writeData(this.list);
    }

    public int getListSize() {
        return this.list.size();
    }

    /**
     * Returns a String indicating the number of tasks in the TaskList
     * @return String representation of the number of tasks
     */
    public String getListSizeString() {
        return String.format("\n Now you have %d tasks in the list.\n",
                list.size());
    }

    public void clear() {
        this.list.clear();
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < this.list.size(); i++) {
            Task task = this.list.get(i);
            str += String.format(" %d.%s", i + 1, task.toString());
            if (i < this.list.size() - 1) {
                str += "\n";
            }
        }
        return str;
    }

}
