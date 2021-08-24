package duke.classes;

import duke.tasks.Task;

import java.util.List;

public class TaskList {
    private List<Task> taskList;

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public Task remove(int index) {
        return taskList.remove(index);
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public int size() {
        return taskList.size();
    }

    public int last() {
        return taskList.size() - 1;
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    public void completeTask(int index) {
        taskList.get(index).markAsDone();
    }
}
