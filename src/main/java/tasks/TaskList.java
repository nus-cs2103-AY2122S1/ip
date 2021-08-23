package tasks;

import tasks.Task;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    public void markAsDone(int index) {
        this.taskList.get(index).markAsDone();
    }

    public int getNumTask() {
        return this.taskList.size();
    }

    public void deleteTask(int index) {
        this.taskList.remove(index);
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

    public String saveFormat() {
        String fileContent = "";
        for (Task task : this.taskList) {
            fileContent += task.saveFormat() + "\n";
        }
        return fileContent;
    }
}