package duke;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskList = new ArrayList<Task>();

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    public Integer taskListSize() {
        return this.taskList.size() - 1;
    }

    public void setTaskDone(int index) {
        taskList.get(index).markAsDone();
    }

    public Task removeTask(int index) {
        Task.noOfTask -= 1;
        return taskList.remove(index);
    }
}
