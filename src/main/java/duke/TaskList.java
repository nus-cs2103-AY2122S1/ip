package duke;
import java.util.ArrayList;
import java.util.List;

import duke.task.Task;

public class TaskList {

    private final List<Task> taskList;

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
}