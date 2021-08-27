package duke.main;
import duke.tasks.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a class to hold all tasks
 */
public class TaskList {
    protected ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(List<StorageElement> storageList) {
        taskList = new ArrayList<>();
        for (StorageElement storageElement : storageList) {
            this.addTask(Task.of(storageElement));
        }
    }

    /**
     * Adds task to list of tasks
     * @param task
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Marks task as done
     * @param index Index of tasks starting from 0
     * @return Done task
     */
    public Task markAsDone(int index) {
        this.taskList.get(index).markAsDone();
        return this.taskList.get(index);
    }

    /**
     * Gets number of tasks in list
     * @return Number of task in list
     */
    public int getNumTask() {
        return this.taskList.size();
    }

    /**
     * Deletes task at a specific index
     * @param index Index of task to be deleted starting from 0
     * @return Deleted task
     */
    public Task deleteTask(int index) {
        return this.taskList.remove(index);
    }

    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    public String toString() {
        if (this.taskList.size() == 0) {
            return "Empty Task List";
        }

        String description = "";
        for (int i = 0; i < this.taskList.size(); i += 1) {
            description += (i + 1) + "." + this.taskList.get(i) + "\n";
        }
        //To remove last new line
        description = description.substring(0, description.length() - 1);
        return description;
    }

    public List<StorageElement> getStorageList() {
        List<StorageElement> storageList = new ArrayList<>();
        for (Task task : this.taskList) {
            storageList.add(task.getStorageElement());
        }
        return storageList;
    }
}