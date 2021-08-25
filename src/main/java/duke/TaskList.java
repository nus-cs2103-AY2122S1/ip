package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> taskList = new ArrayList<>();

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {

    }

    public int getTotalNumberOfTask() {
        return taskList.size();
    }

    public Task getTaskById(int id) {
        return taskList.get(id);
    }

    public void markTaskDoneById(int id) {
        Task task = taskList.get(id);
        task.markDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("\t" + task);
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void removeTaskById(int id) {
        taskList.remove(id);
    }
}
