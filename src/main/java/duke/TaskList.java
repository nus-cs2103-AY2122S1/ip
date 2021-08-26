package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> taskList;
    private int taskNumber;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public void deleteTask(int index) {
        taskList.remove(index);
        taskNumber -= 1;
    }

    public void deleteTask(Task task) {
        taskList.remove(task);
        taskNumber -= 1;
    }

    public void addTask(Task task) {
        taskList.add(task);
        taskNumber += 1;
    }

    public int getTaskNumber() {
        return taskNumber;
    }


}
