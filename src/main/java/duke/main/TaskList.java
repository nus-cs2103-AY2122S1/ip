package duke.main;
import duke.tasks.Task;

import java.util.ArrayList;
import java.util.List;

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

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public Task markAsDone(int index) {
        this.taskList.get(index).markAsDone();
        return this.taskList.get(index);
    }

    public int getNumTask() {
        return this.taskList.size();
    }

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