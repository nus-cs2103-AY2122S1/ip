package duke.data;

import java.util.ArrayList;
import duke.task.Task;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public int getLength() {
        return this.taskList.size();
    }

    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    public void removeTask(Task task) {
        this.taskList.remove(this.taskList.indexOf(task));
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

}
