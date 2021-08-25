package duke.tasks;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> list) {
        taskList = list;
    }

    public int length() {

        return taskList.size();
    }

    public void addTask(Task task) {

        taskList.add(task);
    }

    public Task getTask(int index) {

        return taskList.get(index);
    }

    public void deleteTask(int index) {

        taskList.remove(index);
    }

    public ArrayList<Task> toArrayList() {

        return taskList;
    }
}
