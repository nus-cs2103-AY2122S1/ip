package duke;
import java.util.ArrayList;
import java.util.List;

import duke.Task.Task;

public class TaskList {

    private final List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }
    public TaskList(List<Task> storedTasks) {
        this.taskList = new ArrayList<>();
        taskList.addAll(storedTasks);
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public Task deleteTaskAtIndex(int index) {
        return taskList.remove(index);
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public int getTaskListLength() {
        return taskList.size();
    }

    public void completeTask(int index) {
        taskList.get(index).markAsCompleted();
    }

    public TaskList searchTaskList(String description) {
        TaskList matchingTasks = new TaskList();
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getDescription().contains(description)) {
                matchingTasks.addTask(taskList.get(i));
            }
        }
        return matchingTasks;
    }
}