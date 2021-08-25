package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public int size() {
        return taskList.size();
    }

    public Task get(int taskNo) {
        return this.taskList.get(taskNo - 1);
    }

    public boolean isEmpty() {
        return this.taskList.isEmpty();
    }

    public void remove(int taskNo) {
        this.taskList.remove(taskNo - 1);
    }

    public void add(Task task) {
        this.taskList.add(task);
    }
}
