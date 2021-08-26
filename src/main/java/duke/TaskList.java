package duke;

import java.util.List;

public class TaskList {
    private List<Task> taskList;

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }
    public void deleteTask(int taskIndex) {
        taskList.remove(taskIndex);
    }

    public int length() {
        return taskList.size();
    }

    public List<Task> getTaskList() {
        return this.taskList;
    }

    public Task getTask(int taskIndex) {
        return taskList.get(taskIndex);
    }
}


