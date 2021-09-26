package duke.main;

import java.util.ArrayList;
import java.util.List;

import duke.tasks.Task;

/**
 * Represents a class to hold all tasks
 */
public class TaskList {
    protected ArrayList<Task> taskList;
    private int numTaskDone;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Creates a task list
     * @param storageList list of storageElement
     */
    public TaskList(List<StorageElement> storageList) {
        assert storageList.size() > 0 : "StorageList length should not be 0";
        taskList = new ArrayList<>();
        for (StorageElement storageElement : storageList) {
            this.addTask(Task.of(storageElement));
        }
    }

    public void setNumTaskDone(int numTaskDone) {
        this.numTaskDone = numTaskDone;
    }

    public int getNumTaskDone() {
        return numTaskDone;
    }

    /**
     * Adds task to list of tasks
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
        // Only update the count if the task is not done before
        if (!this.taskList.get(index).isDone()) {
            this.numTaskDone += 1;
        }

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
        assert index < this.taskList.size() : "Index to be deleted is out of range of list of task";
        return this.taskList.remove(index);
    }

    /**
     * Find a task by key word
     * @param keyWords keyword to be searched
     * @return tasks found in form of strings
     */
    public String findTaskByKeyWord(String ... keyWords) {
        boolean canFind = false;
        String description = "";
        for (String keyWord : keyWords) {
            for (int i = 0; i < this.taskList.size(); i += 1) {
                String taskDescription = this.taskList.get(i).getDescription();
                if (taskDescription.contains(keyWord)) {
                    description += (i + 1) + "." + this.taskList.get(i) + "\n";
                    canFind = true;
                }
            }
        }

        if (!canFind) {
            return "No tasks found with this keyword";
        }

        //To remove last new line
        description = description.substring(0, description.length() - 1);
        return description;
    }

    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    /**
     * String representation of TaskList
     * @return String representation of taskList
     */
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
